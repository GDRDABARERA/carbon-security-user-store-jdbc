/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.security.connector.jdbc.internal;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.datasource.core.api.DataSourceService;
import org.wso2.carbon.security.connector.jdbc.util.DatabaseUtil;
import org.wso2.carbon.security.usercore.connector.AuthorizationStoreConnector;
import org.wso2.carbon.security.usercore.connector.CredentialStoreConnector;
import org.wso2.carbon.security.usercore.connector.IdentityStoreConnector;
import org.wso2.carbon.security.connector.jdbc.JDBCAuthorizationConnector;
import org.wso2.carbon.security.connector.jdbc.JDBCCredentialStoreConnector;
import org.wso2.carbon.security.connector.jdbc.JDBCIdentityStoreConnector;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * OSGi component for carbon security connectors.
 */
@Component(
        name = "org.wso2.carbon.security.connector.jdbc.ConnectorComponent",
        immediate = true
)
public class ConnectorComponent {

    private static final Logger log = LoggerFactory.getLogger(ConnectorComponent.class);

    @Activate
    public void registerCarbonSecurityConnectors(BundleContext bundleContext) {

        Dictionary<String, String> connectorProperties = new Hashtable<>();

        connectorProperties.put("connector-id", "JDBCIdentityStore");
        bundleContext.registerService(IdentityStoreConnector.class, new JDBCIdentityStoreConnector(),
                connectorProperties);

        connectorProperties = new Hashtable<>();
        connectorProperties.put("connector-id", "JDBCAuthorizationStore");
        bundleContext.registerService(AuthorizationStoreConnector.class, new JDBCAuthorizationConnector(),
                connectorProperties);

        connectorProperties = new Hashtable<>();
        connectorProperties.put("connector-id", "JDBCCredentialStore");
        bundleContext.registerService(CredentialStoreConnector.class, new JDBCCredentialStoreConnector(),
                connectorProperties);
    }

    @Reference(
            name = "org.wso2.carbon.datasource.DataSourceService",
            service = DataSourceService.class,
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unregisterDataSourceService"
    )
    protected void registerDataSourceService(DataSourceService service) {

        if (service == null) {
            log.error("DataSourceService is null");
            return;
        }
        DatabaseUtil.getInstance().setDataSourceService(service);
    }

    protected void unregisterDataSourceService(DataSourceService service) {
    }
}

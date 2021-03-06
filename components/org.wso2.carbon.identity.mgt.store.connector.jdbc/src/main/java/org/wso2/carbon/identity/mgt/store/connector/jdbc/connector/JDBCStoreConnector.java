/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.mgt.store.connector.jdbc.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.mgt.exception.StoreException;
import org.wso2.carbon.identity.mgt.store.connector.jdbc.constant.ConnectorConstants;
import org.wso2.carbon.identity.mgt.store.connector.jdbc.queries.MySQLFamilySQLQueryFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a JDBC based store connector.
 *
 * @since 1.0.0
 */
public abstract class JDBCStoreConnector {

    private static Logger log = LoggerFactory.getLogger(JDBCStoreConnector.class);

    protected Map<String, String> sqlQueries;

    protected void loadQueries(Map<String, String> properties) {

        String databaseType = properties.get(ConnectorConstants.DATABASE_TYPE);

        if (databaseType != null && (databaseType.equalsIgnoreCase("MySQL") || databaseType.equalsIgnoreCase("H2"))) {
            sqlQueries = new MySQLFamilySQLQueryFactory().getQueries();
            if (log.isDebugEnabled()) {
                log.debug("{} sql queries loaded for database type: {}.", sqlQueries.size(), databaseType);
            }
        } else {
            throw new StoreException("Invalid or unsupported database type specified in the configuration.");
        }

        // If there are matching queries in the properties, we have to override the default and replace with them.
        sqlQueries.putAll(sqlQueries.keySet().stream()
                .filter(properties::containsKey)
                .collect(Collectors.toMap(key -> key, properties::get)));
    }
}

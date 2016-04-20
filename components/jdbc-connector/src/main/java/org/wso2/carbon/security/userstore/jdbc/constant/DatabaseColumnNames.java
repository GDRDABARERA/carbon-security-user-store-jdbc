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

package org.wso2.carbon.security.userstore.jdbc.constant;

/**
 * Names of the database table columns.
 */
public class DatabaseColumnNames {

    /**
     * Names of the Group table columns.
     */
    public static final class Group {
        public static final String ID = "ID";
        public static final String GROUP_UNIQUE_ID = "GROUP_UNIQUE_ID";
        public static final String GROUP_NAME = "GROUP_NAME";
    }

    /**
     * Names of the User table columns.
     */
    public static final class User {
        public static final String ID = "ID";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String USER_UNIQUE_ID = "USER_UNIQUE_ID";
        public static final String TENANT_ID = "TENANT_ID";
    }

    /**
     * Names of the Group table columns.
     */
    public static final class Role {

        public static final String ROLE_UNIQUE_ID = "ROLE_UNIQUE_ID";
        public static final String ROLE_NAME = "ROLE_NAME";
    }

    /**
     * Names of the UserAttributes table columns.
     */
    public static final class UserAttributes {
        public static final String ATTR_NAME = "ATTR_NAME";
        public static final String ATTR_VALUE = "ATTR_VALUE";
        public static final String USER_ID = "USER_ID";
    }

    /**
     * Names of the UserGroup table columns.
     */
    public static final class UserGroup {
        public static final String USER_ID = "USER_ID";
        public static final String GROUP_ID = "GROUP_ID";
    }

    /**
     * Names of the PasswordInfo table columns.
     */
    public static final class PasswordInfo {
        public static final String HASH_ALGO = "HASH_ALGO";
        public static final String PASSWORD_SALT = "PASSWORD_SALT";
    }

    /**
     * Names of the Permission table columns.
     */
    public static final class Permission {
        public static final String RESOURCE_ID = "RESOURCE_ID";
        public static final String ACTION = "ACTION";
    }
}

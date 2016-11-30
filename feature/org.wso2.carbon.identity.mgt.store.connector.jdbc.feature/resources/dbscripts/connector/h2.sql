--
-- Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

/* NOTE: Use VARCHAR(255) instead of VARCHAR(256) if the length needed is less than 256. Because 256 will require
 * two bytes to store the VARCHAR character length.
 */

--IDENTITY STORE
CREATE TABLE UM_USER
(
  ID             INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  USER_UNIQUE_ID VARCHAR(64)                        NOT NULL,
);
CREATE UNIQUE INDEX unique_ID_INDEX_1
  ON UM_USER (USER_UNIQUE_ID);

CREATE TABLE UM_GROUP
(
  ID              INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  GROUP_UNIQUE_ID VARCHAR(64)                        NOT NULL,
);

CREATE TABLE UM_ATTRIBUTES
(
  ID        INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  ATTR_NAME VARCHAR(255)                       NOT NULL,
);

CREATE TABLE UM_USER_ATTRIBUTES
(
  ID         INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  ATTR_ID    INTEGER                            NOT NULL,
  ATTR_VALUE VARCHAR(1024)                      NOT NULL,
  USER_ID    INTEGER                            NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES UM_USER (ID) ON DELETE CASCADE,
  FOREIGN KEY (ATTR_ID) REFERENCES UM_ATTRIBUTES (ID) ON DELETE CASCADE
);

CREATE TABLE UM_GROUP_ATTRIBUTES
(
  ID         INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  ATTR_ID    INTEGER                            NOT NULL,
  ATTR_VALUE VARCHAR(1024)                      NOT NULL,
  GROUP_ID   INTEGER                            NOT NULL,
  FOREIGN KEY (GROUP_ID) REFERENCES UM_GROUP (ID) ON DELETE CASCADE,
  FOREIGN KEY (ATTR_ID) REFERENCES UM_ATTRIBUTES (ID) ON DELETE CASCADE
);

CREATE TABLE UM_USER_GROUP
(
  ID       INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  USER_ID  INTEGER                            NOT NULL,
  GROUP_ID INTEGER                            NOT NULL,
  FOREIGN KEY (GROUP_ID) REFERENCES UM_GROUP (ID) ON DELETE CASCADE,
  FOREIGN KEY (USER_ID) REFERENCES UM_USER (ID) ON DELETE CASCADE
);

--CREDENTIAL STORE
CREATE TABLE UM_PASSWORD
(
  ID             INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  PASSWORD       VARCHAR(88)                        NOT NULL,
  USER_UNIQUE_ID VARCHAR(64)                        NOT NULL,
);

CREATE TABLE UM_PASSWORD_INFO
(
  ID              INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
  PASSWORD_SALT   VARCHAR(64)                        NOT NULL,
  HASH_ALGO       VARCHAR(128)                       NOT NULL,
  ITERATION_COUNT INT,
  KEY_LENGTH      INT,
  USER_ID         VARCHAR(64)                        NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES UM_PASSWORD (ID) ON DELETE CASCADE
);

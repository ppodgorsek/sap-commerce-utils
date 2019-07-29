# SAP Commerce-utils

A set of extensions to improve SAP Commerce, formely known as SAP Hybris Commerce.

Disclaimer: this project is not affiliated, associated, authorized, endorsed by, or in any way officially connected with SAP, or any of its subsidiaries or its affiliates.

## HikariCP datasource

The default Commerce datasource can be replaced by the more performant HikariCP.

### local.properties

New properties have to be defined in order to use the new datasource:

    db.pool.name=sapCommerceDataSource
    db.pool.fromJNDI=java:comp/env/jdbc/${db.pool.name}
    
    # TODO: select the correct database type below
    db.pool.fromJNDI.dbtype=hsqldb|oracle|mysql|sqlserver|sap

### Declare the datasource in the application server

The steps below describe how to install the datasource in Tomcat but they are very similar for tcServer.

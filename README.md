# SAP Commerce Utils

A set of extensions to improve SAP Commerce, formely known as SAP Hybris Commerce.

**Disclaimer: this project is not affiliated, associated, authorized, endorsed by, or in any way officially connected with SAP, or any of its subsidiaries or its affiliates.**

## HikariCP datasource

The default Commerce datasource can be replaced by [HikariCP](https://github.com/brettwooldridge/HikariCP), which is more performant.

### Installing the library

The HikariCP library can be easily installed by running the following command from your `platform` folder:

    ant customize

Along with the HikariCP library, Slf4j libraries also need to be copied, as the datasource relies on them. They are however not used by Tomcat and simply pass on any log messages to the existing logger.

### local.properties

New properties have to be defined in order to use the new datasource:

    db.pool.name=sapCommerceDataSource
    db.pool.fromJNDI=java:comp/env/jdbc/${db.pool.name}
    
    # TODO: select the correct database type below
    db.pool.fromJNDI.dbtype=hsqldb|oracle|mysql|sqlserver|sap
    
    # TODO: select the correct datasource type below
    db.pool.dataSourceClassName=org.hsqldb.jdbc.JDBCDataSource|oracle.jdbc.pool.OracleDataSource|com.mysql.jdbc.jdbc2.optional.MysqlDataSource|com.microsoft.sqlserver.jdbc.SQLServerDataSource

### Declare the datasource in the application server

The steps below describe how to install the datasource in Tomcat but they are very similar for tcServer.

  1. Open `hybris/config/tomcat/conf/server.xml`
  2. Add a new listener just before the `<GlobalNamingResources>` tag:
  
      ```xml
      <Listener className="de.hybris.tomcat.HybrisGlobalResourcesLifecycleListener" />
      ```
  
  3. Declare the datasource resource in the `<GlobalNamingResources>` tag:
  
  * **Oracle:**
      ```xml
      <Resource name="${db.pool.name}"
                auth="Container"
                type="javax.sql.DataSource"
                factory="com.zaxxer.hikari.HikariJNDIFactory"
                dataSourceClassName="${db.pool.dataSourceClassName}"
                username="${db.username}"
                password="${db.password.XMLENCODED}"
                connectionTimeout="${db.pool.maxWait}"
                maximumPoolSize="${db.pool.maxActive}"
                poolName="${db.pool.name}ConnectionPool"
                dataSource.url="${db.url.XMLENCODED}"
      />
      ```

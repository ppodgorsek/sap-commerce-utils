# SAP Commerce Utils

A set of extensions to improve SAP Commerce, formely known as SAP Hybris Commerce.

**Disclaimer: this project is not affiliated, associated, authorized, endorsed by, or in any way officially connected with SAP, or any of its subsidiaries or its affiliates.**

This project aims to improve the following features:

  * Persistent configuration using [ConfiguR](https://github.com/ppodgorsek/configur)
  * Datasource connection using [HikariCP](https://github.com/brettwooldridge/HikariCP)

## ConfiguR

Maintaining server configuration in `local.properties` is not convenient, as changes are not automatically persisted and are lost after each restart.

Instead of having to update the configuration file, ConfiguR allows to modify the configuration on-the-fly and all changes are persisted in the database.

Furthermore, it is fully compatible with the OOTB `ConfigurationService` class and properties are first fetched from the database before being looked up in the standard locations.

### Installing the extension

Two extensions have to be copied into your project:

  * hybris/bin/custom/configur
  * hybris/bin/custom/configur-backoffice

Remember to update your `localextensions.xml` too.

### Item types

New item types have been created:

  * `ConfigurCategory`: a category regrouping properties, such as "Analytics",
  * `ConfigurProperty`: a property having a key/value of course, but also a name and description for easy identification,
  * `ConfigurClusterNodeVariation`: a node-specific value for a property, allowing to define different values per node of the cluster.

The first two types can be found in the Backoffice under `System > Persistent Configuration`. The third type can be set via the configuration properties.

## HikariCP datasource

The default Commerce datasource can be replaced by HikariCP, which is more performant.

### Installing the library

As the datasource will be defined directly in Tomcat, your database library must be added to the Tomcat libraries in `hybris/config/customize/platform/tomcat/lib`:

  * Oracle: `ojdbc6-w.x.y.z.jar`
  * MySQL: `mysql-connector-java-x.y.z.jar`

The HikariCP library can then be easily installed by running the following command from your `platform` folder:

    ant customize

Along with the HikariCP library, Slf4j libraries will also be copied, as the datasource relies on them. They are however not used by Tomcat and simply pass on any log messages to the existing logger.

### local.properties

New properties have to be defined in order to use the new datasource:

  ```properties
    db.pool.name=sapCommerceDataSource
    db.pool.fromJNDI=java:jdbc/${db.pool.name}
  ```

These properties must however not be used for the JUnit tenant, as they will cause unexpected issues. To avoid this, add the following properties to `hybris/config/local_tenant_junit.properties`:

  ```properties
    db.pool.name=
    db.pool.fromJNDI=
    db.pool.fromJNDI.dbtype=
    db.pool.dataSourceClassName=
  ```

Different properties must be added, depending on the database you are using:

  * HSQLDB:
  
  ```properties
    db.pool.fromJNDI.dbtype=hsqldb
    db.pool.dataSourceClassName=org.hsqldb.jdbc.JDBCDataSource
  ```

  * Oracle:
  
  ```properties
    db.pool.fromJNDI.dbtype=oracle
    db.pool.dataSourceClassName=oracle.jdbc.pool.OracleDataSource
  
    # Make sure your DB URL is complete and has a syntax similar to below
    db.url=jdbc:oracle:thin:@127.0.0.1:1521:xe
  ```

  * MySQL:
  
  ```properties
    db.pool.fromJNDI.dbtype=mysql
    db.pool.dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
  ```

  * SQL Server:
  
  ```properties
    db.pool.fromJNDI.dbtype=sqlserver
    db.pool.dataSourceClassName=com.microsoft.sqlserver.jdbc.SQLServerDataSource
  ```

  * SAP HANA:
  
  ```properties
    db.pool.fromJNDI.dbtype=sap
    db.pool.dataSourceClassName=<DataSource class name for SAP HANA>
  ```

### Declare the datasource in the application server

The steps below describe how to install the datasource in Tomcat but they are very similar for tcServer.

  1. Open `hybris/config/tomcat/conf/context.xml` (if this file does not exist, create it based on `hybris/bin/platform/tomcat/conf/context.xml`)
  2. Declare the resource link for the Commerce application before the `<Context>` tag closes:
  
      ```xml
      <ResourceLink name="jdbc/${db.pool.name}"
                global="jdbc/${db.pool.name}"
                auth="Container"
                type="javax.sql.DataSource" />
      ```

  3. Open `hybris/config/tomcat/conf/server.xml`
  4. Declare the datasource resource in the `<GlobalNamingResources>` tag:
  
  * Oracle:
  
      ```xml
      <Resource name="jdbc/${db.pool.name}"
                global="jdbc/${db.pool.name}"
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

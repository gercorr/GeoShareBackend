# GeoShareBackend

You will need to add persistence.xml to here:
GeoShareBackend\src\main\resources\META-INF

persistence.xml (replace {xxx}):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
   xsi:schemaLocation="http://java.sun.com/xml/ns/persistence 
   http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
   
   <persistence-unit name="Eclipselink_JPA" transaction-type="RESOURCE_LOCAL">
   
      <class>com.geoshare.entities.Note</class>

      <properties>
         <property name="javax.persistence.jdbc.url" value="jdbc:mysql:{mysq path e.g. //localhost:1234/database_name}"/>
         <property name="javax.persistence.jdbc.user" value="{mysq username}"/>
         <property name="javax.persistence.jdbc.password" value="{mysq password)"/>
         <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
         <property name="eclipselink.logging.level" value="FINE"/>
         <property name="eclipselink.ddl-generation" value="create-tables"/>
      </properties>
      
   </persistence-unit>
</persistence>

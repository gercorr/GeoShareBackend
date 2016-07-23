# GeoShareBackend

All you should need to do is add persistence.xml to here:
GeoShareBackend\src\main\resources\META-INF

persistence.xml (replace {xxx}):
```xml
<?xml version="1.0" encoding="UTF-8"?>

<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
   xsi:schemaLocation="http://java.sun.com/xml/ns/persistence 
   http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
   
   <persistence-unit name="Hibernate_JPA" transaction-type="RESOURCE_LOCAL">
	
	<provider>org.hibernate.ejb.HibernatePersistence</provider>
   
      <class>com.geoshare.entities.Note</class>

      <properties>
      
         <property name="javax.persistence.jdbc.url" value="jdbc:mysql:{mysq path e.g. //localhost:1234/database_name}"/>
         <property name="javax.persistence.jdbc.user" value="{mysq username}"/>
         <property name="javax.persistence.jdbc.password" value="{mysq password)"/>
      
         <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
         <property name="hibernate.show_sql" value="true" />
		 <property name="hibernate.format_sql" value="true" />
		 <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />
		 <property name="hibernate.hbm2ddl.auto" value="update" />
      </properties>
      
   </persistence-unit>
</persistence>

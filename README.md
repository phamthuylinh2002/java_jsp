# Setup Project

## Thay đổi file pom.xml( phần build)
```
           <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <url>http://[IP_ADDRESS]:80/manager/text</url>
                    <username>[YOUR_TOMCAT_USERNAME]</username>
                    <password>[YOUR_TOMCAT_PASSWORD]</password>
                    <server>TomcatServer</server>
                    <path>[YOUR_PATH]</path>
                    <encoding>UTF-8</encoding>
                    <update>true</update>
                </configuration>
            </plugin>
```

## Thay đổi file src/main/resources/hibernate.cfg.xml && src/main/java/hibernate.cfg.xml
```
        <property name="connection.username">[your_username]</property>
        <property name="connection.password">[your_password]</property>
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://[IP_ADDREDD]:[PORT]/[DATABASE_NAME]</property>
        <property name="hibernate.default_schema">[DATABASE_NAME]</property>
```
## Thay đổi file .docker/tomcat-users.xml
```
<user username=[your_username] password=[your_password] roles="manager-gui, manager-script, manager-jmx,manager-status" />

```
## Tạo file  src/main/resources/META-INF/persistence.xml && src/main/java/META-INF/persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
   <persistence-unit name="ToolDangKy" transaction-type="RESOURCE_LOCAL">
       <class>model.BoMon</class>
       <class>model.ChuyenNganh</class>
       <class>model.ChuyenNganhGv</class>
       <class>model.CoSo</class>
       <class>model.DotDangKy</class>
       <class>model.GvHuongDan</class>
       <class>model.NhanVien</class>
       <class>model.Nhom</class>
       <class>model.SinhVien</class>
 
<properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://[IP_ADDRESS]:[PORT]/[DATABASE_NAME]"/>
            <property name="javax.persistence.jdbc.user" value="[USERNAME]"/>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.password" value="[PASSWORD]"/>          
        </properties>
   </persistence-unit>
</persistence>

```

## Chỉnh file docker-compose.yml
```
version: '3'
services:
  webapp:
    build: .
    ports:
            - "[IP_ADDRESS]:80:8080"
    volumes:
      - ./:/var/www/app      
    links:
      - db
  db:
    image: mysql:latest
    restart: always
    command: --character-set-server=utf8 --collation-server=utf8_general_ci
    environment:
      MYSQL_ROOT_PASSWORD: [PASSWORD]
    volumes:
      - data:/var/lib/mysql
    ports:
            - "[IP_ADDRESS]:3306:3306"
  PhpMyAdmin:
    image: phpmyadmin/phpmyadmin
    restart: always
    ports:
            - "[IP_ADDRESS]:8090:80"
    links:
      - db:mysql
volumes:
  data:
```

## Cấu hình Client_id & client_secret của google
```
vim src/main/java/common/Constants.java
```

```
public final static String GOOGLE_CLIENT_ID = "YOUR_CLIENT_ID";
	public final static String GOOGLE_CLIENT_SECRET = "YOUR_CLIENT_SECRET";
	public final static String GOOGLE_REDIRECT_URI = "YOUR_REDIRECT_URI";
	public final static String GOOGLE_GRANT_TYPE = "authorization_code";
	public final static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public final static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
```

## Chạy docker-compose( terminal đã được cd đến project)
```
docker-compose up -d
```

## Truy cập vào docker
```
docker exec -it [docker-container-name] /bin/bash
```

## cd đến project
```
cd /var/www/app
```

## Deploy project
```
mvn tomcat7:deploy -e

```

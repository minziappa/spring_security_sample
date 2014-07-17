# A sample for Spring Security

By Kim woong joon
kim_woongjoon@cyberagent.co.jp

## About
1. More [information](http://projects.spring.io/spring-security/) about Spring Security.

* Spring Security

## Requirements environment 
* Java7
* Maven3
* Tomcat7

## Get started
In the Dependencies of a Pom.xml, add the following:
```
<properties>
	<org.springframework.secutiry.version>3.1.4.RELEASE</org.springframework.secutiry.version>
</properties>

<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-core</artifactId>
	<version>${org.springframework.secutiry.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-web</artifactId>
	<version>${org.springframework.secutiry.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-config</artifactId>
	<version>${org.springframework.secutiry.version}</version>
</dependency>
```

## Deploy
Compile & make a war file
```
mvn -U clean package
```

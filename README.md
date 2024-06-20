<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>by.itclass</groupId>
  <artifactId>final_project_2211</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>final_project_2211 Maven Webapp</name>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.30</version>

      <!-- ••− −•• −−− •−•• •−•− • −− −−•• •−•• −−− −− −••• −−− −•− •− −•• −−•• −• •−• •− −•• −−− −•− −•− −−− −•• •−  -->
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>6.0.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <version>8.0.33</version>
    </dependency>
    <dependency>
      <groupId>org.glassfish.web</groupId>
      <artifactId>jakarta.servlet.jsp.jstl</artifactId>
      <version>2.0.0</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>final_project_2211</finalName>
    <plugins>
      <plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>11.0.18</version>
      </plugin>
    </plugins>
  </build>
</project>


![video-0-00-01-536](https://github.com/ivakhnenkovitali/final_project_2211/assets/141067997/745efbc3-81ae-4a22-9141-6430a6717a87)

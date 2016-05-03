Project is a Maven project, dependancies are in the pom and therefore should download automatically from 
the maven repostitory.

Dependancies are
 <dependencies>
  	<dependency>
  		<groupId>org.jsoup</groupId>
  		<artifactId>jsoup</artifactId>
  		<version>1.8.3</version>
  	</dependency>
  	<dependency>
  		<groupId>com.googlecode.json-simple</groupId>
  		<artifactId>json-simple</artifactId>
  		<version>1.1</version>
  	</dependency>
  	<dependency>
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-core</artifactId>
		<version>2.7.3</version>
	</dependency>
  	<dependency>
  		<groupId>org.codehaus.jackson</groupId>
  		<artifactId>jackson-mapper-asl</artifactId>
  		<version>1.9.13</version>
  	</dependency>
  </dependencies>

The project is a console project and will run as soon as the main method is invoked. 
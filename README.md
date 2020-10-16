# elastic-search-demo
This project is to demonstrate how we can use **Elasticsearch** with 
**Spring Boot**, as well as we will also **dockerize** the **Spring Boot App**, 
**Elasticsearch**, and **Kibana** all together.

Here we have two indexes  **customer** and **order**, we are storing 
them in the **Elasticsearch** and I have developed **APIs** in the **Spring Boot** so we can easily manage these indexes in the **Elasticsearch**, you will get the more 
information on how to access these APIs in the following section.

### Requirements To Run Applications
* JDK 14
* Elasticsearch

### Dependencies And Tools Used To Build Applications
* Git
* JDK 14
* Spring Boot
* Elasticsearch
* Gradle
* Lombok
* MapStruct
* Apache Commons
* Swagger
* IDE  

### Run the application locally
1. Download the zip or clone the Git repository
4. Start Elasticsearch
5. Config Elasticsearch properties from `elastic-search-demo/src/main/resources/application-dev.yml` if needed.
6. Go to the `elastic-search-demo/` directory and open  the 
terminal and simply run the following command to run the app
   
   **In Windows** 
       
       gradlew bootrun
  
   **In Linux**
   
       bash gradlew bootrun
  
And we are done, now you can open the swagger to access the APIs: 
[Swagger] (http://localhost:8081/swagger-ui/)


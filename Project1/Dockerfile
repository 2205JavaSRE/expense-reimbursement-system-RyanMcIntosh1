FROM openjdk:8-jdk

#Copying over the jar file into the container
ADD target/Project1-0.0.1-SNAPSHOT-shaded.jar Project1-0.0.1-SNAPSHOT-shaded.jar


#Setting up environment variables
ENV Endpoint=jdbc:postgresql://database-1.c7kojdmg9vdy.us-east-1.rds.amazonaws.com:5432/postgres
ENV DBUsername=postgres
ENV DBPassword=password

#Final command in the dockerfile
CMD ["java","-jar","Project1-0.0.1-SNAPSHOT-shaded.jar"]


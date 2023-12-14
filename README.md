# expenses-microservice
install docker desktop

run: docker compose up -d

The API calls are protected with Keycloak. You should create a real called **spring-boot-microservices-realm** and a new cliet called **spring-cloud-client**.
You should to introduce this data in postman to geneate a new token.

run services in order:

1. Registry-service: it´s Eureka service in order to be recognize services among themselves
2. api-gateway: it´s gateway service with security to call the rest of the services
3. user-service: collect info user. This service is a kafka producer to send msg to wallet-service
4. expenses-service: this service is responsable for control expeses related to user and wallet
5. wallet-service: this service is a consumer kafka. Every time new user is created, user-service sends a notification to wallet-service and this service will create a new wallet individually for each user


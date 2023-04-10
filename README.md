# momiji-extractor

Momiji Crawler System is a series of interconnected services which primary purpose is to obtain data from ecommerce websites.
Momiji Wood Chipper is a part of services that makes Momiji Extractor. This service is mainly responsible for extracting data from crawler services. To decouple these services, our crawler system is using RabbitMQ message boker to pass messages between services.


## Using technologies:
- Java 19
- Spring Boot
- Spring AMQP
- RabbitMQ

## Install Requirements:
- Java SDK 19
- Project Lombok
- RabbitMQ Server

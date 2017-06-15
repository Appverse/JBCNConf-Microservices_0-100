# JBCNConf-Microservices_0-100
This repository is created to host the Microservices from 0 to 100 at JBCNConf 2017

**You still can expect changes!!!**

We encourage you to go over this readme **before the conference** as there is some heavy download required for the workshop and previous experiences demonstrate that WiFi at conferences cannot stand against a workshop using Docker, maven or npm... all WiFi's just die when everyone starts downloading!

## Microservices Platform

### Requirements

The requirements for running this demo on your machine are found below.

**Please Install:**

* Maven 3
* Java 8
* Docker
* Docker Compose
* cUrl (optional)

**Please run:** (so they get cached)

* Docker images used (download)
  * docker pull spotify/kafka
  * docker pull mysql:8.0
  * docker pull java:8-jre-alpine
  * docker pull mongo

#### Docker Configuration 

##### Docker for Mac or Windows 
  
* Assign more RAM - 4gb recommended
* Set the *DOCKER_HOST_IP* environment variable with the Docker Host IP or just *localhost*.

  ```
  $ export DOCKER_HOST_IP=localhost
  ```

##### Usign Docker Toolbox with Virtualbox  (TBDN)

- Assign More RAM 

  It's recommended to increase the assigned memory

  Example using Docker Machine: 

  ```
  $ docker-machine create --driver virtualbox default --virtualbox-memory 8192
  ```

  or change the VM settings on Virtualbox settings. 

- Set the *DOCKER_HOST_IP* environment variable with the Docker Host IP.

  ```
  $ export DOCKER_HOST_IP=$(docker-machine ip ${DOCKER_MACHINE_NAME:-default})
  ```

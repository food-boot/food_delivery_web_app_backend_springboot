# Food-Delivery-Web-App-BackEnd-SpringBoot

## Build from the Source

Download the source code. You can either download it as a zip file and 
extract it or simply execute the following command in the terminal.

`git clone 
https://github.com/food-boot/Food-Delivery-Web-App-BackEnd-SpringBoot.git`

Change your directory into the project folder. And run the following command.

`mvn install`

* For windows try this 
[link](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) to 
install maven.
* for ubuntu you can use this 
[link](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/) 
to install maven.

Next run the command,

`mvn spring-boot:run`

and the server will start automatically. Try to visit 
[this](http://localhost:8080/food-boot/swagger-ui.html) url to see the api 
documentation.

> NOTE
> 
> If you failed to start the server try to configure your database 
details with the project. To update database details find the file 
located in **`src\main\resources`** and find the file 
**`application.properties`**. And update following details.
> 
> `spring.datasource.username=<your database username>`
> `spring.datasource.password=<your database user's password>`
> 
>And then you will be good to go. Make sure to start the spring app when 
you are done editing the above mention file.

If you wan't to run the war version of the spring app, locate to the 
folder `/target` which will be in your directory when you run the `mvn 
install command`, and in there you will see you `.war` file.

To run the war file, use the below code.

`java -jar food-delivery-web-app-0.0.1-SNAPSHOT.war`

## Docker Installation

The docker file containing the docker image building is in the **1.0.0 branch**. If you wish to use the docker file for the building, please follow the following steps.

1. Make sure you have already installed Docker in your machine. If it is not, please refere [this](https://docs.docker.com/engine/install/) link.
2. Switch to the 1.0.0 branch
    `git checkout 1.0.0`
3. Use a terminal inside the project where the Dockerfile is located. Execute the following command to build the image. Replace `<your tag>` by the tag of your own.
    `docker build --tag <your tag> .`
5. Run the image as a container.
    `docker run -d -p 8080:8080 <yout tag> `
7. Use [docker exec](https://docs.docker.com/engine/reference/commandline/exec/) command to access the container.

PR's are welcome. Please submit any issue that you faced when developing your spring-boot application.

# Hacker News Microservice built using Spring Boot

<h2> Prerequisite </h2>

<h3> 1. Cassandra Configuration </h3>

Create Cassandra Astra Account in DataStax https://astra.datastax.com/register

After registration create your keyspace and configure credentails for your Cassandra Astra Instance.

Download Cassandra Secure Connect bundle <b> Rename it as secure-connect.zip and place it in resource/cassandra_bundle/secure-connect.zip </b>

<h3> 2. Create Cassandra Table - CQL </h3>

Launch developer studio from conection details

create your notebook and run the below query

    create table story(id int, title text, url text, score int, time int, by text, descendants int, kids list<int>,type text,primary key(id));

<h3> 3. Update application.properties file </h3>

update the following properties in application.properties file

    spring.cassandra.username = <Cassandra Astra Useranme>

    spring.cassandra.password = <Cassandra Astra Password>

    spring.cassandra.keyspace = <Cassandra Astra Keyspace>

<h2> Docker Build </h2>

Docker file provided in the project, build docker image using following cmd

    docker build -t hackerstory:1.0
    
<h2> Swagger Document </h2>

Swagger document available at following url http://localhost:PORT/swagger-ui.html
    

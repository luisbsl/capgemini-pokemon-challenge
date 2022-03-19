# Capgemini Pokemon Challenge

API Rest reativa com integração às <b> APIs OpenWeatherMap e PokeApi </b> para gerar o resultado desejado pelo desafio


## Tecnologias utilizadas

* Java 11
* Spring Boot 2.6.4
* Webflux
* Lombok


## Requisitos para executar a aplicação

Fornecer a <b> appid da API OpenWeatherMap </b> no arquivo application.yml


## Executando a aplicação

```console
foo@bar:~$ ./mvnw package && java -jar target/challenge-0.0.1-SNAPSHOT.jar
```


## Executando a aplicação com Docker

```console
foo@bar:~$ ./mvnw package && docker build -t capgemini/pokemon-challenge .
foo@bar:~$ docker run -p 8080:8080 capgemini/pokemon-challenge 
```

## Realizando requisição no Browser passando a cidade

http://localhost:8080/pokemons?city=canoas

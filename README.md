# Projeto de Gerenciamento de Professores

Este projeto é um sistema de gerenciamento de professores desenvolvido em Java utilizando o framework Spring Boot. Ele permite a criação, listagem e consulta de professores através de uma API REST.

## Tecnologias Utilizadas

- Java 22
- Spring Boot 3.2.0
- Maven
- OpenFeign
- Lombok
- ModelMapper
- Kafka
- SoapUI

## Estrutura do Projeto

- `src/main/java/com/example/ms/teachers/management`: Contém o código fonte principal do projeto.
- `src/main/resources`: Contém arquivos de configuração e recursos estáticos.
- `src/test/java/com/example/ms/teachers/management`: Contém os testes unitários e de integração.

## Pré-requisitos

- Java 22
- Maven 3.6+
- Docker (opcional, para executar o Kafka)
- SoapUI (para mocks de `instructors`)


## Configuração
1. Compile o projeto:
    ```sh
    mvn clean install
    ```

- Inicie o Kafka (se necessário):  
- docker-compose up -d

2. Execute a aplicação:
    ```sh
    mvn spring-boot:run
    ```

## Endpoints da API

- GET /teachers: Lista todos os professores.
- GET /teachers/{id}: Consulta um professor pelo ID.
- POST /teachers: Cria um novo professor.


## Configurações Adicionais

- Porta padrão: 8080
- Documentação da API: /swagger-ui.html (os arquivos swagger estão localizados no projeto Ms Teachers Management)


## Mock de instructors com SoapUI

- Abra o SoapUI e crie um novo projeto.
- Adicione um novo MockService.
- Adicione as operações getInstructor, getInstructorById e createInstructor ao MockService.
- Configure as respostas mock para cada operação.
- Inicie o MockService no SoapUI antes de executar os testes ou a aplicação.



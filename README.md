
# DummyJSON Client - Java 8 e Spring Boot 2.x.x

## Descrição do Projeto

Este projeto é um microsserviço Java que interage com a API pública [DummyJSON](https://dummyjson.com/docs/products) para realizar operações de busca de produtos. O projeto foi desenvolvido usando Java 8 e Spring Boot 2.6.x.

## Objetivo do Desafio

O desafio consiste em migrar este projeto para Java 17 e Spring Boot 3.2.5. Durante a migração, você enfrentará várias dificuldades, incluindo a adaptação ao novo namespace, substituição de métodos depreciados e ajustes em testes unitários.

## Funcionalidades

- **Consulta de Produtos**: Realiza chamadas para a API do DummyJSON para buscar informações sobre produtos.
- **Integração com `RestTemplate`**: Utiliza `RestTemplate` para realizar chamadas HTTP.
- **Validação de Dados**: Validação de dados de entrada usando Bean Validation (`javax.validation`).
- **Gestão de Dependências**: Configurado para utilizar @Autowired.
- **Testes Unitários**: Inclui testes unitários desenvolvidos com JUnit 4 e Mockito.

## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjsonclient
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── config
│   │   │       │   └── RestTemplateConfig.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       ├── service
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjsonclient
│       │       ├── config
│       │       │   └── RestTemplateConfigTest.java
│       │       └── controller
│       │       │   └── ProductControllerTest.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       └── service
│       │           └── ProductServiceTest.java
│       └── resources
└── pom.xml
```

## Passos para Executar o Projeto

### Pré-requisitos

- **Java 8**
- **Maven 3.8.x**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/WendellTufano/code-challenge-migration.git
    cd dummyjson-client
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço:

    O serviço estará disponível em `http://localhost:8080`.

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```

## Requisitos de Entrega

1. Atualizar o `pom.xml` para usar Java 17+ e Spring Boot 3.2.5.
2. Substituir `RestTemplate` por `WebClient` ou `Openfeign`.
3. Substituir os testes unitários feitos com `JUnit 4` e `Mockito` por testes de integração utilizando `@SpringBootTest`.
4. Refatorar qualquer código depreciado ou incompatível.
5. Garantir que todos os testes ainda passam após a migração.
6. Deixar a URL da API dummyjson parametrizada por ambiente no projeto.
7. Adicionar no projeto um novo path `/health` que retorna a saude do microsserviço.

## Validação Sobre o Challenge

- O projeto deve estar funcionando em Java 17 e Spring Boot 3.2.5.
- Todos os testes unitários devem ser executados e passar sem falhas.
- O código deve estar devidamente documentado e organizado.

## Extras

- Entregar o projeto em container será um diferencial.
- Fica a critério do desenvolvedor inserir ou remover dependencias do projeto para garantir o objetivo do challenge.


# ----------- Após migração ----------------

O projeto foi migrado para a Java 17 e spring Boot 3.2.5

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Validation
* Spring Actuator
* Spring Cloud OpenFeign
* Maven 3.8.x
* JUnit 5
* Mockito
* PiTest

## Pré-requisitos

Antes de rodar o projeto, você precisa ter o seguinte instalado:
* JDK 17+
* Maven 3.8.x (se não for utilizar a funcionalidade de build do IDE)

## Rodando o Projeto localmente

### 1. Clone o repositório

git clone https://github.com/kscosta/code-challenge-migration.git

### 2. Acesse o diretório do projeto

### 3. Compile e execute o projeto
 
* Produção:
```bash
  mvn clean install
  mvn spring-boot:run
```
* Desenvolvimento:
```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=development
```

### 4. Executando testes

```bash
  mvn clean test
```

Se estiver usando uma IDE como IntelliJ ou Eclipse, você pode rodar a classe principal (normalmente, a classe anotada com @SpringBootApplication) diretamente.

### 5. Acessando a API
Após iniciar o projeto, a aplicação estará rodando em http://localhost:8080/

### 6. Endpoints da API

* Busca Todos os Produtos: http://localhost:8080/api/products
* Busca Porduto por id: http://localhost:8080/api/products/1
* Saúde da aplicação: http://localhost:8080/health
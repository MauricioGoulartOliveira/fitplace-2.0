# FitPlace 2.0

## Descrição

FitPlace 2.0 é uma plataforma para gerenciar rotinas de exercícios, permitindo que os usuários registrem, visualizem e atualizem seus exercícios de forma fácil e eficiente. O sistema foi desenvolvido utilizando Java, Spring Boot e PostgreSQL, e inclui funcionalidades de autenticação JWT, documentação Swagger e operações CRUD para usuários e exercícios.

## Tecnologias Utilizadas

- **Backend**: Java, Spring Boot
- **Banco de Dados**: PostgreSQL
- **Autenticação**: JWT
- **Documentação**: Swagger
- **Testes**: Postman

## Funcionalidades

- **Cadastro de Usuários**: Os usuários podem se registrar na plataforma.
- **Login de Usuários**: Os usuários podem fazer login usando suas credenciais.
- **CRUD de Exercícios**: Os usuários podem criar, ler, atualizar e deletar seus exercícios.
- **Documentação da API**: A API é documentada usando Swagger.

## Configuração do Ambiente

### Pré-requisitos

- JDK 11 ou superior
- PostgreSQL
- Maven

### Configuração do Banco de Dados

1. Certifique-se de que o PostgreSQL está instalado e em execução.
2. Crie um banco de dados chamado `db_fitplace`.
3. Atualize as configurações do banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db_fitplace
spring.datasource.username=postgres
spring.datasource.password=xb100pro

Executando o Projeto
Clone o repositório:
git clone https://github.com/MauricioGoulartOliveira/fitplace-2.0.git
cd fitplace-2.0

Execute o projeto:
mvn spring-boot:run

Acesse a API através de http://localhost:8080.

Autenticação
Registrar Usuário

Método: POST
URL: /auth/register
Corpo:
{
  "nome": "Seu Nome",
  "sexo": "Masculino",
  "cpf": "12345678900",
  "dataNascimento": "1990-01-01",
  "email": "email@example.com",
  "senha": "suaSenha",
  "endereco": "Seu Endereço"
}

Método: POST
URL: /auth/login
Corpo:
{
  "email": "email@example.com",
  "senha": "suaSenha"
}
Exercícios
Criar Exercício

Método: POST
URL: /exercises
Corpo:
{
  "nome": "Nome do Exercício",
  "descricao": "Descrição do Exercício"
}
Listar Exercícios

Método: GET
URL: /exercises
Buscar Exercício por ID

Método: GET
URL: /exercises/{id}
Atualizar Exercício

Método: PUT
URL: /exercises/{id}
Corpo:
{
  "nome": "Nome Atualizado",
  "descricao": "Descrição Atualizada"
}
Deletar Exercício

Método: DELETE
URL: /exercises/{id}
Documentação da API
A documentação da API pode ser acessada através do Swagger em http://localhost:8080/swagger-ui.html.

Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

Licença
Este projeto é licenciado sob a MIT License.

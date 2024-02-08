# Backend GS3 Gestão Usuários

Uma aplicação simples de gerenciamento de usuários construída com Java, Spring Boot e H2 Database.
A execução completa do projeto depende do frontend (https://github.com/angthefirst/frontend-gs3-gestao-usuarios)

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- H2 Database
- Swagger (OpenAPI)
- Spring Security
- Flyway

## Descrição

O GS3 Gestão de Usuários uma aplicação web para gerenciar usuários. Ela oferece as principais operações para manter os usuários e utiliza o Spring Security para autenticação e autorização.

## Funcionalidades

- Autenticação com Spring Security + token JWT
- Busca de usuários paginadas
- Versionamento de banco e criação/população automática com o FlyWay
- Principais operações CRUDS para funcionamento da aplicação
- Testes unitários e de integração
- Proteção de endpoints por perfil (carregado no token)
- Gerar documentação da API com Swagger (OpenAPI) em http://localhost:8080/gs3-gestao-docs

## Primeiros Passos

Para começar com o GS3 Gestao Usuarios API, siga os seguintes passos:

1. Clone o repositório: `git clone https://github.com/angthefirst/backend-gs3-gestao-usuarios`
2. Instale o Java 17, caso ainda não tenha instalado.
3. Instale o Maven, caso ainda não tenha instalado.
4. Construa o projeto: `mvn build`
5. Execute e acesse a aplicação através do backend

## Documentação da API

A documentação da API é gerada utilizando o Swagger (OpenAPI). Uma vez que a aplicação está em execução, você pode acessar a documentação da API em 
http://localhost:8080/gs3-gestao-docs. Essa documentação fornece detalhes sobre os endpoints disponíveis, formatos de requisição/resposta e 
requisitos de autenticação.

## Configuração

A aplicação pode ser configurada utilizando o arquivo `application.properties`. Algumas configurações comuns incluem as configurações do banco de dados, porta do servidor e configurações de segurança. 
Você pode encontrar o arquivo de configuração no diretório `src/main/resources`.

Também há um arquivo de configuração para keys chamado messages.properties onde são armazenados as mensagens de exceções customizadas.

## Testes

A aplicação inclui testes unitários e testes de integração. Você pode rodar os testes com o comando 'mvn test'


## Considerações finais

O banco de dados pode ser acessado no localhost:8080/h2-console.

## Período do desafio técnico (backend e frontend)
06/02/2024 a 08/02/2024


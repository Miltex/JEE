# Aplicação CRUD JEE

Este documento fornece uma visão geral abrangente do projeto CRUD JEE, sua estrutura e instruções sobre como construí-lo e executá-lo.

## Introdução

Este projeto é uma aplicação completa de Criar, Ler, Atualizar e Excluir (CRUD) construída usando a pilha Java Enterprise Edition (JEE). Ele é projetado para ser executado em um ambiente conteinerizado usando Docker e Docker Compose, fornecendo uma configuração totalmente automatizada para o servidor de aplicação e o banco de dados.

## Tecnologias Utilizadas

- **Backend:** Java EE, JSF (JavaServer Faces), JPA (Java Persistence API)
- **Frontend:** PrimeFaces
- **Servidor de Aplicação:** Wildfly
- **Banco de Dados:** MariaDB
- **Ferramenta de Build:** Apache Maven
- **Conteinerização:** Docker, Docker Compose

## Estrutura do Projeto

Este projeto utiliza uma configuração padrão Maven multi-módulo para construir corretamente o arquivo final Enterprise Archive (`.ear`).

- **`CRUD-parent`:** O POM pai raiz que gerencia dependências e plugins para os módulos.
- **`CRUD-war`:** O módulo web contendo todo o código-fonte, páginas JSF e recursos web. Este módulo é empacotado como um arquivo `.war`.
- **`CRUD-ear`:** O módulo enterprise responsável por empacotar o arquivo `.ear` final. Ele inclui o módulo `CRUD-war` como seu componente web.

### Árvore de Diretórios

```
.
├── CRUD-ear
│   └── target
├── CRUD-war
│   ├── src
│   │   └── main
│   │       ├── java
│   │       ├── resources
│   │       └── webapp
│   └── target
└── wildfly
```

## Pré-requisitos

Antes de começar, certifique-se de ter o seguinte instalado em seu sistema:
- Java JDK 1.8 ou superior
- Apache Maven
- Docker
- Docker Compose

## Como Construir

O projeto pode ser construído a partir do diretório raiz usando um único comando Maven. Este comando irá compilar todos os módulos e criar o artefato `CRUD-ear.ear` final no diretório `CRUD-ear/target/`.

```bash
mvn clean install
```

## Como Executar

Todo o ambiente da aplicação é gerenciado pelo Docker Compose.

1.  **Construir e Iniciar os Serviços**

    No diretório raiz do projeto, execute o seguinte comando. Isso construirá a imagem Docker do Wildfly (que inclui sua aplicação) e iniciará todos os serviços necessários.

    ```bash
    docker compose up --build -d
    ```

2.  **Serviços**

    Isso iniciará três serviços:
    - **`mariadb`:** O contêiner do banco de dados MariaDB.
    - **`wildfly`:** O servidor de aplicação Wildfly com a aplicação `CRUD-ear.ear` implantada.
    - **`phpmyadmin`:** Uma interface web para gerenciar o banco de dados MariaDB.

## Acessando a Aplicação

Uma vez que os serviços estejam em execução, você pode acessar as diferentes partes do ambiente através do seu navegador web:

- **Aplicação:** [http://localhost:8080/CRUD/](http://localhost:8080/CRUD/)
- **Console de Administração do Wildfly:** [http://localhost:9990](http://localhost:9990)
  - **Nome de Usuário:** `admin`
  - **Senha:** `admin`
- **phpMyAdmin:** [http://localhost:8081](http://localhost:8081)
  - Use os detalhes de conexão do MariaDB de `docker-compose.yml` para fazer login (por exemplo, usuário `root`, senha `root_password`).

## Configuração Automatizada

O `wildfly/Dockerfile` é projetado para automatizar toda a configuração do servidor Wildfly. Quando a imagem Docker é construída, ela automaticamente:

1.  Cria um usuário de gerenciamento (`admin`/`admin`).
2.  Baixa o driver JDBC do MariaDB e o instala como um módulo do Wildfly.
3.  Configura o datasource do MariaDB (`MyDS`) no `standalone.xml` do Wildfly para que a aplicação possa se conectar ao banco de dados sem nenhuma configuração manual.
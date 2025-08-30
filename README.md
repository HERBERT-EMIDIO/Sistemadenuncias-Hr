# 🏥 Sistema de Denúncias

Bem-vindo ao repositório do projeto **Sistema de Denúncias**, uma API REST desenvolvida em Java com o framework Spring Boot. Este projeto foi criado com o objetivo de servir como backend para um sistema de gestão de denúncias na área de saúde.

## 🎯 Ideia de Negócio

A ideia central é fornecer uma API robusta e confiável para centralizar e gerenciar denúncias sobre problemas em hospitais, clínicas e outras instituições de saúde. A API permite que usuários reportem anonimamente ou não, incidentes como equipamentos com defeito, eventos adversos e outros tipos de irregularidades.

O sistema foi projetado para ser escalável e fácil de integrar com diferentes interfaces (web, mobile, etc.), garantindo que as informações críticas sejam coletadas de forma eficiente e segura.

## ✨ Funcionalidades

Até o momento, o projeto implementa as principais operações de um CRUD (Create, Read, Update, Delete) para o recurso de "Denúncia".

- **Criação de Denúncias** `(POST /api/denuncias)`: Permite criar novas denúncias, salvando os dados no banco de dados.
- **Listagem de Denúncias** `(GET /api/denuncias)`: Retorna uma lista com todas as denúncias registradas no sistema.
- **Busca por ID** `(GET /api/denuncias/{id})`: Permite buscar uma denúncia específica usando seu identificador único.
- **Atualização de Denúncias** `(PUT /api/denuncias/{id})`: Permite a atualização dos dados de uma denúncia já existente.

## 🛠️ Tecnologias e Ferramentas

- **Java 17**: Linguagem de programação.
- **Spring Boot 3.5.5**: Framework para desenvolvimento rápido de APIs.
- **Spring Data JPA**: Facilita a interação com o banco de dados.
- **Lombok**: Reduz a quantidade de código boilerplate (getters, setters, etc.).
- **H2 Database**: Banco de dados em memória, ideal para desenvolvimento e testes.
- **Maven**: Gerenciador de dependências.
- **Postman**: Ferramenta para testar os endpoints da API.
- **IntelliJ IDEA**: IDE utilizada para o desenvolvimento.

## 🚀 Como Rodar o Projeto

Para rodar este projeto em sua máquina local, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/sistemadenuncias.git](https://github.com/seu-usuario/sistemadenuncias.git)
    cd sistemadenuncias
    ```
2.  **Abra o projeto no IntelliJ IDEA:**
    - Vá em `File -> Open` e selecione a pasta `sistemadenuncias`.

3.  **Execute a aplicação:**
    - Abra o arquivo `SistemadenunciasApplication.java`.
    - Clique no botão "Run" (ícone de play verde) ao lado da classe principal.
    - O servidor será iniciado na porta **8081** e o banco de dados H2 estará disponível.

## 🧪 Testando a API com o Postman

Use o Postman para interagir com os endpoints da API.

### 1. Criar uma Denúncia

- **Método:** `POST`
- **URL:** `http://localhost:8081/api/denuncias`
- **Body:** `raw` e `JSON`
- **Exemplo de JSON:**
  ```json
  {
    "titulo": "Equipamento com defeito",
    "descricao": "O monitor de sinais vitais no leito 12 está falhando.",
    "anonima": false,
    "nomeDoador": "Dr. Herbert",
    "contatoDoador": "herbert@hr.com",
    "tipo": "EVENTO_ADVERSO",
    "classificacao": "MODERADA"
  }

# teste-snow

# Catálogo de Livros API

Bem-vindo à API do Catálogo de Livros! Esta API permite gerenciar usuários, livros, idiomas e autores. Utiliza o padrão OpenAPI 3.0 para definição de suas interfaces.

## Endpoints

### Usuários

- **Listar Todos os Usuários**
    - **GET** `/api/usuarios`
    - **Descrição**: Retorna uma lista de todos os usuários.
    - **Resposta**: Status `200 OK` com um array de objetos `Usuario`.

- **Adicionar Novo Usuário**
    - **POST** `/api/usuarios`
    - **Descrição**: Adiciona um novo usuário.
    - **Corpo da Requisição**: Objeto `Usuario` com o campo `nome`.
    - **Resposta**: Status `200 OK` com o objeto `Usuario` adicionado.

- **Buscar Usuário por ID**
    - **GET** `/api/usuarios/{id}`
    - **Descrição**: Retorna um usuário específico pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do usuário.
    - **Resposta**: Status `200 OK` com o objeto `Usuario` correspondente.

- **Atualizar Usuário**
    - **PUT** `/api/usuarios/{id}`
    - **Descrição**: Atualiza as informações de um usuário existente.
    - **Parâmetros**: `id` (no caminho) - ID do usuário.
    - **Corpo da Requisição**: Objeto `Usuario` com o campo `nome`.
    - **Resposta**: Status `200 OK` com o objeto `Usuario` atualizado.

- **Deletar Usuário**
    - **DELETE** `/api/usuarios/{id}`
    - **Descrição**: Remove um usuário pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do usuário.
    - **Resposta**: Status `200 OK`.

### Livros

- **Listar Todos os Livros**
    - **GET** `/api/livros`
    - **Descrição**: Retorna uma lista de todos os livros.
    - **Resposta**: Status `200 OK` com um array de objetos `Livro`.

- **Adicionar Novo Livro**
    - **POST** `/api/livros`
    - **Descrição**: Adiciona um novo livro.
    - **Corpo da Requisição**: Objeto `Livro` com os campos `titulo`, `usuario`, `idioma`, e `autor`.
    - **Resposta**: Status `200 OK` com o objeto `Livro` adicionado.

- **Atualizar Livro**
    - **PUT** `/api/livros/{id}`
    - **Descrição**: Atualiza as informações de um livro existente.
    - **Parâmetros**: `id` (no caminho) - ID do livro.
    - **Corpo da Requisição**: Objeto `Livro` com os campos `titulo`, `usuario`, `idioma`, e `autor`.
    - **Resposta**: Status `200 OK` com o objeto `Livro` atualizado.

- **Deletar Livro**
    - **DELETE** `/api/livros/{id}`
    - **Descrição**: Remove um livro pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do livro.
    - **Resposta**: Status `200 OK` com uma mensagem de confirmação.

- **Pesquisar Livros**
    - **GET** `/api/livros/search`
    - **Descrição**: Pesquisa livros por autor ou título.
    - **Parâmetros**: `autor` (opcional) - Nome do autor; `titulo` (opcional) - Título do livro.
    - **Resposta**: Status `200 OK` com um array de objetos `Livro`.

### Idiomas

- **Listar Todos os Idiomas**
    - **GET** `/api/idiomas`
    - **Descrição**: Retorna uma lista de todos os idiomas.
    - **Resposta**: Status `200 OK` com um array de objetos `Idioma`.

- **Adicionar Novo Idioma**
    - **POST** `/api/idiomas`
    - **Descrição**: Adiciona um novo idioma.
    - **Corpo da Requisição**: Objeto `Idioma` com os campos `nome` e `sigla`.
    - **Resposta**: Status `200 OK` com o objeto `Idioma` adicionado.

- **Buscar Idioma por ID**
    - **GET** `/api/idiomas/{id}`
    - **Descrição**: Retorna um idioma específico pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do idioma.
    - **Resposta**: Status `200 OK` com o objeto `Idioma` correspondente.

- **Atualizar Idioma**
    - **PUT** `/api/idiomas/{id}`
    - **Descrição**: Atualiza as informações de um idioma existente.
    - **Parâmetros**: `id` (no caminho) - ID do idioma.
    - **Corpo da Requisição**: Objeto `Idioma` com os campos `nome` e `sigla`.
    - **Resposta**: Status `200 OK` com o objeto `Idioma` atualizado.

- **Deletar Idioma**
    - **DELETE** `/api/idiomas/{id}`
    - **Descrição**: Remove um idioma pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do idioma.
    - **Resposta**: Status `200 OK`.

### Autores

- **Listar Todos os Autores**
    - **GET** `/api/autores`
    - **Descrição**: Retorna uma lista de todos os autores.
    - **Resposta**: Status `200 OK` com um array de objetos `Autor`.

- **Adicionar Novo Autor**
    - **POST** `/api/autores`
    - **Descrição**: Adiciona um novo autor.
    - **Corpo da Requisição**: Objeto `Autor` com o campo `nome`.
    - **Resposta**: Status `200 OK` com o objeto `Autor` adicionado.

- **Buscar Autor por ID**
    - **GET** `/api/autores/{id}`
    - **Descrição**: Retorna um autor específico pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do autor.
    - **Resposta**: Status `200 OK` com o objeto `Autor` correspondente.

- **Atualizar Autor**
    - **PUT** `/api/autores/{id}`
    - **Descrição**: Atualiza as informações de um autor existente.
    - **Parâmetros**: `id` (no caminho) - ID do autor.
    - **Corpo da Requisição**: Objeto `Autor` com o campo `nome`.
    - **Resposta**: Status `200 OK` com o objeto `Autor` atualizado.

- **Deletar Autor**
    - **DELETE** `/api/autores/{id}`
    - **Descrição**: Remove um autor pelo ID.
    - **Parâmetros**: `id` (no caminho) - ID do autor.
    - **Resposta**: Status `200 OK`.

## Modelos de Dados

### Usuario

```json
{
  "id": "integer",
  "nome": "string"
}
```

### Autor

```json
{
  "id": "integer",
  "nome": "string"
}
```

### Idioma

```json
{
"id": "integer",
"nome": "string",
"sigla": "string"
}
```

### Livro

```json
{
  "id": "integer",
  "titulo": "string",
  "usuario": {
    "id": "integer",
    "nome": "string"
  },
  "idioma": {
    "id": "integer",
    "nome": "string",
    "sigla": "string"
  },
  "autor": {
    "id": "integer",
    "nome": "string"
  }
}

```

### Execução

```shell
./mvnw spring-boot:run
```



### Acesso Swagger

```
http://localhost:8080/swagger-ui/index.html
```


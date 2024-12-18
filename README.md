# Documentação para Execução do Projeto com Docker Compose

## Visão Geral

Este projeto contém três serviços Docker que compõem a aplicação:
1. `catalogolivros`: Serviço de catálogo de livros.
2. `integracaoservico`: Serviço de integração de previsão do tempo.
3. `seguranca`: Serviço de segurança e autenticação.

Os serviços são conectados através de uma rede Docker chamada `app-network`.

## Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes pré-requisitos instalados em sua máquina:
- Docker
- Docker Compose

## Passo 2: Configurar Variáveis de Ambiente

Certifique-se de definir as seguintes variáveis de ambiente no arquivo `docker-compose.yml`:

- **Para o serviço `catalogolivros`**:
  - `PWD`: Senha de exemplo para o serviço.

- **Para o serviço `integracaoservico`**:
  - `PWD`: Senha de exemplo para o serviço.
  - `PWD_EMAIL`: Senha do email para envio de previsões.
  - `CHAVE_API`: Chave da API do serviço de previsão do tempo.

## Passo 3: Executar os Serviços com Docker Compose

Execute o comando abaixo para iniciar todos os serviços definidos no arquivo `docker-compose.yml`:

```bash
docker-compose up --build
```




# Teste 1 - Catálogo de Livros API

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
  - **Corpo da Requisição**: Objeto `UsuarioRequestDTO` com o campo `nome`.
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
  - **Corpo da Requisição**: Objeto `LivroRequestDTO` com os campos `titulo`, `usuario`, `idioma`, e `autor`.
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
  - **Corpo da Requisição**: Objeto `IdiomaRequestDTO` com os campos `nome` e `sigla`.
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
  - **Corpo da Requisição**: Objeto `AutorRequestDTO` com o campo `nome`.
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

### UsuarioRequestDTO

```json
{
  "nome": "string"
}

```

### LivroRequestDTO

```json
{
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

### IdiomaRequestDTO

```json
{
  "nome": "string",
  "sigla": "string"
}

```

### AutorRequestDTO

```json
{
  "nome": "string"
}

```



### Adicionar Variável de Ambiente `PWD`

### Linux

Para adicionar a variável de ambiente `PWD` no Linux, siga estes passos:

```shell
  export PWD=password
```


## Execução do Projeto

Para executar o projeto de forma autônoma:

1**Compile e Execute a Aplicação**
    ```bash
    ./mvnw spring-boot:run
    ```

2**Acesse a Aplicação**
    - A aplicação estará disponível em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).






# Teste 2 - Previsão do Tempo e Gerenciamento de Usuários

Este projeto é uma aplicação Spring Boot que oferece um serviço de previsão do tempo e gerenciamento de usuários. A aplicação consome dados de uma API externa de previsão do tempo, envia emails com a previsão diária para uma lista de usuários e fornece um CRUD para gerenciar usuários.

## Funcionalidades

- **Previsão do Tempo**: Obtém dados da API WeatherAPI e envia emails diários com a previsão do tempo para os usuários cadastrados.
- **Gerenciamento de Usuários**: Permite criar, ler, atualizar e deletar usuários.

## Configuração

1. Clone o repositório:
    ```sh
    git clone git@github.com:eltongoncalves/teste-snow.git
    cd teste-snow/2_integracaoServico
    ```

2. Configure as variáveis de ambiente no `application.properties`:
    ```properties
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    weather.api.key=SEU_WEATHER_API_KEY
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=SEU_EMAIL@gmail.com
    spring.mail.password=SUA_SENHA
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```

3. Execute a aplicação:
    ```sh
    ./mvnw spring-boot:run
    ```

## Endpoints da API

### Usuario Controller

- **Buscar Usuário por ID**
    ```http
    GET /usuarios/{id}
    ```
  Respostas:
    - 200 OK
        ```json
        {
          "id": 1,
          "nome": "Nome do Usuário",
          "email": "usuario@dominio.com",
          "localizacao": "Localização"
        }
        ```

- **Atualizar Usuário**
    ```http
    PUT /usuarios/{id}
    ```
  Corpo da Requisição:
    ```json
    {
      "nome": "Nome Atualizado",
      "email": "email@atualizado.com",
      "localizacao": "Localização Atualizada"
    }
    ```
  Respostas:
    - 200 OK
        ```json
        {
          "id": 1,
          "nome": "Nome Atualizado",
          "email": "email@atualizado.com",
          "localizacao": "Localização Atualizada"
        }
        ```

- **Deletar Usuário**
    ```http
    DELETE /usuarios/{id}
    ```
  Respostas:
    - 200 OK

- **Listar Todos os Usuários**
    ```http
    GET /usuarios
    ```
  Respostas:
    - 200 OK
        ```json
        [
          {
            "id": 1,
            "nome": "Nome do Usuário",
            "email": "usuario@dominio.com",
            "localizacao": "Localização"
          },
          {
            "id": 2,
            "nome": "Outro Usuário",
            "email": "outro@dominio.com",
            "localizacao": "Outra Localização"
          }
        ]
        ```

- **Salvar Novo Usuário**
    ```http
    POST /usuarios
    ```
  Corpo da Requisição:
    ```json
    {
      "nome": "Novo Usuário",
      "email": "novo@dominio.com",
      "localizacao": "Nova Localização"
    }
    ```
  Respostas:
    - 200 OK
        ```json
        {
          "id": 3,
          "nome": "Novo Usuário",
          "email": "novo@dominio.com",
          "localizacao": "Nova Localização"
        }
        ```

### Previsao Tempo Controller

- **Obter Previsão do Tempo**
    ```http
    GET /previsao-tempo
    ```
  Parâmetros:
    - `localizacao`: Localização para obter a previsão.
      Respostas:
    - 200 OK
        ```json
        {
          "location": {
            "name": "Nome da Localização",
            "region": "Região",
            "country": "País",
            "lat": 0.0,
            "lon": 0.0,
            "tz_id": "Fuso Horário",
            "localtime": "2024-07-23 00:00"
          },
          "current": {
            "maxtemp_c": 30.0,
            "maxtemp_f": 86.0,
            "mintemp_c": 20.0,
            "mintemp_f": 68.0,
            "avgtemp_c": 25.0,
            "avgtemp_f": 77.0,
            "maxwind_mph": 10.0,
            "maxwind_kph": 16.0,
            "totalprecip_mm": 5.0,
            "totalprecip_in": 0.2,
            "totalsnow_cm": 0.0,
            "avgvis_km": 10.0,
            "avgvis_miles": 6.2,
            "avghumidity": 70.0,
            "daily_will_it_rain": 1,
            "daily_chance_of_rain": 80.0,
            "daily_will_it_snow": 0,
            "daily_chance_of_snow": 0.0,
            "condition": {
              "text": "Ensolarado",
              "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png"
            },
            "uv": 5.0
          },
          "forecast": {
            "forecastday": [
              {
                "date": "2024-07-23",
                "day": {
                  "maxtemp_c": 30.0,
                  "maxtemp_f": 86.0,
                  "mintemp_c": 20.0,
                  "mintemp_f": 68.0,
                  "avgtemp_c": 25.0,
                  "avgtemp_f": 77.0,
                  "maxwind_mph": 10.0,
                  "maxwind_kph": 16.0,
                  "totalprecip_mm": 5.0,
                  "totalprecip_in": 0.2,
                  "totalsnow_cm": 0.0,
                  "avgvis_km": 10.0,
                  "avgvis_miles": 6.2,
                  "avghumidity": 70.0,
                  "daily_will_it_rain": 1,
                  "daily_chance_of_rain": 80.0,
                  "daily_will_it_snow": 0,
                  "daily_chance_of_snow": 0.0,
                  "condition": {
                    "text": "Ensolarado",
                    "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png"
                  },
                  "uv": 5.0
                }
              }
            ]
          }
        }
        ```

- **Envio de Email com Previsão do Tempo**
    ```http
    GET /previsao-tempo/envio
    ```
  Respostas:
    - 200 OK
        ```json
        {
          "destinatarios": ["usuario@dominio.com"],
          "conteudo": "Previsão do Tempo"
        }
        ```

### HTML enviado

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Previsão do Tempo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        header {
            background: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }

        header h1 {
            margin: 0;
        }

        section {
            background: #fff;
            margin: 20px 0;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .current-weather, .forecast {
            margin-bottom: 20px;
        }

        .current-weather img, .forecast-day img {
            width: 64px;
            height: 64px;
        }

        .location p, .current-info p, .forecast-day p {
            margin: 10px 0;
        }

        footer {
            background: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Previsão do Tempo</h1>
    </header>
    <section class="current-weather">
        <h2>Condição Atual</h2>
        <div class="location">
            <h3>Localização:</h3>
            <p id="location-name">Belém</p>
            <p id="location-region">Para, Brasilien</p>
        </div>
        <div class="current-info">
            <img id="weather-icon" src="//cdn.weatherapi.com/weather/64x64/night/143.png" alt="Condição Atual">
            <p id="condition-text">Mist</p>
            <p id="temperature">23.2°C</p>
            <p id="feels-like">Sensação térmica: 25.6°C</p>
            <p id="humidity">Umidade: 95%</p>
            <p id="wind">Vento: 5.1 mph</p>
            <p id="pressure">Pressão: 1014 mb</p>
        </div>
    </section>
    <section class="forecast">
        <h2>Previsão para o Dia</h2>
        <div class="forecast-day">
            <h3>Data: 2024-07-23</h3>
            <img id="forecast-icon" src="//cdn.weatherapi.com/weather/64x64/day/176.png" alt="Condição do Dia">
            <p id="forecast-condition">Patchy rain nearby</p>
            <p id="max-temp">Máx: 33.4°C</p>
            <p id="min-temp">Mín: 23.2°C</p>
            <p id="average-temp">Média: 26.3°C</p>
            <p id="precipitation">Precipitação: 4.64 mm</p>
            <p id="uv-index">Índice UV: 11.0</p>
        </div>
    </section>
    <footer>
        <p>&copy; 2024 Previsão do Tempo</p>
    </footer>
</div>
</body>
</html>

```

## Esquemas

### Usuario

```json
{
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int64"
    },
    "nome": {
      "type": "string"
    },
    "email": {
      "type": "string"
    },
    "localizacao": {
      "type": "string"
    }
  }
}
```



### Adicionar Variável de Ambiente `PWD`

### Linux

Para adicionar a variável de ambiente `PWD` no Linux, siga estes passos:

```shell
  export PWD=password
```


### Execução

## Execução do Projeto

Para executar o projeto de forma autônoma:

1**Compile e Execute a Aplicação**
    ```bash
    ./mvnw spring-boot:run
    ```

2**Acesse a Aplicação**
    - A aplicação estará disponível em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).




# Teste 3: Mapeamento de Objetos e Segurança
# Documentação da API: Gerenciamento de Usuários e Roles

## Descrição

Esta API permite gerenciar usuários e roles (papéis) com autenticação e autorização. A aplicação inclui endpoints para:

- Registro de novos usuários
- Login de usuários existentes
- Consulta de todos os usuários
- Consulta dos dados do usuário logado

## Servidor

- **URL Base**: [http://localhost:8080](http://localhost:8080)

## Endpoints

### 1. Registrar Novo Usuário

- **Método**: `POST`
- **URL**: `/auth/signup`
- **Descrição**: Registra um novo usuário na aplicação.
- **Corpo da Requisição**:
    ```json
    {
        "id": 1,
        "username": "string",
        "password": "string",
        "roles": ["string"]
    }
    ```
- **Resposta de Sucesso**:
    - **Código**: `200 OK`
    - **Corpo**:
        ```json
        {
            "id": 1,
            "username": "string",
            "password": "string",
            "roles": ["string"]
        }
        ```

### 2. Login de Usuário

- **Método**: `POST`
- **URL**: `/auth/signin`
- **Descrição**: Realiza o login de um usuário e retorna um token JWT.
- **Corpo da Requisição**:
    ```json
    {
        "username": "string",
        "password": "string"
    }
    ```
- **Resposta de Sucesso**:
    - **Código**: `200 OK`
    - **Corpo**:
        ```json
        "string"  // Token JWT
        ```

### 3. Consultar Todos os Usuários

- **Método**: `GET`
- **URL**: `/users`
- **Descrição**: Consulta todos os usuários registrados na aplicação.
- **Resposta de Sucesso**:
    - **Código**: `200 OK`
    - **Corpo**:
        ```json
        [
            {
                "username": "string",
                "roles": ["string"]
            }
        ]
        ```

### 4. Consultar Dados do Usuário Logado

- **Método**: `GET`
- **URL**: `/users/me`
- **Descrição**: Consulta os dados do usuário atualmente logado.
- **Resposta de Sucesso**:
    - **Código**: `200 OK`
    - **Corpo**:
        ```json
        {
            "username": "string",
            "roles": ["string"]
        }
        ```

## Componentes

### Schemas

#### Usuario

- **Tipo**: `object`
- **Propriedades**:
    - `id`: `integer` (formato `int64`)
    - `username`: `string`
    - `password`: `string`
    - `roles`: `array` de `string`

#### AuthRequestDTO

- **Tipo**: `object`
- **Propriedades**:
    - `username`: `string`
    - `password`: `string`

#### UsuarioDTO

- **Tipo**: `object`
- **Propriedades**:
    - `username`: `string`
    - `roles`: `array` de `string`

## Segurança

- **Autenticação**: Implementada utilizando JWT.
- **Proteção contra CSRF**: Configurada para prevenir ataques Cross-Site Request Forgery.
- **Proteção contra XSS**: Configurada para prevenir ataques Cross-Site Scripting.
- **Proteção contra SQL Injection**: Implementada usando práticas seguras de acesso a banco de dados.

## Execução do Projeto

Para executar o projeto de forma autônoma:

1**Compile e Execute a Aplicação**
    ```bash
    ./mvnw spring-boot:run
    ```

2**Acesse a Aplicação**
    - A aplicação estará disponível em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).


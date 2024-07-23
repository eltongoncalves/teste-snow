# Previsão do Tempo e Gerenciamento de Usuários

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

```shell
./mvnw spring-boot:run
```



### Acesso Swagger

```
http://localhost:8080/swagger-ui/index.html
```


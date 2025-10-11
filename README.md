# TRABALHO SUB TECH CHALLENGE CURSO SOAT – PÓSTECH

## Diagramas

### arquitetura

![arquitetura](docs/arquitetura.png)

### Banco de dados

![bd.png](docs/banco-de-dados.png)

## estrutura do projeto
```
├── config/   
├── domain/   
│   ├── model/   
│   ├── exception/   
│   ├── repository/  
│   └── service/   
├── application/   
│   ├── service/   
├── infrastructure/   
│   ├── persistence/   
│   │   ├── entity/   
│   │   ├── repository/   
│   │   └── adapter/   
│   ├── web/   
│   │   ├── controller/   
│   │   ├── dto/
│   │   ├── mapper/   
│   │   └── events/   
 
```

## configurações
### executar localmente utilizando docker compose na raiz deste projeto

1. gerar arquivo src/main/resources/application-dev.properties com o seguinte conteúdo:
   ```properties
    # auth
    okta.oauth2.client-id=
    okta.oauth2.client-secret=
    # DB
    spring.datasource.url=jdbc:postgresql://localhost:5432/veiculos_db
    spring.datasource.username=admin
    spring.datasource.password=123456
    # servidor smtp - email
    spring.mail.username=
    spring.mail.password=
    # mercado pago
    mercadopago.access-token=
    mercadopago.base-url=https://api.mercadopago.com
   ```

obs.: habilitar perfil dev para sobrescrever as configurações em application.properties, incluindo a propriedade **spring.profiles.active=dev** ou no script de execução - ./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

2. criar novo arquivo .env que será utilizado pelo docker-compose com o seguinte conteúdo:

```
DB_USERNAME=admin
DB_PASSWORD=123456
DB_NAME=veiculos_db
AUTH0_CLIENT_ID=
AUTH0_CLIENT_SECRET=
EMAIL_SENDER_FROM=
EMAIL_SENDER_PASSWORD=
MERCADO_LIVRE_KEY=
MERCADO_LIVRE_PASS=
```

3. criar rede do docker com comando abaixo:
> docker network create localnet

4. executar docker compose:
> docker compose up -d

5. acessar endereço do frontend: http://localhost:8080

### API 
- [swagger](http://localhost:8080/swagger-ui/index.html)

## webhook do mercado pago com ngrok
- instalar
```
 curl -sSL https://ngrok-agent.s3.amazonaws.com/ngrok.asc \
  | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null \
  && echo "deb https://ngrok-agent.s3.amazonaws.com bookworm main" \
  | sudo tee /etc/apt/sources.list.d/ngrok.list \
  && sudo apt update \
  && sudo apt install ngrok
```
> ngrok config add-authtoken meu_token
> ngrok http 8080

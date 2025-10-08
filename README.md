# TRABALHO SUB TECH CHALLENGE CURSO SOAT – PÓSTECH

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
   ```
   
obs.: habilitar perfil dev para sobrescrever as configurações em application.properties, incluindo a propriedade **spring.profiles.active=dev** ou no script de execução - ./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

2. criar novo arquivo .env com o seguinte conteúdo:

```
DB_USERNAME=admin
DB_PASSWORD=123456
DB_NAME=veiculos_db
```

3. criar rede do docker com comando abaixo:
   > docker network create localnet
   
4. executar docker compose:
   > docker compose up -d

5. acessar endereço do frontend: http://localhost:8080


### estrutura do projeto
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

### banco de dados

![bd.png](docs/bd.png)

### API 
- [swagger](http://localhost:8080/swagger-ui/index.html)

### emails
backstagefood6@gmail.com

### LINKS
- auth0 - https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
- auth0 com google - https://auth0.com/docs/quickstart/webapp/java-spring-boot/interactive
- mercado pago - https://www.mercadopago.com.br/developers/pt/docs/checkout-pro/create-application
- mercado pago devs - https://www.mercadopago.com.br/developers/pt
- gmail senha de app - https://myaccount.google.com/apppasswords

## mercado pago - cartoes de teste
Mastercard 5031 4332 1540 6351 123  11/30
Visa       4235 6477 2802 5682 123  11/30
Amex       3753 651535 56885   1234 11/30
Elo Debito 5067 7667 8388 8311 123  11/30


CPF - 12345678909
NOME - APRO 

## usuarios de teste

Buyer Test User - 2908928085 - TESTUSER3367302021471928748  iev9t9d37S  - 928085
Seller Test User - 2909305387 -  TESTUSER4451468298938495915 Gs6VGh5qoy


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
ngrok config add-authtoken meu_token

ngrok http 8080



curl -i -X GET "https://api.mercadopago.com/v1/payments/1341559103?access_token=APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387"


APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387"



TESTUSER4820435565320605542  - uRCbkzeso0



curl -X GET -H "Authorization: Bearer APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387" https://api.mercadopago.com/v1/payments/1341571987
curl -X GET -H "Authorization: Bearer APP_USR-362396827355051-100618-3e590259dc26349f87ab36fd943d7916-2909305387" https://api.mercadopago.com/v1/payments


curl -X POST https://api.mercadopago.com/users/test_user -H "Authorization: Bearer TEST-5663058348663981-100718-8e9be69bae8ee69a6afa214425648b56-2906463580" -H "Content-Type: application/json" -d '{"site_id":"MLB"}'

{"id":2713619422,"email":"test_user_510369497@testuser.com","nickname":"TESTUSER510369497","site_status":"active","password":"VYfwJKgTWl"

TESTUSER3367302021471928748 2908928085

test_user_3367302021471928748@testuser.com   iev9t9d37S
 
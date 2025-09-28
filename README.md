# TRABALHO SUB TECH CHALLENGE CURSO SOAT – PÓSTECH

## configurações
### executar localmente utilizando docker compose na raiz deste projeto

1. gerar arquivo src/main/resources/application-dev.properties com o seguinte conteúdo:
   ```properties
    # auth
    auth.client-id=AbWDEBmr3kAI1eaxBC4PLsGIYXhFtGFD
    auth.client-secret=NwC9nvjve13HAHbB-SdCa6eOH6v56SBu76FlP-7ZBIgc-7ntXz-sVoGT9EBh7LNt
    
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
│   ├── dto/   
│   ├── service/   
│   └── port/   
├── infrastructure/   
│   ├── persistence/   
│   │   ├── entity/   
│   │   ├── repository/   
│   │   └── adapter/   
│   ├── web/   
│   │   ├── controller/   
│   │   ├── dto/
│   │   ├── mapper/      
│   │   └── config/   
│   │   └── events/   
│   └── external/   
└── shared/   
└── util/   
```

### banco de dados

![bd.png](docs/bd.png)

### API 
- [swagger](http://localhost:8080/swagger-ui/index.html)

### LINKS
- auth0 - https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
- auth0 com google - https://auth0.com/docs/quickstart/webapp/java-spring-boot/interactive

### PENDENCIAS
- [ ] integrar auth0 com autenticação de terceiros, com google ou github
- [ ] obter dados do usuario após o login e gravar na tabela
```
accessToken	"eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIiwiaXNzIjoiaHR0cHM6Ly9oZmFudGluLnVzLmF1dGgwLmNvbS8ifQ..nxLo29vMnYWLrFnI.BvSum0k665Qz0zLF3gpatb4biIYssHrO3ey61FOn2CSMQ7GwBd8JJpoJxOU68Bzc-5FbLU9De5EuaHPZXUl9dMQ6hN-JaHuNG6B_KYDEUeIepb4rR7-VECMU_ibL8oOA0_ZP4X08hKRcxq-jJ4V2_glmyRayxgCw7VYCOpQ_r0jL46t9UTUVKYLTmRwmzS_Hm8AprTrS97gnssyG1NNX28HvsKRclzx8C7wmRPWjsn2jNyGwEBBoAZ6b_2_asTzhN7ep7THilXDkH5n1_BWsROsTgRNFoDEcy30ie4WWn2cyr1BqSPVxUClcpFnX.kyhT-shx50EtS-a_qLRkIA"
userClaims	
sub	"google-oauth2|118310884889032345464"
email_verified	true
iss	"https://hfantin.us.auth0.com/"
given_name	"backstagefood"
nonce	"yplNKGlmlDKS_E_ccEaTCWOXXpnHwkASnsT8r7PQHcw"
picture	"https://lh3.googleusercontent.com/a/ACg8ocKXg7djLEbL3JyOBqAMIOw1GAx9N2o0KJLdmMc0uKgbyXrerA=s96-c"
sid	"39WKJ7yiq94C5lUXZJhEgxmuaUD_XZlP"
aud	
0	"AbWDEBmr3kAI1eaxBC4PLsGIYXhFtGFD"
updated_at	"2025-09-28T13:49:46.338Z"
nickname	"backstagefood6"
name	"backstagefood"
exp	"2025-09-28T23:49:51Z"
iat	"2025-09-28T13:49:51Z"
email	"backstagefood6@gmail.com"
```

    id SERIAL PRIMARY KEY,
    auth_id VARCHAR(255) NOT NULL UNIQUE, -- ID do serviço externo de autenticação
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NULL,
    address TEXT NULL,
    type VARCHAR(20) DEFAULT 'BUYER' CHECK (type IN ('BUYER', 'SELLER', 'BOTH')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
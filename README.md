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

2. criar rede do docker com comando abaixo:
   > docker network create localnet
   
3. executar docker compose:
   > docker compose up -d

4. acessar endereço do frontend: http://localhost:8080


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
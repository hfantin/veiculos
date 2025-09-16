# TRABALHO SUB TECH CHALLENGE CURSO SOAT – PÓSTECH

## configurações
### executar localmente utilizando docker compose na raiz deste projeto

1. gerar arquivo .env com o seguinte conteúdo:
   ```env
    DB_HOST=localhost
    DB_PORT=5432
    DB_NAME=veiculos_db
    DB_USERNAME=
    DB_PASSWORD=
   ```

2. criar rede do docker com comando abaixo:
   > docker network create localnet
   
3. executar docker compose:
   > docker compose up -d

4. acessar endereço do frontend: http://localhost:8080



### estrutura do projeto
```
├── config/   
├── domain/   
│   ├── entity/   
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
│   │   └── config/   
│   └── external/   
└── shared/   
└── util/   
```
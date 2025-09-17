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
│   │   ├── mapper/      
│   │   └── config/   
│   └── external/   
└── shared/   
└── util/   
```


### endpoints 
- http://localhost:8080/swagger-ui/index.html
POST /api/brands - Criar nova marca

GET /api/brands/{id} - Buscar marca por ID

GET /api/brands/name/{name} - Buscar marca por nome

GET /api/brands - Listar todas as marcas

GET /api/brands?ordered=true - Listar marcas ordenadas por nome

PUT /api/brands/{id} - Atualizar marca

DELETE /api/brands/{id} - Deletar marca

GET /api/brands/count - Contar total de marcas

GET /api/brands/exists/{name} - Verificar se marca existe
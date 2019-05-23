## Account Service

### Executando Postgres localmente

```bash
docker run --rm --name=account-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```

### Executando servico localmente

```bash
mvn clean compile spring-boot:run
```

### Endpoints do Serviço

Verbo | URI | Descrição
-----|----|---
GET | /v1/accounts | Obtem uma lista de todas as 10 contas 
GET | /v1/accounts/{id} | Obtem os dados da conta com ID especificado
GET | /v1/accounts/{id}/customer | Obtem os dados do cliente associado a conta
POST | /v1/accounts | Cria uma nova conta
DELETE | /v1/accounts/{id} | Remove uma determinada conta pelo ID

### Estrutura da Tabela

Tabela: *accounts*

Campo|Tipo
-----|----
account_id | VARCHAR
customer_id | VARCHAR
type | Int4
active | boolean
created_at| timestamp

```
PS. Este serviço tem relação com Customer Service
```
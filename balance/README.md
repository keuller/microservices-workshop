## Balance Service

### Executando Postgres Localmente

```bash
docker run --rm --name=balance-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```

### Executando servico localmente

```bash
mvn clean compile exec:exec
```

### Acessando o Serviço

```bash
http://localhost:8282/v1/balances
```

### Endpoints do Serviço

Verbo | URI | Descrição
-----|----|---
GET | /v1/balances/{id}/account | Obtem o saldo da conta de acordo com ID especificado
POST| /v1/balances|Gera um novo evento de atualização de saldo

### Estrutura da Tabela

Tabela: *balances*

Campo|Tipo
-----|----
balance_id | VARCHAR
customer_id | VARCHAR
account_id | VARCHAR
value | double
last_transaction | VARCHAR
last_update| timestamp
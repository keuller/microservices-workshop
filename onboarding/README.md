## Onboarding Service

### Executando Postgres localmente

```bash
docker run --rm --name=wallet-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```

### Executando servico localmente

```bash
mvn clean compile spring-boot:run
```

### Acessando o Serviço

```bash
http://localhost:7000/v1/onboarding
```

### Endpoints do Serviço

Verbo | URI | Descrição
-----|----|---
POST | /v1/onboarding | Processa o onboarding de um novo cliente.

### Estrutura da Tabela

Tabela: *balances*

Campo|Tipo
-----|----
balance_id | VARCHAR
account_id | VARCHAR
customer_id | VARCHAR
lasttrasaction|VARCHAR
type | Int4
value | double
lastupdate| timestamp

## Onboarding Service

O serviço de Onboarding realiza composição dos demais serviços: *Customer*, *Account* e *Balance*. Certifique-se que todos os serviços estejam funcionais.

### Executando serviço localmente

```bash
mvn clean compile spring-boot:run
```

### Acessando o Serviço

```bash
http://localhost:7000/v1/onboarding
```

### Endpoint do Serviço

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

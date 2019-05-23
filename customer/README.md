## Customer Service

### Executando Postgres localmente

```bash
docker run --rm --name=customer-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```

### Executando servico localmente

```bash
mvn clean compile spring-boot:run
```

### Acessando Serviço

```bash
http://localhost:8080/v1/customers
```

### Endpoints do Serviço

Verbo|URI|Descrição
-----|---|---------
GET|/v1/customers|Obtem a lista de clientes.
GET|/v1/customers/{id}|Obtem os dados do cliente com ID especificado
GET|/v1/customers/find?email=[email]|Obtem os dados do cliente através do **email**
POST|/v1/customers|Cria um novo cliente.
DELETE|v1/customers/{id}|Remove um determinado cliente pelo ID.

### Estrutura da Tabela

Tabela: *customers*

Campo|Tipo
-----|----
customer_id | VARCHAR
name | VARCHAR
email | VARCHAR
gender|int4
birth_day|timestamp
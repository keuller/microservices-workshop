## Customer Service

### Executando Postgres localmente

```bash
docker run --rm --name=customer-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```

### Executando servico localmente

```bash
mvn clean compile spring-boot:run
```

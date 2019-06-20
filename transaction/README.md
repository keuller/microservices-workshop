## Transaction Service

### Executando Postgres localmente

```bash
docker run --rm --name=wallet-db -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

### Executando servico localmente

```bash
mvn clean compile spring-boot:run
```

### Acessando Serviço

```bash
http://localhost:8080/v1/transactions
```

### Endpoints do Serviço

Verbo|URI|Descrição
-----|---|---------
**GET**|/v1/transactions/{id}|Obtem os dados de uma transação financeira com ID especificado.
**POST**|/v1/transactions|Realizar uma transação financeira entre contas.

### Estrutura da Tabela

Tabela: *transactions*

Campo|Tipo
-----|----
transaction_id|varchar
account_id|varchar
type|int4
operation|int4
amount|decimal
created_at|timestamp

### Load Testing

Para executar o teste de carga no microserviço, usamos a ferramenta [locust.io](https://locust.io). Para executar basta instalar localmente o Locust (precisa do Python/PIP instalado localmente).

```bash
$ locust -f locustfile.py
```

O servidor será iniciado localmente na port 8089. Basta apontar o navegador para `http://localhost:8089` e executar seus testes.

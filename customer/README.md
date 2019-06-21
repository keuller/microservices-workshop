## Customer Service

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
**GET**|/v1/customers|Obtem a lista de clientes.
**GET**|/v1/customers/{id}|Obtem os dados do cliente com ID especificado
**GET**|/v1/customers/find?email=[email]|Obtem os dados do cliente através do **email**
**POST**|/v1/customers|Cria um novo cliente.
**PUT**|/v1/customers/{id}|Atualiza os dados do cliente pelo ID especificado.
**DELETE**|v1/customers/{id}|Remove um determinado cliente pelo ID.

### Estrutura da Tabela

Tabela: *customers*

Campo|Tipo
-----|----
customer_id | VARCHAR
name | VARCHAR
email | VARCHAR
gender|int4
birth_day|timestamp

### Acessando Console H2

```
http://localhost:8080/h2-console
```

### Load Testing

Para executar o teste de carga no microserviço, usamos a ferramenta [locust.io](https://locust.io). Para executar basta instalar localmente o Locust (precisa do Python/PIP instalado localmente).

```bash
$ locust -f locustfile.py
```

O servidor será iniciado localmente na port 8089. Basta apontar o navegador para `http://localhost:8089` e executar seus testes.

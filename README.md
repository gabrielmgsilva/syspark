```markdown
# Projeto de Gerenciamento de Garagens e Receitas

Este projeto é uma API desenvolvida em **Java** utilizando o framework **Spring Boot** para gerenciar garagens, vagas e receitas. A aplicação inclui endpoints documentados com **Swagger** e utiliza **Flyway** para controle de migrações no banco de dados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.5**
- **Maven**
- **Flyway** (para migrações de banco de dados)
- **Spring Data JPA** (para persistência de dados)
- **MySQL** (banco de dados relacional)
- **Springdoc OpenAPI** (para documentação com Swagger)

## Funcionalidades

- **Gerenciamento de Garagens**: Configuração e listagem de garagens.
- **Gerenciamento de Vagas**: Controle de status das vagas (livre/ocupada).
- **Receitas**: Consulta de receitas por setor e data.
- **Documentação da API**: Disponível via Swagger UI.

## Endpoints Principais

### Garagens
- **GET** `/garage`: Lista todas as garagens configuradas.

### Vagas
- **POST** `/spot-status`: Retorna o status de uma vaga específica.

### Placas
- **POST** `/plate-status`: Retorna o status de uma placa específica.

### Receitas
- **GET** `/revenue`: Retorna a receita total de um setor em uma data específica.

### Webhook
- **POST** `/webhook`: Registra um evento relacionado à garagem.

## Configuração do Ambiente

### Pré-requisitos
- **Java 17** ou superior
- **Maven**
- **MySQL**

### Configuração do Banco de Dados
1. Crie um banco de dados no MySQL.
2. Configure as credenciais no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=none
   spring.flyway.enabled=true
   ```

3. Execute as migrações com o Flyway:
   ```bash
   mvn flyway:migrate
   ```

### Executando o Projeto
1. Compile e inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

2. Acesse a documentação Swagger em:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## Estrutura do Projeto

- `src/main/java/com/example/demo/controllers`: Contém os controladores da API.
- `src/main/java/com/example/demo/services`: Contém a lógica de negócios.
- `src/main/java/com/example/demo/domain/models`: Contém as entidades do banco de dados.
- `src/main/resources/db/migration`: Scripts de migração do Flyway.

## Scripts de Migração

Os scripts de migração estão localizados em `src/main/resources/db/migration`. Exemplo de criação de tabelas:

```sql
CREATE TABLE tbl_garagem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    setor VARCHAR(2) NOT NULL UNIQUE,
    preco_base DECIMAL(10, 2) NOT NULL,
    capacidade_maxima INT NOT NULL,
    hora_abertura VARCHAR(5) NOT NULL,
    hora_fechamento VARCHAR(5) NOT NULL,
    duracao_limite INT NOT NULL,
    dt_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dt_ultima_modificacao DATETIME DEFAULT NULL
);
```

## Contribuição

1. Faça um fork do repositório.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das alterações:
   ```bash
   git commit -m "Minha nova feature"
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a licença **MIT**.
```

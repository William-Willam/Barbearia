# 💈 Sistema de Agendamento para Barbearia

Sistema web para gerenciamento de uma barbearia, permitindo o cadastro de clientes, serviços e agendamentos, com validação automática para evitar conflitos de horário.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-05122A)

## 📋 Sobre o projeto

Esse sistema foi desenvolvido para simular o dia a dia de uma barbearia: cadastro de clientes, definição dos serviços oferecidos (com duração e preço) e o agendamento de horários — tudo isso com uma regra de negócio central: **o sistema não permite que dois agendamentos ocupem o mesmo horário**, considerando a duração de cada serviço.

## ✨ Funcionalidades

- **Clientes:** cadastro, listagem, edição e exclusão
- **Serviços:** cadastro, listagem, edição e exclusão, com duração (em minutos) e preço
- **Agendamentos:** cadastro, listagem, edição e exclusão
  - Validação de conflito de horário: verifica sobreposição de intervalos entre agendamentos ativos, considerando a duração de cada serviço
  - Validação de data/hora: não permite agendar em uma data que já passou
  - Status de agendamento: `AGENDADO`, `CONCLUIDO`, `CANCELADO`
- Interface web responsiva com Thymeleaf, sem necessidade de frontend separado

## 🛠️ Tecnologias utilizadas

- **Java 17+**
- **Spring Boot** — Web, Data JPA, Validation
- **Thymeleaf** — motor de templates para as páginas HTML
- **MySQL** — banco de dados relacional
- **Hibernate** — ORM, com geração automática do schema
- **Maven** — gerenciamento de dependências

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas:

```
Controller → Service → Repository → MySQL
```

- **Controller:** recebe as requisições HTTP e direciona para as views ou redireciona
- **Service:** concentra as regras de negócio (como a validação de conflito de horário)
- **Repository:** interfaces do Spring Data JPA para acesso ao banco
- **Model:** entidades JPA que representam as tabelas do banco

A regra de validação de horário fica exclusivamente na camada Service, mantendo o Controller responsável apenas pelo fluxo da aplicação.

## 📂 Estrutura do projeto

```
src/main/java/com/barbearia/agendamento/
├── controller/
│   ├── ClienteController.java
│   ├── ServicoController.java
│   ├── AgendamentoController.java
│   └── HomeController.java
├── service/
│   ├── ClienteService.java
│   ├── ServicoService.java
│   └── AgendamentoService.java
├── repository/
│   ├── ClienteRepository.java
│   ├── ServicoRepository.java
│   └── AgendamentoRepository.java
└── model/
    ├── Cliente.java
    ├── Servico.java
    ├── Agendamento.java
    └── StatusAgendamento.java

src/main/resources/
├── templates/
│   ├── index.html
│   ├── fragments/nav.html
│   ├── clientes/
│   ├── servicos/
│   └── agendamentos/
├── static/css/style.css
└── application.properties
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL em execução localmente

### Passo a passo

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/agendamento-barbearia.git
   cd agendamento-barbearia
   ```

2. Crie o banco de dados no MySQL:
   ```sql
   CREATE DATABASE agendamento_barbearia;
   ```

3. Configure as credenciais do banco em `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/agendamento_barbearia
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

4. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse no navegador:
   ```
   http://localhost:8080
   ```

As tabelas do banco são criadas automaticamente pelo Hibernate na primeira execução (`spring.jpa.hibernate.ddl-auto=update`).

## 🔒 Regra de negócio: conflito de horário

Cada agendamento define um intervalo de tempo (início = data/hora escolhida; fim = início + duração do serviço). Antes de salvar um novo agendamento, o sistema verifica se esse intervalo se sobrepõe a algum outro agendamento ativo (ignorando os cancelados), usando a lógica clássica de sobreposição de intervalos:

```
inícioA < fimB  E  inícioB < fimA
```

Se houver sobreposição, o sistema bloqueia o salvamento e exibe uma mensagem informando o horário já ocupado.

## 📌 Possíveis melhorias futuras

- Autenticação e controle de acesso (login para funcionários)
- Agenda visual em formato de calendário
- Notificações por e-mail/SMS para lembrar o cliente do agendamento
- Relatórios de faturamento por período

## 📄 Licença

Este projeto foi desenvolvido para fins de estudo e aprendizado.

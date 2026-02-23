# 🚀 Smart Factory Manager - Otimização de Produção

Este sistema é uma solução **Full-Stack** desenvolvida para a gestão e otimização de lucros em ambientes fabris. O foco principal é um algoritmo de inteligência que calcula a melhor combinação de produtos a serem fabricados com base na disponibilidade limitada de insumos em estoque.

## 👨‍💻 Desenvolvedor
* **Nome:** Marcelo Ferreira Bispo
* **Perfil:** Desenvolvedor Full-Stack com experiência em Web (PHP, JS, Node.js) e Mobile (Flutter, React Native, Java/Kotlin).

## 🛠️ Tecnologias Utilizadas

### **Back-end (Java)**
* **Framework:** Spring Boot 3
* **Linguagem:** Java 17
* **Banco de Dados:** H2 Database (In-memory para testes rápidos)
* **ORM:** Spring Data JPA com Hibernate
* **Gerenciador de Dependências:** Maven

### **Front-end (JavaScript)**
* **Framework:** Vue.js 3 (Composition API)
* **Comunicação:** Axios
* **Ferramenta de Build:** Vite
* **Estilização:** CSS3 Moderno com foco em UX/UI

## 🧠 Algoritmo de Otimização
O sistema utiliza um algoritmo de **Backtracking** para realizar uma busca exaustiva de combinações de produção.

* **Objetivo:** Maximizar o valor total de venda ($V_{total}$).
* **Restrição:** $\sum (Qtd_{necessaria} \times Qtd_{produzida}) \leq Estoque_{disponivel}$ para cada insumo.

## ⚙️ Como Executar o Projeto

### **1. Pré-requisitos**
* JDK 17 ou superior configurado no `JAVA_HOME`
* Node.js instalado para o front-end

### **2. Rodando o Back-end**
```bash
cd backend
./mvnw spring-boot:run

## 🚀 Próximos Passos (Melhorias Futuras)

Embora o sistema atenda a todos os requisitos e regras de negócio propostas, a arquitetura foi desenhada pensando em escalabilidade. As seguintes evoluções estão mapeadas para o futuro da aplicação:

* **Testes Unitários no Front-end:** Implementação de suítes de testes para os componentes Vue.js utilizando **Vitest** e Vue Test Utils.
* **Internacionalização (i18n):** Adição de suporte a múltiplos idiomas na interface utilizando a biblioteca **Vue I18n**, facilitando a adoção em fábricas multinacionais.
* **Segurança e Autenticação:** Implementação de Spring Security e tokens JWT no back-end para separar os perfis de acesso (ex: Administrador vs. Operador de Máquina).
* **Evolução do Algoritmo:** Para cenários com milhares de variáveis de insumos e produtos, migrar a lógica de *Backtracking* para modelos de **Programação Linear** (utilizando bibliotecas como Google OR-Tools ou Apache Commons Math) visando máxima performance em tempo real.
* **Dockerização:** Criação de `Dockerfile` e `docker-compose.yml` para orquestrar os contêineres do banco de dados, da API Spring Boot e do front-end Vue.js, garantindo um ambiente de deploy padronizado.

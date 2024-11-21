# SparkLight API ⚡

Este sistema foi criado para facilitar o controle das despesas de energia elétrica, oferecendo recursos para calcular o consumo e os custos de aparelhos elétricos residenciais.

---

## Índice 📋
- [Desenvolvedores](#desenvolvedores)
- [Recursos](#recursos)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Endpoints](#endpoints)
  - [Histórico](#histórico)
  - [Aparelho](#aparelho)
  - [Usuário](#usuário)
  - [Item](#item)
  - [Exemplos de JSON](#exemplos-de-json)

---

## Recursos

-  **Estimativa de Consumo (kWh):**
-  Realiza o cálculo do consumo mensal em quilowatt-hora (kWh) com base nas especificações técnicas e no tempo de uso dos aparelhos.

- **Cálculo de Custo (R$):**
- Gera uma estimativa de gastos em reais (R$) considerando a tarifa aplicada.

- **Registro de Histórico Mensal:**
- Produz registros mensais com informações detalhadas sobre o consumo e o custo total dos aparelhos cadastrados.

## Desenvolvedores 🚀

- [Sabrina Couto](https://github.com/sabrinacouto) - Backend Developer
- [Juliana Mo.](https://github.com/julianamo93) - Modelagem de Dados e Cloud
- [Kevin Nobre](https://github.com/KevinNobre) - Backend e Front

## Como Rodar o Projeto 🖥️

### Passos
1. Clone o repositório:
    ```bash
    git clone https://github.com/sabrinacouto/sparklight-api.git
    cd sparklight-api
    ```
2. Configure as variáveis de ambiente no arquivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA
    ```

Agora o servidor estará disponível em `http://localhost:8080`. 🎉

---

## Endpoints

### Histórico 🕒

| Método | URL                    | Descrição              |
|--------|------------------------|------------------------|
| GET    | `/historicos`           | Listar Históricos      |
| POST   | `/historicos`           | Criar Histórico        |
| PUT    | `/historicos/{id}`      | Atualizar Histórico    |
| DELETE | `/historicos/{id}`      | Deletar Histórico      |
|GET     | `/historicos/{usuarioId}/calculo`| Consultar histórico de consumo de todos os aparelhos do usuário |

---

### Aparelho ⚙️

| Método | URL                         | Descrição               |
|--------|-----------------------------|-------------------------|
| GET    | `/aparelhos`                 | Listar Aparelhos        |
| POST   | `/aparelhos`                 | Criar Aparelho          |
| PUT    | `/aparelhos/{id}`            | Atualizar Aparelho      |
| DELETE | `/aparelhos/{id}`            | Deletar Aparelho        |

---

### Usuário 👤

| Método | URL                         | Descrição               |
|--------|-----------------------------|-------------------------|
| GET    | `/usuarios`                  | Listar Usuários         |
| POST   | `/usuarios`                  | Criar Usuário           |
| PUT    | `/usuarios/{id}`             | Atualizar Usuário       |
| DELETE | `/usuarios/{id}`             | Deletar Usuário         |

---

### Item 👤

| Método | URL                         | Descrição               |
|--------|-----------------------------|-------------------------|
| GET    | `/itens`                  | Listar Itens        |
| POST   | `/itens`                  | Criar Item          |
| PUT    | `/itens/{id}`             | Atualizar Item       |
| DELETE | `/itens/{id}`             | Deletar Item         |

---

## Exemplos de JSON para o POST e PUT 📄

### Histórico
```json
  {
    "ano": 2024,
    "mes": 12,
    "consumoMes": 1.5,
    "custoMes": 36.15,
    "usuarioId": 1
}
```
#### 🗓️ **ano**  
-  Ano ao qual o registro está relacionado.  
-  `2024` representa o ano de 2024.

#### 📅 **mes**   
- Mês correspondente ao registro, no formato numérico.  
- `1` para janeiro, `2` para fevereiro, ..., `12` para dezembro.  
- `12` indica o mês de dezembro.

#### ⚡ **consumoMes**  
-  Consumo total de energia elétrica no mês, medido em quilowatt-hora (kWh).  
- `1.5` significa um consumo de 1,5 kWh no mês.

#### 💰 **custoMes**  
- Custo total de energia elétrica no mês, calculado com base no consumo e na tarifa por kWh.  
- `36.15` indica que o custo total do consumo foi de R$ 36,15.

#### 👤 **usuarioId**  
- Identificador único do usuário ao qual o registro pertence.  
- `1` representa o ID do usuário associado ao registro.

### Aparelho
```json
{
    "nome": "Televisão",
    "potencia": 150.0,
    "tempo": 24.0,
    "periodo": "Manhã",
    "usuarioId": 1,
}
```
#### 📌 **nome**  
-  `2024` Nome do aparelho que está sendo cadastrado.
-  Pode ser qualquer aparelho doméstico, como `Geladeira` , `Computador` , etc.)

#### ⚡ **potencia**   
- Potência do aparelho em watts (W). Representa a energia consumida pelo aparelho por hora de uso.  
- **Exemplo:** : `150.0` (150 watts por hora). 

#### ⏳ **tempo**  
- Número de horas que o aparelho é utilizado diariamente. 
- **Exemplo:** : `24.0` (Aparelho usado 24 horas por dia).

#### ⛅ **periodo**  
- Indica o período predominante de uso do aparelho (como `Manhã`, `Tarde`, ou `Noite`). 
-  **Exemplo:** : `Manhã` (o aparelho é usado predominantemente pela manhã).

### Usuário
```json
 {
  "nome": "Fulano",
  "sobrenome": "de Tal",
  "email": "usuarioexemplo@example.com",
  "cidade": "São Paulo",
  "estado": "SP",
  "idade": 20,
  "genero": "Masculino",
  "cep": "01001-000",
	"senha":"senhasegura123"
}
```
O json do `Usuário` segue o padrão de um cadastro comum.

### Item
```json
{
  "quantidade": 3,
  "aparelhoId": 2,
  "historicoId": 3
}

```
#### ⛅ **quantidade**  
- Indica a quantidade de unidades do aparelho associadas a este item.
-  **Exemplo:** : `3` (O item está associado a três unidades do aparelho indicado pelo aparelhoId).

#### ⚡ **aparelhoid**   
- Identificador único do aparelho ao qual o item está relacionado.

#### ⏳ **historicoid**  
- Identificador único do histórico ao qual o item está associado.
---

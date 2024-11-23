# SparkLight API ⚡

Este sistema foi criado para facilitar o controle das despesas de energia elétrica, oferecendo recursos para calcular o consumo e os custos de aparelhos elétricos residenciais. Ele possibilita o registro de dispositivos e apresenta estimativas detalhadas com base no tempo de uso e nas especificações técnicas dos aparelhos.  Projetado para ser intuitivo e acessível, o sistema promove hábitos conscientes, contribuindo para um futuro mais sustentável.

---

## Índice 📋
- [Recursos](#recursos)
- [Desenvolvedores](#desenvolvedores)

- [Imagens Explicativas](#imagens-explicativas)
  - [Telas da aplicação](#telas-da-aplicacao)
  - [Diagrama de classes](#diagrama-de-classes)
  - [Macro do Projeto](#macro)

- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Endpoints](#endpoints)
  - [Histórico](#histórico)
  - [Aparelho](#aparelho)
  - [Usuário](#usuário)
  - [Item](#item)
  - [Exemplos de JSON](#exemplos-de-json)
- [Imagens Explicativas](#imagens-explicativas)
  - [Telas da aplicação](#telas-da-aplicacao)
  - [Diagrama de classes](#diagrama-de-classes)
  - [Diagrama Macro](#diagrama-macro)
- [Vídeo de explicação do funcionamento do software](#video-de-explicação-do-software)
- [Vídeo Pitch](#video-pitch)

---

## Recursos

-  **Estimativa de Consumo (kWh):**
-  Realiza o cálculo do consumo mensal em quilowatt-hora (kWh) com base nas especificações técnicas e no tempo de uso dos aparelhos.

- **Cálculo de Custo (R$):**
- Gera uma estimativa de gastos em reais (R$) considerando a tarifa aplicada.

- **Registro de Histórico Mensal:**
- Produz registros mensais com informações detalhadas sobre o consumo e o custo total dos aparelhos cadastrados.

## Desenvolvedores 🚀

- [Sabrina Couto](https://github.com/sabrinacouto) - RM552728 - BackEnd Java e QA
- [Juliana Mo.](https://github.com/julianamo93) - RM554113 - Cloud e Dados
- [Kevin Nobre](https://github.com/KevinNobre) - RM552590 - Front-end e BackEnd .Net

## Imagens Explicativas


### Telas

![image](https://media.discordapp.net/attachments/1297737020248031284/1309577833558839316/Login.png?ex=674216e6&is=6740c566&hm=c12a734c61db18d8bbbe591adefce8c9d7eefa9a4ae00bc9e09c80318a56d412&=&format=webp&quality=lossless&width=323&height=662)
![image](https://media.discordapp.net/attachments/1297737020248031284/1309577764692692992/Register.png?ex=674216d5&is=6740c555&hm=5a8fab95de03b1da49d5b0707594f11d46d0dd2a4678517d203e9735aea21854&=&format=webp&quality=lossless&width=247&height=662)
![image](https://media.discordapp.net/attachments/1297737020248031284/1309577872448684113/Home.png?ex=674216ef&is=6740c56f&hm=2642dbf0f18274f765051f087af0df8c87ba8b9b51ad516099c9e442be9dd5ce&=&format=webp&quality=lossless&width=323&height=662)
![image](https://media.discordapp.net/attachments/1297737020248031284/1309577726096572517/Dicas_1.png?ex=674216cc&is=6740c54c&hm=e160a6b5531bb6d2bbfb376c8be6ea5bda906518d258fe0aa30b182157a6b790&=&format=webp&quality=lossless&width=323&height=662)
![image](https://media.discordapp.net/attachments/1297737020248031284/1309579031452192778/Consumo.png?ex=67421803&is=6740c683&hm=2ba383a8afd594ddf4c6dcc6c372c7ba0cf44f1519de828e86288a3a08500863&=&format=webp&quality=lossless&width=223&height=661)
![image](https://media.discordapp.net/attachments/1297737020248031284/1309579031217442896/Consumo_-_Aparelhos.png?ex=67421803&is=6740c683&hm=11125e57b19b9658155f39dc522da8942f41b957a4b997c09bcddd1e2dfbdbd2&=&format=webp&quality=lossless&width=323&height=662)

### Diagrama de Classes

### Macro do projeto

![diagramJava](https://media.discordapp.net/attachments/1297737020248031284/1309576636168929422/Diagrama_sem_nome.drawio_1.png?ex=674215c8&is=6740c448&hm=b190aebc56cb6f1306a9f8417b9415d9b22e584ad03f899c9d9f73e10283ed0c&=&format=webp&quality=lossless&width=838&height=438)
  
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
3. Configurar o Projeto no IntelliJ IDEA
<ul>
  <li>Selecione a opção "Open" e navegue até o diretório do projeto SparkLight clonado.</li>
  <li>Em Project Structure garanta que o SDK esteja para o Java 17.</li>
  <li>Verifique no IntelliJ se a aba do Gradle está presente na barra lateral.</li>
  <li>Caso o projeto não seja automaticamente reconhecido como Gradle, abra o arquivo build.gradle e aceite a importação do Gradle quando o IntelliJ perguntar.</li>
</ul>

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

### Item 👩‍💻

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

## Deploy WebApp

![image](https://github.com/user-attachments/assets/98992142-267c-4d53-a4cf-373d71cb2a44)

Link do Deploy: https://sparklight-webapp-cqhjebazedhagehy.brazilsouth-01.azurewebsites.net/


## Vídeo de explicação do software
https://youtu.be/kVD9L8fzxC4

## Vídeo Pitch

![ThumbSpark](https://github.com/user-attachments/assets/ccb87980-04ac-41c2-81f5-36f0a4618cc6)


https://www.youtube.com/watch?v=0dKvL1Tfhl8



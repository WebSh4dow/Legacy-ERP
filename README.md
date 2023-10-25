# Projeto ERP

![Diagrama da Arquitetura ERP](https://github.com/WebSh4dow/Legacy-ERP/blob/main/DIAGRAM-ERP.png)

Este é um projeto de sistema ERP (Enterprise Resource Planning) que utiliza Docker, Spring Boot, Spring Validation Spring Data JPA, HATEOAS, Spring Security e MySQL. O objetivo é criar um sistema flexível e seguro para a gestão de recursos empresariais.

## Conteúdo

1. [Arquitetura](#REST-API)
2. [Docker](#docker)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Recursos](#recursos)
5 -[Clientes](#clientes)
6. [Usando HATEOS](#link-com-json)
7. [Modelo de Banco de Dados](#modelo-de-banco-de-dados)

## Arquitetura

A arquitetura deste sistema é baseada em microserviços, onde cada serviço é responsável por uma área específica da empresa, como vendas, estoque, recursos humanos, etc. A comunicação entre esses serviços é feita por meio de APIs RESTful, seguindo o princípio HATEOAS.

## Docker

O uso de Docker neste projeto permite a fácil implantação e escalabilidade dos serviços. Cada microserviço é empacotado em contêineres Docker independentes, tornando o desenvolvimento e a implantação mais simples.

## Tecnologias Utilizadas

Este projeto utiliza as seguintes tecnologias:

- Docker: Para empacotar e implantar os serviços em contêineres.
- Nginx: Como um proxy reverso para encaminhar solicitações aos serviços.
- Spring Boot: Para desenvolver os microserviços.
- Spring Data JPA: Para mapeamento objeto-relacional e acesso ao banco de dados MySQL.
- HATEOAS: Para implementar Hypermedia as the Engine of Application State (HATEOAS) e criar links nos recursos.
- Spring Security: Para proteger os endpoints e garantir a segurança do sistema.
- MySQL: Como banco de dados para armazenar os dados da empresa.

## Alguns recursos que o projeto tem 

### Clientes

#### Recurso: Lista de Clientes
- **Método**: GET
- **URL**: `https://{host}/api/clientes`
- **Descrição**: Retorna a lista de todos os clientes registrados na empresa.

#### Recurso: Cliente por ID
- **Método**: GET
- **URL**: `https://{host}/api/clientes/{cliente_id}`
- **Descrição**: Retorna as informações detalhadas de um cliente específico com base em seu ID.

#### Recurso: Contratos de Clientes
- **Método**: GET
- **URL**: `https://{host}/api/clientes/{cliente_id}/contratos`
- **Descrição**: Retorna a lista de contratos associados a um cliente específico.

## Exemplo de Link com JSON

Aqui está um exemplo de como os links HATEOAS são incorporados no JSON de resposta:

```json
{
  "id": 123,
  "nome": "Exemplo Ltda.",
  "email": "exemplo@example.com",
  "_links": {
    "self": {
      "href": "https://{host}.com/api/clientes/123"
    },
    "contratos": {
      "href": "https://{host}.com/api/clientes/123/contratos/123"
    }
  }
}

# Chamadas de Endpoints

Para interagir com os endpoints da API deste projeto ERP, você pode seguir as seguintes diretrizes:

### Especificações

A API utiliza especificações para permitir consultas complexas. Para filtrar os resultados, você pode adicionar parâmetros à URL da seguinte forma:

#### Filtragem por Nome e Email

- **URL**: `https://{host}.com/api/clientes?nome=Exemplo%20Ltda.&email=exemplo@example.com`
- **Método**: GET
- **Descrição**: Retorna a lista de clientes com o nome "Exemplo Ltda." e o email "exemplo@example.com".

### Paginação

Para lidar com grandes conjuntos de dados, a API suporta paginação. Você pode especificar o número da página e o tamanho da página:

- **URL**: `https://{host}/api/clientes?page=1&size=10`
- **Método**: GET
- **Descrição**: Retorna a primeira página com 10 clientes. 

### Exemplo de Código JSON

Aqui está um exemplo de código JSON representando uma resposta com paginação:

```json
{
  "content": [
    {
      "cliente_id": 123,
      "nome": "Exemplo Ltda.",
      "email": "exemplo@example.com",
      "_links": {
        "self": {
          "href": "https://{host}/api/clientes/123"
        }
      }
    },
    {
      "cliente_id": 124,
      "nome": "Empresa XYZ",
      "email": "xyz@example.com",
      "_links": {
        "self": {
          "href": "https://{host}/api/clientes/124"
        }
      }
    },
    // Outros clientes...
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "pageNumber": 1,
    "pageSize": 10,
    "offset": 10,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 3,
  "totalElements": 30,
  "last": false,
  "first": false,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 10,
  "size": 10,
  "number": 1,
  "empty": false
}





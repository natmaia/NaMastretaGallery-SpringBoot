# NaMastretaArt

Uma API para o projeto de galeria de artes conceitual

## Endpoints

- Login
  - [Efetuar login](#efetuar-login)
  
 - Curadoria
   - [Criar novo curadoria login](#criar-novo-curadoria-login)
   - [Listar Obra do curador](#listar-curadoria-obra)
   - [Editar Perfil](#editar-curadoria-perfil)
   - [Excluir login](#excluir-curadoria-perfil)
  
 - Obra
   - [Cadastrar Obra](#criar-nova-obra)
   - [Listar Obra](#listar-obra)
   - [Editar](#editar-obra)
   - [Excluir Obra](#excluir-obra)
   
 - Artista
   - [Cadastrar Artista](#criar-nova-obra)
   - [Listar Artista](#listar-obra)
   - [Excluir Artista](#excluir-obra)

--------------------------------------------------------------------------------------------------------------------------------------------------------

### Criar novo curadoria login

`POST` /namastreta/api/cadastro-curadoria

**Campos da requisição**
| Campo | Tipo | Obrigatório | Descrição 
|-------|------|:-----------:|---
imagem | img | não | Campo responsavel por armazenar a foto de perfil do curador (usuario)
nome | String | sim | Campo responsavel por armazenar o nome do curador (usuario)
curador_id | sim | Campo de armazenamento do id do perfil curador
email| String | sim | Campo responsabvel por armazenar o e-mail do curador (usuario)
senha | String | sim | Campo responsavel por armazenar a senha do usuario

**Corpo da requisição - exemplo**

```JSON
{
  "nome": "nathalia maia",
  "curador_id":"1",
  "email":"nathalia@maia.com.br",
  "senha":"maia1234"
    
}

```
**Códigos de resposta**

| código | descrição
|-|-
| 201 | usuario criado com sucesso
| 400 | campos inválidos
| 500 | Erro inesperado contactar o suporte

#### Efetuar Login

`POST` /namastreta/api/efetuar-login

**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
email|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario

**Corpo da requisição - exemplo**

```JSON
{
    "email":"nathalia@maia.com.br",
    "senha":"maia1234"
}
```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | usuario/senha autenticados
| 401 | usuario/senha invalidos

--------------------------------------------------------------------------------------------------------------------------------------------------------

### Criar nova obra

`POST` /namastreta/api/criar-nova-obra

**Campos da requisição**
| Campo | Tipo | Obrigatório | Descrição 
|-------|------|:-----------:|---
curador_id | int | sim | Campo responsavel por armazenar o id do Curador responsavel pela obra.
imagem | img | não | Campo responsavel por armazenar a foto de perfil do curador (usuario)
titulo_obra | String | sim | Campo responsavel por armazenar o titulo da obra
tamanho | String | sim | Campo responsavel por armazenar o tamanho da obra
artista_id | int | sim | Campo responsavel por armazenar o id do artista criador da obra.
descricao | String | sim | Campo responsavel por armazenar a historia da obra
valor | decimal | sim | Campo responsavel por armazenar o titulo da obra

**Corpo da requisição - exemplo**

```JSON
{ 
  "curador_id":1
  "imagem":
    {
      "sourceUrl": "\"hemlock_6.jpg\"",
      "status": "OK",
      }
  "titulo_obra":"A Falação"
  "tamanho": "100x200"
  "artista_id": 1
  "descricao":"Pintura a oleo bla bla bla"
  "valor":350.00
  }
  
  

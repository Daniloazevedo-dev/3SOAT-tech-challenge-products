# language: pt

Funcionalidade: API Produto

   Cenário: Cadastrar um novo produto
     Quando submeter um novo produto
     Então o produto é registrado com sucesso
     E retornado o produto registrado

  Cenário: Alterar um produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a alteração de um produto
    Então o produto é atualizado com sucesso
    E retornado o produto atualizado

  Cenário: Listar Produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a busca de um produto
    Então o produto é retornado com sucesso

  Cenário: Excluir um produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a exclusão de um produto
    Então o produto é removido com sucesso


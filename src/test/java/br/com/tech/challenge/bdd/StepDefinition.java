package br.com.tech.challenge.bdd;

import br.com.tech.challenge.domain.dto.ProdutoDTO;
import br.com.tech.challenge.domain.entidades.Categoria;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.CREATED;

public class StepDefinition {

    private Response response;
    private ProdutoDTO produtoResposta;
    private final String ENDPOINT_API_PRODUTOS = "http://localhost:8080/produtos";

    @Quando("submeter um novo produto")
    public ProdutoDTO submeter_um_novo_produto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(setProdutoDTO())
                .when()
                .post(ENDPOINT_API_PRODUTOS);
        return response.then()
                .extract().as(ProdutoDTO.class);
    }

    @Então("o produto é registrado com sucesso")
    public void o_produto_é_registrado_com_sucesso() {
        response.then()
                .statusCode(CREATED.value());
    }

    @E("retornado o produto registrado")
    public void retornado_o_produto_registrado() {
        response.body().as(ProdutoDTO.class);
    }

    @Dado("que um produto já foi cadastrado")
    public void que_um_produto_já_foi_cadastrado() {
        produtoResposta = submeter_um_novo_produto();
    }

    @Quando("requisitar a alteração de um produto")
    public void requisitar_a_alteração_de_um_produto() {

        ProdutoDTO produtoAtualizado = setProdutoDTO();
        produtoAtualizado.setDescricao("Coca Cola 2.0");

        response = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(produtoAtualizado)
                .when()
                    .patch(ENDPOINT_API_PRODUTOS + "/" + produtoResposta.getId());
    }

    @Então("o produto é atualizado com sucesso")
    public void o_produto_é_atualizado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @E("retornado o produto atualizado")
    public void retornado_o_produto_atualizado() {
        response.body().as(ProdutoDTO.class);
    }

    @Quando("requisitar a busca de um produto")
    public void requisitar_a_busca_de_um_produto() {
        response = given()
                        .param("id", produtoResposta.getId())
                        .param("categoria", produtoResposta.getCategoria().getId())
                        .param("pagina", 0)
                        .param("tamanho", 10)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .get(ENDPOINT_API_PRODUTOS);
    }

    @Então("o produto é retornado com sucesso")
    public void o_produto_é_retornado_com_sucesso() {
        response
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", greaterThan(0))
                .body("content[0].id", notNullValue())
                .body("content[0].descricao", notNullValue());
        ;
    }

    @Quando("requisitar a exclusão de um produto")
    public void requisitar_a_exclusão_de_um_produto() {
        response = when()
                        .delete(ENDPOINT_API_PRODUTOS + "/" + produtoResposta.getId());
    }

    @Então("o produto é removido com sucesso")
    public void o_produto_é_removido_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private ProdutoDTO setProdutoDTO() {
        return ProdutoDTO.builder()
                .id(2L)
                .descricao("Coca Cola")
                .valorUnitario(BigDecimal.valueOf(5.00))
                .categoria(setCategoria())
                .build();
    }

    private Categoria setCategoria() {
        return Categoria.builder()
                .id(2L)
                .descricao("Bebida")
                .build();
    }
}

package br.com.tech.challenge.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Operation(description = "Endpoint que retorna o status da aplicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aplicação está OK."),
    })
    @GetMapping
    public String status() {
        return "STATUS: OK";
    }
}

package io.github.rafaelframos2016.tielerama.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
    private String descricao;
    private String data;
    private String valor;
    private String idCliente;
}

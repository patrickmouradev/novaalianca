package com.novaalianca.api.bancointer.models.boleto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PdfBoleto {

    private byte[] pdf;
}

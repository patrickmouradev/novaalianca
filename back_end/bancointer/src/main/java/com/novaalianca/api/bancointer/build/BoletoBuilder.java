//package com.novaalianca.api.bancointer.build;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.patrickmoura.apiboleto.entities.Usuario;
//import com.patrickmoura.apiboleto.enuns.TipoDesconto;
//import com.patrickmoura.apiboleto.enuns.TipoMora;
//import com.patrickmoura.apiboleto.enuns.TipoMulta;
//import com.patrickmoura.apiboleto.enuns.TipoPessoa;
//import com.patrickmoura.apiboleto.models.boleto.BoletoDTO;
//import com.patrickmoura.apiboleto.repositories.ParametrosSistemaRepository;
//import com.patrickmoura.apiboleto.util.Feriados;
//
//@Component
//public class BoletoBuilder {
//
//	@Autowired
//	Feriados feriados;
//
//	@Autowired
//	ParametrosSistemaRepository parametrosSistemaRepository;
//
//	public BoletoDTO carregaDadosEmissao(Usuario usuario) throws ParseException {
//		BoletoDTO boleto = new BoletoDTO();
//		DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
//		String valorCOndominio1 =(parametrosSistemaRepository.findValorParametro("VALOR_CONDOMINIO_" + LocalDate.now().format(formatterYear)));
//		Float valorCondominio = Float.parseFloat(valorCOndominio1);
//		Float valorMulta = Float.parseFloat(parametrosSistemaRepository.findValorParametro("VALOR_MULTA"));
//		Float valorMora = Float.parseFloat(parametrosSistemaRepository.findValorParametro("VALOR_MORA"));
//		int diaVencimento = Integer
//				.parseInt(parametrosSistemaRepository.findValorParametro("DIA_DE_VENCIMENTO_BOLETO"));
//
//		/* Preenchendo Dados Pagador */
//		boleto.getPagador().setCpfCnpj(usuario.getCpfUsuario());
//		boleto.getPagador().setNome(usuario.getNomeUsuario());
//		boleto.getPagador().setEmail(usuario.getEmailUsuario());
//		boleto.getPagador().setTelefone(usuario.getTelefoneCelular() == null ? "" : usuario.getTelefoneCelular());
//		boleto.getPagador().setCep(usuario.getEndereco().getCep());
//		boleto.getPagador().setNumero(usuario.getEndereco().getNumero());
//		boleto.getPagador().setComplemento(usuario.getEndereco().getComplemento());
//		boleto.getPagador().setBairro(usuario.getEndereco().getBairro());
//		boleto.getPagador().setCidade(usuario.getEndereco().getCidade());
//		boleto.getPagador().setUf(usuario.getEndereco().getUf());
//		boleto.getPagador().setEndereco(usuario.getEndereco().getEndereco());
//		boleto.getPagador().setDdd(usuario.getTelefoneCelularDDD() == null ? "" : usuario.getTelefoneCelularDDD());
//		boleto.getPagador().setTipoPessoa(TipoPessoa.FISICA.toString());
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		boleto.setDataEmissao(LocalDate.now().format(formatter));
//
//		DateTimeFormatter formatterSeuNumer = DateTimeFormatter.ofPattern("MMyyyy");
//		boleto.setSeuNumero(LocalDate.now().format(formatterSeuNumer) + usuario.getUnidade().getNumeroUnidade());
//		boleto.setDataLimite("TRINTA");
//
//		boleto.setDataVencimento(this.verificaFeriado(diaVencimento).toString());
//
//		/* Preenchendo Mensagens */
//		// boleto.getMensagem().setLinha1("JUROS(MORA) - TAXA MENSAL - 1 DIA apos DO
//		// VENCIMENTO - PERCENTUAL 2%");
//		// boleto.getMensagem().setLinha2("MULTA - VALOR FIXO - 1 DIA após DO VENCIMENTO
//		// - VALOR 5,40");
//		boleto.getMensagem().setLinha1("TAXA CONDOMINAL REFERENTE AO MÊS " + LocalDate.now().format(formatterSeuNumer));
//
//		/* Preenchendo Desconto 1 */
//		boleto.getDesconto1().setCodigoDesconto(TipoDesconto.NAOTEMDESCONTO.toString());
//		boleto.getDesconto1().setTaxa(0.0);
//		boleto.getDesconto1().setValor(0.0);
//		boleto.getDesconto1().setData("");
//
//		/* Preenchendo Desconto 2 */
//		boleto.getDesconto2().setCodigoDesconto(TipoDesconto.NAOTEMDESCONTO.toString());
//		boleto.getDesconto2().setTaxa(0.0);
//		boleto.getDesconto2().setValor(0.0);
//		boleto.getDesconto2().setData("");
//
//		/* Preenchendo Desconto 3 */
//		boleto.getDesconto3().setCodigoDesconto(TipoDesconto.NAOTEMDESCONTO.toString());
//		boleto.getDesconto3().setTaxa(0.0);
//		boleto.getDesconto3().setValor(0.0);
//		boleto.getDesconto3().setData("");
//
//		boleto.setValorNominal(valorCondominio);
//		boleto.setValorAbatimento(0F);
//
//		/* Preenchendo Multa */
//		boleto.getMulta().setCodigoMulta(TipoMulta.VALORFIXO.toString());
//		boleto.getMulta().setData(this.verificaFeriado(diaVencimento).plusDays(1).format(formatter));
//		boleto.getMulta().setValor(valorMulta);
//		boleto.getMulta().setTaxa(0F);
//
//		/* Preenchendo Mora */
//		boleto.getMora().setCodigoMora(TipoMora.TAXAMENSAL.toString());
//		boleto.getMora().setData(this.verificaFeriado(diaVencimento).plusDays(1).format(formatter));
//		boleto.getMora().setTaxa(valorMora);
//		boleto.getMora().setValor(0F);
//
//		boleto.setCnpjCPFBeneficiario("07890271000109");
//		boleto.setNumDiasAgenda("TRINTA");
//
//		return boleto;
//	}
//
//
//
//
//	public LocalDate verificaFeriado(Integer diaVencimento) throws ParseException {
//
//		LocalDate dataVencimento = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
//				diaVencimento);
//		while (feriados.verificaFeriado(dataVencimento) || dataVencimento.getDayOfWeek().getValue() > 5) {
//			dataVencimento = dataVencimento.plusDays(1);
//		}
//		return dataVencimento;
//
//	}
//
//}

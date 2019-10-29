package br.com.cefetrj.sc.dominio;

import br.com.cefetrj.sc.exception.EntidadeInconsistenteException;
import org.apache.commons.lang3.StringUtils;

public class BancaExaminadora implements EntidadeVerificavel
{


	private String nome;

	public BancaExaminadora()
	{
	}

	public BancaExaminadora(String nome)
	{
		super();
		this.nome = nome;
		validarEstado();
	}

	public String getNome()
	{
		return nome;
	}

	public void validarEstado() throws EntidadeInconsistenteException
	{
		Notificacao notificacao = new Notificacao();
		if (StringUtils.isBlank(nome))
		{
			notificacao.adicionar("Nome é informação obrigatória");
			throw new EntidadeInconsistenteException(notificacao);
		}
	}
}

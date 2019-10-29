package br.com.cefetrj.sc.dominio;

import br.com.cefetrj.sc.exception.EntidadeInconsistenteException;
import org.apache.commons.lang3.StringUtils;

public class Alternativa implements EntidadeVerificavel
{


	private String conteudo;

	private String letra;

	private Alternativa()
	{
	}

	Alternativa(String conteudo, String letra)
	{
		super();
		this.conteudo = conteudo;
		this.letra = letra;
		validarEstado();
	}

	public String getLetra()
	{
		return letra;
	}

	String getConteudo()
	{
		return conteudo;
	}

	public String toString()
	{
		return this.letra + " " + this.conteudo;
	}

	public void validarEstado() throws EntidadeInconsistenteException
	{
		Notificacao notificacao = new Notificacao();
		if (StringUtils.isBlank(conteudo))
		{
			notificacao.adicionar("Conteúdo da questão é vazio.");
			throw new EntidadeInconsistenteException(notificacao);
		}
		if (StringUtils.isBlank(letra))
		{
			notificacao.adicionar("Opção inválida.");
			throw new EntidadeInconsistenteException(notificacao);
		}
	}
}

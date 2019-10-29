package br.com.cefetrj.sc.exception;

import br.com.cefetrj.sc.dominio.Notificacao;

public class EntidadeInconsistenteException extends RuntimeException
{

	private static final long serialVersionUID = -511386132068846129L;

	private Notificacao notificacao;

	public EntidadeInconsistenteException(Notificacao notificacao)
	{
		this.notificacao = notificacao;
	}

	public EntidadeInconsistenteException(String msg, NomeProvaDuplicadoException e)
	{
		super(msg, e);
	}

	public Notificacao getNotificacao()
	{
		return notificacao;
	}

}
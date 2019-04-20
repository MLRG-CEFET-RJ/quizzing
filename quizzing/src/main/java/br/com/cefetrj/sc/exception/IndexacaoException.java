package br.com.cefetrj.sc.exception;

public class IndexacaoException extends RuntimeException
{

	private static final long serialVersionUID = -511386132068846129L;

	public IndexacaoException()
	{
	}

	public IndexacaoException(String mensagem)
	{
		super(mensagem);

	}

	public IndexacaoException(Throwable causa)
	{
		super(causa);

	}

	public IndexacaoException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}
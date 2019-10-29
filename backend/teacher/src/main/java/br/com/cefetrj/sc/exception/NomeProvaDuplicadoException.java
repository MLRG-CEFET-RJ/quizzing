package br.com.cefetrj.sc.exception;

public class NomeProvaDuplicadoException extends Exception
{

	private static final long serialVersionUID = -511386132068846129L;

	public NomeProvaDuplicadoException()
	{
	}

	public NomeProvaDuplicadoException(String mensagem)
	{
		super(mensagem);

	}

	public NomeProvaDuplicadoException(Throwable causa)
	{
		super(causa);

	}

	public NomeProvaDuplicadoException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}
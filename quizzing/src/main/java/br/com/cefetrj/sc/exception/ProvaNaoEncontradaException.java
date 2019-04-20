package br.com.cefetrj.sc.exception;

public class ProvaNaoEncontradaException extends RuntimeException
{

	private static final long serialVersionUID = -511386132068846129L;

	public ProvaNaoEncontradaException()
	{
	}

	public ProvaNaoEncontradaException(String mensagem)
	{
		super(mensagem);

	}

	public ProvaNaoEncontradaException(Throwable causa)
	{
		super(causa);

	}

	public ProvaNaoEncontradaException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}
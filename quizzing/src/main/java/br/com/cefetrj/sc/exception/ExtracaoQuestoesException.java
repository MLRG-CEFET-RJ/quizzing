package br.com.cefetrj.sc.exception;

public class ExtracaoQuestoesException extends RuntimeException
{

	private static final long serialVersionUID = -511386132068846129L;

	public ExtracaoQuestoesException()
	{
	}

	public ExtracaoQuestoesException(String mensagem)
	{
		super(mensagem);

	}

	public ExtracaoQuestoesException(Throwable causa)
	{
		super(causa);

	}

	public ExtracaoQuestoesException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}
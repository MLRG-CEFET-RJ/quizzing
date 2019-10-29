package br.com.cefetrj.sc.exception;

public class ExtratorQuestoesNaoCadastradoException extends RuntimeException
{

	private static final long serialVersionUID = -511386132068846129L;

	public ExtratorQuestoesNaoCadastradoException()
	{
	}

	public ExtratorQuestoesNaoCadastradoException(String mensagem)
	{
		super(mensagem);

	}

	public ExtratorQuestoesNaoCadastradoException(Throwable causa)
	{
		super(causa);

	}

	public ExtratorQuestoesNaoCadastradoException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}
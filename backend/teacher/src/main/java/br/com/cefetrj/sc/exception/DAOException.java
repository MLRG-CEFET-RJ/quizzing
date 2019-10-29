package br.com.cefetrj.sc.exception;

public class DAOException extends RuntimeException
{

	private static final long serialVersionUID = 2738639933160296138L;

	public DAOException()
	{
	}

	public DAOException(String mensagem)
	{
		super(mensagem);

	}

	public DAOException(Throwable causa)
	{
		super(causa);

	}

	public DAOException(String mensagem, Throwable causa)
	{
		super(mensagem, causa);
	}
}

package br.com.cefetrj.sc.dominio;

import br.com.cefetrj.sc.exception.EntidadeInconsistenteException;

public interface EntidadeVerificavel
{

	void validarEstado() throws EntidadeInconsistenteException;

}

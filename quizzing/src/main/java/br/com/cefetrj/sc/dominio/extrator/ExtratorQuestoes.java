package br.com.cefetrj.sc.dominio.extrator;

import br.com.cefetrj.sc.dominio.Prova;
import br.com.cefetrj.sc.dominio.Questao;
import br.com.cefetrj.sc.exception.ExtracaoQuestoesException;

import java.io.Serializable;
import java.util.List;

public interface ExtratorQuestoes extends Serializable
{

	List<Questao> run(Prova prova) throws ExtracaoQuestoesException;

}

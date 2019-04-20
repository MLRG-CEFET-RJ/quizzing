package br.com.cefetrj.sc.dominio.extrator.generic;


import br.com.cefetrj.sc.dominio.Questao;
import br.com.cefetrj.sc.dominio.extrator.ExtratorAbstratoQuestoes;

import java.util.List;

public class GenericQuestionsExtractor extends ExtratorAbstratoQuestoes
{
	private static final long serialVersionUID = 8741451055154649115L;


	private String SUFIXO_IDENTIFICADOR_QUESTAO;

	private String PREFIXO_IDENTIFICADOR_QUESTAO;

	private int[] PAGINAS_DESPREZIVEIS;

	private int PROVAVEL_NUMERO_MAXIMO_QUESTOES;

	private String[] LIXO;


	public GenericQuestionsExtractor(String SUFIXO_IDENTIFICADOR_QUESTAO, String PREFIXO_IDENTIFICADOR_QUESTAO, int[] PAGINAS_DESPREZIVEIS, int PROVAVEL_NUMERO_MAXIMO_QUESTOES, String[] LIXO)
	{
		this.SUFIXO_IDENTIFICADOR_QUESTAO = SUFIXO_IDENTIFICADOR_QUESTAO;
		this.PREFIXO_IDENTIFICADOR_QUESTAO = PREFIXO_IDENTIFICADOR_QUESTAO;
		this.PAGINAS_DESPREZIVEIS = PAGINAS_DESPREZIVEIS;
		this.PROVAVEL_NUMERO_MAXIMO_QUESTOES = PROVAVEL_NUMERO_MAXIMO_QUESTOES;
		this.LIXO = LIXO;
	}

	@Override
	protected String gerarIdentificadorBusca(int numeroQuestao)
	{
		if (numeroQuestao < 10)
		{
			return PREFIXO_IDENTIFICADOR_QUESTAO.concat("0").concat(String.valueOf(numeroQuestao).concat(SUFIXO_IDENTIFICADOR_QUESTAO));
		}
		else
		{
			return PREFIXO_IDENTIFICADOR_QUESTAO.concat(String.valueOf(numeroQuestao).concat(SUFIXO_IDENTIFICADOR_QUESTAO));
		}
	}

	@Override
	protected int[] obterPaginasDespreziveis()
	{
		return PAGINAS_DESPREZIVEIS;
	}

	@Override
	protected String[] obterRelacaoLixo()
	{
		return LIXO;
	}

	@Override
	protected int obterProvavelNumeroMaximoQuestoes()
	{
		return PROVAVEL_NUMERO_MAXIMO_QUESTOES;
	}

	@Override
	protected List<Questao> estruturarQuestoes(List<Questao> questoes, String optionsFormat)
	{
		for (Questao questao : questoes)
		{
			questao.estruturar(optionsFormat);
		}
		return questoes;
	}
}

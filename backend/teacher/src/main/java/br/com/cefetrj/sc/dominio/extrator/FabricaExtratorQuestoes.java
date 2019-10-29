package br.com.cefetrj.sc.dominio.extrator;

import br.com.cefetrj.sc.dominio.Prova;
import br.com.cefetrj.sc.exception.ExtratorQuestoesNaoCadastradoException;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class FabricaExtratorQuestoes
{

	private Map<String, ExtratorQuestoes> mapaExtratores;

	public FabricaExtratorQuestoes(Map<String, ExtratorQuestoes> mapa)
	{
		mapaExtratores = mapa;
	}

	public Collection<String> obterInstituicoesCadastradas()
	{

		return Collections.unmodifiableCollection(mapaExtratores.keySet());
	}

	public ExtratorQuestoes obterExtratorProva(Prova prova) throws ExtratorQuestoesNaoCadastradoException
	{

		if (!mapaExtratores.containsKey(prova.getBancaOrganizadora()))
		{
			throw new ExtratorQuestoesNaoCadastradoException("Extrator n�o encontrado: " + prova.getBancaOrganizadora());
		}

		return mapaExtratores.get(prova.getBancaOrganizadora());
	}
}

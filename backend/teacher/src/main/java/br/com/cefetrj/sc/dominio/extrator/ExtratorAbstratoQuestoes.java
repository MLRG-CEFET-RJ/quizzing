package br.com.cefetrj.sc.dominio.extrator;

import br.com.cefetrj.sc.dominio.Prova;
import br.com.cefetrj.sc.dominio.Questao;
import br.com.cefetrj.sc.exception.ExtracaoQuestoesException;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ExtratorAbstratoQuestoes implements ExtratorQuestoes
{

	private static final long serialVersionUID = -4694867251370890113L;

	public List<Questao> run(Prova prova) throws ExtracaoQuestoesException
	{

		InputStream stream = null;

		PDDocument documento = null;

		try
		{

			stream = new ByteArrayInputStream(prova.getArquivoProva());

			documento = PDDocument.load(stream);

			PDFTextStripper stripper = new PDFTextStripper();

			removerPaginasDespreziveis(documento);

			String texto = stripper.getText(documento);

			texto = removerConteudoIrrelevante(texto);

			List<Questao> questoes = new ArrayList<>();

			int numeroQuestao = 1;

			int indiceInicial = obterIndiceQuestao(numeroQuestao, texto);

			while (indiceInicial != -1)
			{

				numeroQuestao++;

				int indiceFinal = obterIndiceQuestao(numeroQuestao, texto);

				if (indiceFinal != -1 && indiceFinal < indiceInicial)
				{

					int proporcional = 0;

					String aux = texto;

					while (indiceFinal < indiceInicial)
					{

						indiceFinal += gerarIdentificadorBusca(numeroQuestao).length();

						proporcional += indiceFinal;

						aux = aux.substring(indiceFinal);

						indiceFinal = obterIndiceQuestao(numeroQuestao, aux);

					}

					if (indiceFinal != -1)
					{
						indiceFinal += proporcional;
					}
				}

				if (indiceFinal == -1)
				{

					for (int i = numeroQuestao + 1; i <= obterProvavelNumeroMaximoQuestoes(); i++)
					{

						int indiceBusca = obterIndiceQuestao(i, texto);

						if (indiceBusca != -1)
						{
							indiceFinal = indiceBusca;
							numeroQuestao = i;
							break;
						}
					}
				}

				String conteudo;

				if (indiceFinal == -1)
				{
					conteudo = texto.substring(indiceInicial);
				}
				else
				{
					conteudo = texto.substring(indiceInicial, indiceFinal);

					texto = texto.substring(indiceFinal);
				}

				Questao questao = new Questao(conteudo, numeroQuestao - 1, prova);

				questoes.add(questao);

				indiceInicial = obterIndiceQuestao(numeroQuestao, texto);
			}

			return estruturarQuestoes(questoes, prova.getOptionsFormat());
		}
		catch (Exception e)
		{
			throw new ExtracaoQuestoesException(e);
		}
		finally
		{
			IOUtils.closeQuietly(stream);

			try
			{
				assert documento != null;
 				documento.close();
			}
			catch (Exception e)
			{
				// quieto...
			}

		}

	}

	protected abstract List<Questao> estruturarQuestoes(List<Questao> questoes, String optionsFormat);

	private int obterIndiceQuestao(int numeroQuestao, String texto)
	{
		return texto.indexOf(gerarIdentificadorBusca(numeroQuestao));
	}

	protected abstract String gerarIdentificadorBusca(int numeroQuestao);

	private String removerConteudoIrrelevante(String texto)
	{

		String[] lixo = obterRelacaoLixo();

		for (int i = 0; lixo != null && i < lixo.length; i++)
		{

			if (texto.contains(lixo[i]))
			{
				texto = texto.replaceAll(lixo[i], "");
			}
		}

		return texto;
	}

	protected abstract String[] obterRelacaoLixo();

	private void removerPaginasDespreziveis(PDDocument documento)
	{

		int[] paginasDespreziveis = obterPaginasDespreziveis();
		Arrays.sort(paginasDespreziveis);

		for (int i = paginasDespreziveis.length - 1; i >= 0; i--)
		{
			documento.removePage(paginasDespreziveis[i] - 1);
		}

	}

	protected abstract int[] obterPaginasDespreziveis();

	protected abstract int obterProvavelNumeroMaximoQuestoes();

}

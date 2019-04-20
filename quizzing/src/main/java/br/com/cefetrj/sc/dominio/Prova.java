package br.com.cefetrj.sc.dominio;

import br.com.cefetrj.sc.dominio.extrator.ExtratorQuestoes;
import br.com.cefetrj.sc.dominio.extrator.generic.GenericQuestionsExtractor;
import br.com.cefetrj.sc.exception.EntidadeInconsistenteException;
import br.com.cefetrj.sc.exception.ProvaNaoEncontradaException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;


public class Prova implements Serializable,
                              EntidadeVerificavel
{

	private static final long serialVersionUID = 6404730707564474905L;
	private List<Questao> questoes;
	private String nomeArquivo;

	private byte[] arquivoProva;

	private String bancaOrganizadora;

	private Integer anoRealizacao;

	private String optionsFormat;

	public Prova(String nomeArquivo, String bancaOrganizadora, Integer anoRealizacao, byte[] arquivoProva, String optionsFormat, String sufix, String prefix, int[] uselessPages, int totalQuestions, String[] uselessWords) throws EntidadeInconsistenteException
	{
		super();
		this.nomeArquivo = nomeArquivo;
		this.bancaOrganizadora = bancaOrganizadora;
		this.anoRealizacao = anoRealizacao;
		this.arquivoProva = arquivoProva;
		this.optionsFormat = optionsFormat;

		ExtratorQuestoes extrator = new GenericQuestionsExtractor(sufix, prefix, uselessPages, totalQuestions, uselessWords);

		questoes = extrator.run(this);

		validarEstado();
	}

	public void validarEstado() throws EntidadeInconsistenteException
	{

		Notificacao notificacao = new Notificacao();

		if (StringUtils.isBlank(bancaOrganizadora))
		{
			notificacao.adicionar("A banca organizadora é item obrigatório");
		}

		if (StringUtils.isBlank(nomeArquivo))
		{
			notificacao.adicionar("O nome do arquivo é item obrigatório");
		}

		if (notificacao.possuiMensagens())
		{
			throw new EntidadeInconsistenteException(notificacao);
		}
	}

	public String getNomeArquivo()
	{
		return nomeArquivo;
	}

	public synchronized byte[] getArquivoProva() throws ProvaNaoEncontradaException
	{
		return arquivoProva;
	}

	public void setArquivoProva(byte[] arquivoProva)
	{
		this.arquivoProva = arquivoProva;
	}

	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void definirNovoNomeArquivo()
	{
		String timestamp = String.valueOf(System.currentTimeMillis());
		timestamp = timestamp.concat("-");
		nomeArquivo = timestamp.concat(nomeArquivo);
	}

	public String getBancaOrganizadora()
	{
		return bancaOrganizadora;
	}

	public Integer getAnoRealizacao()
	{
		return anoRealizacao;
	}

	public String toString()
	{
		return this.bancaOrganizadora + "-" + this.anoRealizacao;
	}

	public String getOptionsFormat()
	{
		return optionsFormat;
	}
}

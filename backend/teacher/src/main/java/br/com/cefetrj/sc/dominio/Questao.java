package br.com.cefetrj.sc.dominio;

import br.com.cefetrj.sc.exception.EntidadeInconsistenteException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Questao implements Serializable,
                                EntidadeVerificavel
{


	private static final long serialVersionUID = 1L;

	private ArrayList<Alternativa> alternativas = new ArrayList<>();

	@JsonIgnore
	private Prova prova;

	private int numero = Integer.MIN_VALUE;

	private String textoBase;

	private String conteudo;

	Questao()
	{
	}

	public Questao(String conteudo, int numero, Prova prova) throws EntidadeInconsistenteException
	{
		super();
		this.conteudo = conteudo;
		this.numero = numero;
		this.prova = prova;
		validarEstado();
	}

	public void validarEstado() throws EntidadeInconsistenteException
	{

		Notificacao notificacao = new Notificacao();

		if (StringUtils.isBlank(conteudo))
		{
			notificacao.adicionar("Conteúdo obrigatório.");
		}

		if (numero == Integer.MIN_VALUE)
		{
			notificacao.adicionar("Número obrigatório.");
		}

		if (notificacao.possuiMensagens())
		{
			throw new EntidadeInconsistenteException(notificacao);
		}
	}

	public String getConteudo()
	{
		return this.conteudo;
	}

	public void setConteudo(String conteudo)
	{
		this.conteudo = conteudo;
	}

	public int getNumero()
	{
		return numero;
	}

	private void addAlternativa(String texto, String opcao)
	{
		Alternativa alternativa = new Alternativa(texto, opcao);
		this.alternativas.add(alternativa);
	}

	public String getTextoBase()
	{
		return textoBase;
	}

	private void setTextoBase(String textoBase)
	{
		this.textoBase = textoBase;
	}

	public String toString()
	{
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.textoBase);
		result.append(NEW_LINE);
		for (Alternativa alternativa : alternativas)
		{
			result.append(alternativa.toString());
			result.append(NEW_LINE);
		}
		return result.toString();
	}

	public boolean isValida()
	{
		if (StringUtils.isBlank(this.textoBase))
		{
			return false;
		}
		for (Alternativa alternativa : alternativas)
		{
			if (StringUtils.isBlank(alternativa.getConteudo()))
			{
				return false;
			}
		}
		return true;
	}

	public void estruturar(String optionsFormat)
	{
		String[] opcoes = optionsFormat.split(",");
		String conteudo = this.conteudo;

		try
		{
			String regex = "[0123456789]{1,2}\\r\\n";
			conteudo = conteudo.replaceFirst(regex, "");

			String textoBase = conteudo.substring(0, conteudo.indexOf(opcoes[0]));
			textoBase = textoBase.trim().replaceAll("-\\r\\n", "");
			this.setTextoBase(textoBase);
		}
		catch (StringIndexOutOfBoundsException ex2)
		{
			ex2.printStackTrace();
		}

		for (int i = 0; i < opcoes.length; i++)
		{
			int beginIndex = conteudo.indexOf(opcoes[i]) + opcoes[i].length();
			int endIndex;
			if (i + 1 < opcoes.length)
			{
				endIndex = conteudo.lastIndexOf(opcoes[i + 1]);
			}
			else
			{
				endIndex = conteudo.length() - 1;
			}
			try
			{
				String texto = conteudo.substring(beginIndex, endIndex).trim();
				texto = texto.trim().replaceAll("-\\r\\n", "");
				texto = texto.replaceAll("\\r\\n", " ");
				if (texto.length() == 0)
				{
					this.setTextoBase("");
					throw new IllegalStateException("Texto da opção é vazio.");
				}

				this.addAlternativa(texto, opcoes[i]);
				Alternativa alternativa = new Alternativa(texto, opcoes[i]);

			}
			catch (IllegalStateException | StringIndexOutOfBoundsException | IllegalArgumentException ex1)
			{
				ex1.printStackTrace();
				break;
			}
		}
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public ArrayList<Alternativa> getAlternativas()
	{
		return alternativas;
	}

	public void setAlternativas(ArrayList<Alternativa> alternativas)
	{
		this.alternativas = alternativas;
	}

	public void setProva(Prova prova)
	{
		this.prova = prova;
	}

	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public Prova getProva()
	{
		return prova;
	}
}

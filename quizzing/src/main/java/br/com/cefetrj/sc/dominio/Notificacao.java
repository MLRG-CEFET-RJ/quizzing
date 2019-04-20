package br.com.cefetrj.sc.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notificacao implements Serializable
{

	private static final long serialVersionUID = 7125819861952135085L;

	private List<String> descricoes = new ArrayList<>();

	Notificacao()
	{
	}

	public Notificacao(String mensagem)
	{
		adicionar(mensagem);
	}

	public List<String> getDescricoes()
	{
		return new ArrayList<>(descricoes);
	}

	void adicionar(String texto)
	{
		this.descricoes.add(texto);
	}

	boolean possuiMensagens()
	{
		return !this.descricoes.isEmpty();
	}

}

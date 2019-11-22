package br.com.cefetrj.ws.quizzing.pojo;

public class Import
{
	private String file;
	private String ignoredPages;
	private String ignoredWords;
	private String questionSuffix;
	private String questionPrefix;
	private int maxQuestionsNumber;
	private String optionsIdentifier;

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getIgnoredPages()
	{
		return ignoredPages;
	}

	public void setIgnoredPages(String ignoredPages)
	{
		this.ignoredPages = ignoredPages;
	}

	public String getIgnoredWords()
	{
		return ignoredWords;
	}

	public void setIgnoredWords(String ignoredWords)
	{
		this.ignoredWords = ignoredWords;
	}

	public String getQuestionSuffix()
	{
		return questionSuffix;
	}

	public void setQuestionSuffix(String questionSuffix)
	{
		this.questionSuffix = questionSuffix;
	}

	public String getQuestionPrefix()
	{
		return questionPrefix;
	}

	public void setQuestionPrefix(String questionPrefix)
	{
		this.questionPrefix = questionPrefix;
	}

	public int getMaxQuestionsNumber()
	{
		return maxQuestionsNumber;
	}

	public void setMaxQuestionsNumber(int maxQuestionsNumber)
	{
		this.maxQuestionsNumber = maxQuestionsNumber;
	}

	public String getOptionsIdentifier()
	{
		return optionsIdentifier;
	}

	public void setOptionsIdentifier(String optionsIdentifier)
	{
		this.optionsIdentifier = optionsIdentifier;
	}
}

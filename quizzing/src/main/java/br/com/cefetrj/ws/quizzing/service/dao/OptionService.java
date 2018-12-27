package br.com.cefetrj.ws.quizzing.service.dao;

import br.com.cefetrj.ws.quizzing.model.option.Option;
import br.com.cefetrj.ws.quizzing.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService
{
	@Autowired
	OptionRepository optionRepository;

	public Option createOption(String text)
	{
		Option option = new Option();
		option.setText(text);
		return optionRepository.save(option);
	}

}

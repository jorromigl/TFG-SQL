package converters;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Squadra;

@Component
@Transactional
public class SquadraToStringConverter implements Converter<Squadra, String>{
	
	@Override
	public String convert(Squadra squadra){
		String result;
		
		if(squadra == null)
			result = null;
		else
			result = String.valueOf(squadra.getId());
		
		return result;
	}

}

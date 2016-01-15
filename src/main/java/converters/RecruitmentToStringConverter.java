package converters;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Recruitment;

@Component
@Transactional
public class RecruitmentToStringConverter implements Converter<Recruitment, String>{
	
	@Override
	public String convert(Recruitment recruitment){
		String result;
		
		if(recruitment == null)
			result = null;
		else
			result = String.valueOf(recruitment.getId());
		
		return result;
	}

}

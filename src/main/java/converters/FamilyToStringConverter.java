package converters;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Family;

@Component
@Transactional
public class FamilyToStringConverter implements Converter<Family, String>{
	
	@Override
	public String convert(Family family){
		String result;
		
		if(family == null)
			result = null;
		else
			result = String.valueOf(family.getId());
		
		return result;
	}

}

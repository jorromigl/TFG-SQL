package converters;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Summary;

@Component
@Transactional
public class SummaryToStringConverter implements Converter<Summary, String>{
	
	@Override
	public String convert(Summary summary){
		String result;
		
		if(summary == null)
			result = null;
		else
			result = String.valueOf(summary.getId());
		
		return result;
	}

}

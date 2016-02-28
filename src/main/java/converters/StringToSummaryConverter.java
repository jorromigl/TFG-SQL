package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SummaryRepository;
import domain.Summary;

@Component
@Transactional
public class StringToSummaryConverter implements Converter<String, Summary> {
	
	@Autowired SummaryRepository summaryRepository;
	
	@Override 
	public Summary convert(String text){
		
		Summary result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = summaryRepository.findOne(id);
			}
			
		} catch(Throwable error){
			throw new IllegalArgumentException(error);
		}
		
		return result;
	}
	

}

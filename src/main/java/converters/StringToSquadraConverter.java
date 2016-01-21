package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SquadraRepository;
import domain.Squadra;

@Component
@Transactional
public class StringToSquadraConverter implements Converter<String, Squadra> {
	
	@Autowired SquadraRepository squadraRepository;
	
	@Override 
	public Squadra convert(String text){
		
		Squadra result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = squadraRepository.findOne(id);
			}
			
		} catch(Throwable error){
			throw new IllegalArgumentException(error);
		}
		
		return result;
	}
	

}

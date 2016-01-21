package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MatchRepository;
import domain.Match;

@Component
@Transactional
public class StringToMatchConverter implements Converter<String, Match> {
	
	@Autowired MatchRepository matchRepository;
	
	@Override 
	public Match convert(String text){
		
		Match result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = matchRepository.findOne(id);
			}
			
		} catch(Throwable error){
			throw new IllegalArgumentException(error);
		}
		
		return result;
	}
	

}

package converters;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Player;

import repositories.PlayerRepository;

@Component
@Transactional
public class StringToPlayerConverter implements Converter<String, Player> {
	
	@Autowired PlayerRepository playerRepository;
	
	@Override 
	public Player convert(String text){
		
		Player result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = playerRepository.findOne(id);
			}
			
		} catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
	

}

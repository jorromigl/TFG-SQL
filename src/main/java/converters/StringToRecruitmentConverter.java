package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RecruitmentRepository;
import domain.Recruitment;

@Component
@Transactional
public class StringToRecruitmentConverter implements Converter<String, Recruitment> {
	
	@Autowired RecruitmentRepository recruitmentRepository;
	
	@Override 
	public Recruitment convert(String text){
		
		Recruitment result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = recruitmentRepository.findOne(id);
			}
			
		} catch(Throwable error){
			throw new IllegalArgumentException(error);
		}
		
		return result;
	}
	

}

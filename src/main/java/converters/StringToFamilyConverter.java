package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Family;
import repositories.FamilyRepository;

@Component
@Transactional
public class StringToFamilyConverter implements Converter<String, Family> {

	@Autowired
	FamilyRepository familyRepository;

	@Override
	public Family convert(String text) {

		Family result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = familyRepository.findOne(id);

		} catch (Throwable error) {
			throw new IllegalArgumentException(error);
		}

		return result;
	}

}

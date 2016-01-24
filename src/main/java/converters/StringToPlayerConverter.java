package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Player;
import repositories.PlayerRepository;

@Component
@Transactional
public class StringToPlayerConverter implements Converter<String, Player> {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public Player convert(String text) {

		Player result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = playerRepository.findOne(id);
		} catch (Throwable error) {
			throw new IllegalArgumentException(error);
		}

		return result;
	}

}

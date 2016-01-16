package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query("select f.messages from User a join a.folders f where a.id=?1")
	Collection<Message> findMessagesByUserId(int id);

}

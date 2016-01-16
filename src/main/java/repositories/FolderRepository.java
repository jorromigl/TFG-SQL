package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select a.folders from User a where a.id=?1")
	Collection<Folder> findFoldersByUser(int userId);
	
	@Query("select f from User a join a.folders f where a.id=?1 and f.name='Inbox'")
	Folder inboxFolder(int userId);
	
	@Query("select f from User a join a.folders f where a.id=?1 and f.name='Outbox'")
	Folder outboxFolder(int userId);
	
}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
	
	@Query("select a from Folder a where a.userAccount.id=?1")
	Folder findByUserAccountId(int id);

}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer> {


}

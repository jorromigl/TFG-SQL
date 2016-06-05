package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ClassificationRepository;
import repositories.CommentRepository;

@Service
@Transactional
public class ClassificationService {
	

	// Managed repository
	@Autowired
	private ClassificationRepository classificationRepository;
	
	
	// Constructor
	public ClassificationService() {
		
		super();
	}

}

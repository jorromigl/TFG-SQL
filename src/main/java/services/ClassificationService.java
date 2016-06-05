package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Classification;
import domain.Coach;
import domain.Comment;
import domain.Folder;
import repositories.ClassificationRepository;
import repositories.CommentRepository;
import security.Authority;
import security.UserAccount;
import webscraping.obtainData;

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
	
	public Classification create() {
		Classification res = new Classification();

		return res;
	}
	
	public List<Classification> clasification() throws IOException, ParserConfigurationException, Exception{
		Classification result= create();
		List<Classification> tabla= new ArrayList<>();
		List<List<String>> rows= obtainData.rows();
		for ( List<String> row: rows){
				result.setInfo(row.get(0));
				result.setPoint(Integer.parseInt(row.get(1)));
				result.setPlayed(Integer.parseInt(row.get(2)));
				result.setWon(Integer.parseInt(row.get(3)));
				result.setLost(Integer.parseInt(row.get(4)));
				result.setTied(Integer.parseInt(row.get(5)));	
				tabla.add(result);
			}
		
		return tabla;
		
		
		
		
	}

}

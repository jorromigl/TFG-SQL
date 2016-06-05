package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Classification;
import domain.Coach;
import domain.Comment;
import domain.Folder;
import domain.User;
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

	@Autowired
	private UserService userService;

	public static final String RUTA = "C:/Users/Jorge/git/TFG-SQL/src/main/resources";

	// Constructor
	public ClassificationService() {

		super();
	}

	public Classification create() {
		Classification res = new Classification();

		return res;
	}

	public List<Classification> clasification() throws IOException, ParserConfigurationException, Exception{
		List<Classification> tabla= new ArrayList<>();
		List<List<String>> rows= rows();		
		for ( List<String> row: rows){
			Classification result= create();
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

	public static final String url = "http://www.faf.es/pnfg/NPcd/NFG_VisClasificacion?cod_primaria=1000120&codjornada=26&codcompeticion=2646493&codgrupo=3764773&codjornada=26";

	public List<List<String>> rows() throws IOException, ParserConfigurationException, Exception {

		List<List<String>> rows = new ArrayList<List<String>>();

		// Compruebo si me da un 200 al hacer la petición
		if (obtainData.getStatusConnectionCode(url) == 200) {

			// Obtengo el HTML de la web en un objeto Document (solo una vez por
			// semana)
			Document document = obtainData.getHtmlDocument(url);
			// System.out.println(document);

			String documentHTML = obtainData.readFile(RUTA + "/aaa.txt");

			// Convierto el String en Document
			Document doc = Jsoup.parse(documentHTML);
			Elements tables = doc.getElementsByTag("table");
			Element firstTable = tables.get(4);
			Elements tr2 = firstTable.getElementsByTag("tr");
			List<String> lista = new ArrayList<String>();
			int numeroRows = 0;
			for (int i = 2; i < 15; i++) {
				Element td = tr2.get(i);
				Elements tds = td.getElementsByTag("td");
				numeroRows = tds.size();
				for (int e = 2; e < numeroRows; e++) {
					lista.add(tds.get(e).text());
				}
			}
			int a = 0;
			int b = 10;
			for (int c = 0; c <= numeroRows; c++) {
				rows.add(lista.subList(a, b));
				a = b;
				b += 10;
			}
		}
		return rows;
	}

}

package webscraping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;

public class obtainData {
	
	public static final String RUTA = "./src/main/java/webscraping";
	
	/*Read data from a text file and store in a String variable*/
	static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	 private static Document convertStringToDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try 
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = (Document) builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	 
	
	
	public static int getStatusConnectionCode(String url) {
		
	    Response response = null;
		
	    try {
		response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	    } catch (IOException ex) {
		System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
	    }
	    return response.statusCode();
	}
	
	/**
	 * Con este método devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
	 * @param url
	 * @return Documento con el HTML
	 */
	public static Document getHtmlDocument(String url) {

	    Document doc = null;
		try {
		    doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		    } catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		    }
	    return doc;
	}
	

		
	    public static final String url = "http://www.faf.es/pnfg/NPcd/NFG_VisClasificacion?cod_primaria=1000120&codjornada=26&codcompeticion=2646493&codgrupo=3764773&codjornada=26";
	   
	    public static void main (String args[]) throws IOException, ParserConfigurationException, Exception {
			
	        // Compruebo si me da un 200 al hacer la petición
	        if (getStatusConnectionCode(url) == 200) {
				
	            // Obtengo el HTML de la web en un objeto Document (solo una vez por semana)
	            Document document = getHtmlDocument(url);
//	            System.out.println(document);
	            
	            String documentHTML = readFile(RUTA + "/clasificacion05062016.txt");
	            
	            // Convierto el String en Document
	    		Document doc = Jsoup.parse(documentHTML);
	           
	            System.out.println(doc);
				
	            // Busco todas las entradas que estan dentro de: 
	         
	            Elements entradas = doc.select("td");
	            System.out.println("Número de entradas en la página inicial de Jarroba: "+entradas.size()+"\n");
				List<String> equipos = new ArrayList<String>();
				List<String> resultados = new ArrayList<String>();
	            // Paseo cada una de las entradas
	            for (Element elem : entradas) {
	                String equipo = elem.getElementsByClass("fondotitulolistadosizq").text();
	                equipos.add(equipo);
	                String Listados = elem.getElementsByClass("fondolistados").text();
					resultados.add(Listados);
					
			
					
					// Busco todas las entradas que estan dentro de: 
			         
//		            Elements filas = doc.select("tr");
//					List<String> filaList = new ArrayList<String>();
//					Element firstRow = filas.first();
//					
//					List<String> headers = new ArrayList<String>();
//				
//					
//					
//					System.out.println("FILAS" + filas);
//					
//		            for (Element elem2 : filas) {
////		                String orden = 
//		                filaList.add(elem2.getElementsByClass("FILA_OFF").text());
//		               
//	            }
//	            System.out.println("FILAS_OFF" + filaList);
	            
	            }
	            
	    		System.out.println("QEUIPO" + equipos);
				System.out.println("Result" + resultados);
				
	        }else{
	            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
	        }
	        
	      
	    	
	    }
}
	    	
	    	
	







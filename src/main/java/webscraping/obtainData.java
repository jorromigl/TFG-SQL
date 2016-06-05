package webscraping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
	

		
	    public static final String url = "http://www.faf.es/pnfg/NPcd/NFG_CmpJornada?cod_primaria=1000120&CodCompeticion=5619311&CodGrupo=5619312&CodTemporada=11&CodJornada=1&Sch_Codigo_Delegacion=9&Sch_Tipo_Juego=3";
		
	    public static void main (String args[]) throws IOException, ParserConfigurationException, Exception {
			
	        // Compruebo si me da un 200 al hacer la petición
	        if (getStatusConnectionCode(url) == 200) {
				
	            // Obtengo el HTML de la web en un objeto Document
	            Document document = getHtmlDocument(url);
	            
	            String documentHTML = readFile(RUTA + "/Datos05062016.txt");
	            
	            
	            
//	            Document doc = builder.parse(new InputSource(new StringReader(documentHTML)));
	            
	            
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	            DocumentBuilder builder;  
	           
	                builder = factory.newDocumentBuilder();  
	                org.w3c.dom.Document doc = builder.parse( new InputSource(new StringReader(documentHTML))); 
	            
	         
	            System.out.println("Documento" + documentHTML);
	            System.out.println(doc);
				
	            // Busco todas las entradas que estan dentro de: 
	            Elements entradas = document.select("subtitulos");
	            System.out.println("Número de entradas en la página inicial de Jarroba: "+entradas.size()+"\n");
				
	            // Paseo cada una de las entradas
	            for (Element elem : entradas) {
	                String titulo = elem.getElementsByClass("tituloPost").text();
	                String autor = elem.getElementsByClass("autor").toString();
	                String fecha = elem.getElementsByClass("fecha").text();
					
	                System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n\n");
					
	                // Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
	                // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
	            }
					
	        }else
	            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
	    	
	    	Document doc = Jsoup.connect("http://www.faf.es/").get();
	    	Elements newsHeadlines = doc.select("subtitulos");
	    	System.out.println("aqui"+ newsHeadlines);
	    }
	    	
	    	
	}







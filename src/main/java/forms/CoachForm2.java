package forms;

import javax.persistence.Lob;

public class CoachForm2 {
	
	private int id;
	private int version;
	
	
	private byte[] file;
	
	
	@Lob
	public byte[] getFile(){
		return file;
	}
	
	public void setFile(byte[] file){
		this.file = file;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version= version;
	}

}

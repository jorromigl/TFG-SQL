package forms;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Category;

public class PlayerForm2 {
	
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

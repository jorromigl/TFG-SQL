package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Category {

    private String cname;
    private String age;

    @NotBlank
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    
    //Restriction
    public String getAge(){
    	return age;
    }
    
    public void setAge(String age){
    	this.age=age;
    }


  

 

}

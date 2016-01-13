package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Category {

    private String cname;
    private Integer age;

    @NotBlank
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    
    
    public int getAge(){
    	return age;
    }
    
    public void setAge(int age){
    	this.age=age;
    }


//    public String toString() {
//		return "Category  [name=" + cname + ", age="
//				+ age +"]";
//	}

 

}

//package domain;
//
//import java.util.Collection;
//import java.util.HashSet;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.SafeHtml;
//import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
//
//@Entity
//@Access(AccessType.PROPERTY)
////@Table(indexes= {@Index(columnList="name")})
//public class Folder extends DomainEntity{
//	
//	private String name;
//	
//	
//	public Folder(){
//		super();
//		
//		messages= new HashSet<Message>();
//	}
//	
//	@NotBlank
//	@SafeHtml(whitelistType = WhiteListType.NONE)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	private User user;
//	private Collection<Message> messages;
//	
//	@NotNull
//	@Valid
//	@ManyToOne(optional= false)
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//	@NotNull
//	@OneToMany(mappedBy="folder", cascade=CascadeType.ALL)
//	public Collection<Message> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(Collection<Message> messages) {
//		this.messages = messages;
//	}
//	
//	public void addMessage(Message message) {
//		messages.add(message);
//		message.setFolder(this);
//	
//	}
//	
//	public void removeMessage(Message message) {
//		messages.remove(message);
//		message.setFolder(null);
//	
//	}
//	
//}

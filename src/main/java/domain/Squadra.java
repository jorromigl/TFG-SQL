package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Squadra extends DomainEntity {

	public Squadra() {
		super();

	}

	private String name;
	private Category category;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private Coach coach;
	private Classification classification;
	private Collection<Player> players;

	@ManyToOne(optional = false)
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@OneToOne(optional = true)
	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	@OneToMany(mappedBy = "squadra")
	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player player) {
		players.add(player);
		player.setSquadra(this);
	}

	public void removePlayer(Player player) {
		players.remove(player);
		player.setSquadra(null);
	}

}

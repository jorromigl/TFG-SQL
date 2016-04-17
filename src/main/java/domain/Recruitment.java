package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Recruitment extends DomainEntity {

	public Recruitment() {
		super();

	}

	private Match match;
	private Collection<Player> players;

	@OneToOne(optional = false)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@ManyToMany
	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

	public void addPlayerRecruitment(Player player) {
		players.add(player);
		player.getRecruitments().add(this);
	}

	public void removePlayer(Player player) {
		players.remove(player);
		player.getRecruitments().remove(this);
	}

}

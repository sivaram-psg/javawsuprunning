package ch01.team;

import java.util.List;

public class Team {

	private List<Player> players;
	private String name;
	
	public Team()
	{
		
	}
	public Team(String string, List<Player> mb) {
		setName(name);
		setPlayers(players);
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setRosterCount(int n)
	{
		
	}
	
	public int getRosterCount()
	{
		return players==null?0:players.size();
	}

}

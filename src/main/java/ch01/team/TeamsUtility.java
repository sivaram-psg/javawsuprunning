package ch01.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamsUtility {

	private Map<String, Team> team_map;

	public TeamsUtility() {
		team_map = new HashMap<String, Team>();
	}

	public void make_test_teams() {
     List<Team> list=new ArrayList<Team>();
     Player chico= new Player("Leonard Marx","chico");
     Player groucho=new Player("Julius Marx","groucho");
     Player harpo=new Player("Adolph Marx","Harpo");
     List<Player> mb= new ArrayList<Player>();
     mb.add(chico);
     mb.add(groucho);
     mb.add(harpo);
     Team marx_brothers= new Team("Marx brothers",mb);
     list.add(marx_brothers);
     store_teams(list);
	}

	private void store_teams(List<Team> list) {
		for(Team team:list)
			team_map.put(team.getName(), team);
		
	}

	public Team getTeam(String name) {
		return team_map.get(name);
	}

	public List<Team> getTeams() {
		List<Team> list = new ArrayList<Team>();
		for (String key : team_map.keySet()) {
			list.add(team_map.get(key));
		}
		return list;
	}

}

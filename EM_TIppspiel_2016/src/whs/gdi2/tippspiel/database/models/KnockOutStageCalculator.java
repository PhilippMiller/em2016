package whs.gdi2.tippspiel.database.models;

import java.security.AllPermission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Array;

import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

public class KnockOutStageCalculator {

	public static boolean RoundOfSixteen(ArrayList<TableRankingObject> tro_list) {
		try {
			ArrayList<Team[]> allGroups = new ArrayList<Team[]>();
			
			ArrayList<Integer> gameID = DatabaseManagement.groupGameIds("Achtelfinale");

			for (int i = 0; i < tro_list.size(); i++) {
				allGroups.add(new Team[tro_list.get(i).getTeams().size()]);
				int j = 0;
				for (Team theTeam : tro_list.get(i).getTeams()) {
					allGroups.get(i)[j] = theTeam;
					j++;
				}
			}

			for (Team[] group : allGroups) {
				Arrays.sort(group);
			}

			Statement statement = Main.mainConnection.getConnection().createStatement();

			// Achtelfinale Spiel 1 [2A:2C]
			String sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(0)[1].getTeamName()
					+ "', gastmannschaft='" + allGroups.get(2)[1].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(0);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(0));

			// Achtelfinale Spiel 2 [1B:3A/C/D]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(1)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(0)[2], allGroups.get(2)[2], allGroups.get(3)[2])[0].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(1);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(1));
			
			// Achtelfinale Spiel 3 [1D:3B/E/F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(3)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(1)[2], allGroups.get(4)[2], allGroups.get(5)[2])[0].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(2);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(2));
			
			// Achtelfinale Spiel 4 [1A:3C/D/E]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(0)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(2)[2], allGroups.get(3)[2], allGroups.get(4)[2])[0].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(3);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(3));
			
			// Achtelfinale Spiel 5 [1C:3A/B/F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(2)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(0)[2], allGroups.get(1)[2], allGroups.get(5)[2])[0].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(4);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(4));
			
			// Achtelfinale Spiel 6 [1F:2E]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(5)[0].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(4)[1].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(5);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(5));
			
			// Achtelfinale Spiel 7 [1E:2D]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(4)[0].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(3)[1].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(6);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(6));
			
			// Achtelfinale Spiel 8 [2B:2F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(1)[1].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(5)[1].getTeamName()
					+ "' WHERE spieleid=" + gameID.get(7);
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:" + gameID.get(7));
			
			return true;
		} catch (Exception e) {
			Log.mysqlError("Beim erstellen der KO-Phase ist ein Fehler aufgetreten. Error: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	private static Team[] drittbeste(Team team1, Team team2, Team team3) {
		Team[] drittbesten = { team1, team2, team3 };

		Arrays.sort(drittbesten);
		return drittbesten;
	}
	
	// Viertelfinale
	public static boolean RoundOfEight(ResultSet rs) {
		try {
			
			ArrayList<Team> teams = checkForWinner(rs);
			if (teams.size() < 8) {
				return false;
			}
			
			ArrayList<Integer> gameID = DatabaseManagement.groupGameIds("Viertelfinale");

			// Viertelfinale Spiel 1 [1AF:3AF]
			String sql = "UPDATE spiele SET heimmannschaft='" + teams.get(0).getTeamName()
					+ "', gastmannschaft='" + teams.get(2).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(0);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(0));
			
			// Viertelfinale Spiel 2 [2AF:6AF]
			sql = "UPDATE spiele SET heimmannschaft='" + teams.get(1).getTeamName()
					+ "', gastmannschaft='" + teams.get(5).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(1);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(1));
			
			// Viertelfinale Spiel 3 [5AF:7AF]
			sql = "UPDATE spiele SET heimmannschaft='" + teams.get(4).getTeamName()
					+ "', gastmannschaft='" + teams.get(6).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(2);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(2));			
			
			// Viertelfinale Spiel 4 [4AF:8AF]
			sql = "UPDATE spiele SET heimmannschaft='" + teams.get(3).getTeamName()
					+ "', gastmannschaft='" + teams.get(7).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(3);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(3));
			
			return true;
		} catch (Exception e) {
			Log.error("While reading data from resultSet a error occured. Error: " + e.getMessage());
			return false;
		}
	}
	
	// Halbfinale
	public static boolean RoundOfFour(ResultSet rs) {
		try {
			ArrayList<Team> teams = checkForWinner(rs);
			if (teams.size() < 4) {
				return false;
			}
			
			ArrayList<Integer> gameID = DatabaseManagement.groupGameIds("Viertelfinale");
	
			// Halbfinale Spiel 1 [1VF:2VF]
			String sql = "UPDATE spiele SET heimmannschaft='" + teams.get(0).getTeamName()
					+ "', gastmannschaft='" + teams.get(1).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(0);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(0));
			
			// Halbfinale Spiel 2 [3VF:4VF]
			sql = "UPDATE spiele SET heimmannschaft='" + teams.get(2).getTeamName()
					+ "', gastmannschaft='" + teams.get(3).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(1);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(1));
			
			return true;
		} catch (Exception e) {
			Log.error("While reading data from resultSet a error occured. Error: " + e.getMessage());
			return false;
		}
	}
	
	// Finale
	public static boolean RoundOfTwo(ResultSet rs) {
		try {
			ArrayList<Team> teams = checkForWinner(rs);
			if (teams.size() < 2) {
				return false;
			}
			
			ArrayList<Integer> gameID = DatabaseManagement.groupGameIds("Finale");
	
			// Halbfinale Spiel 1 [1VF:2VF]
			String sql = "UPDATE spiele SET heimmannschaft='" + teams.get(0).getTeamName()
					+ "', gastmannschaft='" + teams.get(1).getTeamName()
					+ "' WHERE spieleid=" + gameID.get(0);
			DatabaseManagement.updateRow(sql);
			Log.mysql("Successfully updated 'Viertelfinale' spieleid:" + gameID.get(0));
			
			return true;
		} catch (Exception e) {
			Log.error("While reading data from resultSet a error occured. Error: " + e.getMessage());
			return false;
		}
	}
	
	
	
	private static ArrayList<Team> checkForWinner(ResultSet rs) throws Exception{
		
		ArrayList<Team> teams = new ArrayList<Team>();
		
		while (rs.next()) {
			Team teamHeim = new Team();
			teamHeim.setTeamName(rs.getString("heimmannschaft"));
			teamHeim.setGoals(rs.getInt("heimmannschaftende"));
			System.out.println(">" + teamHeim.getTeamName() + "< hat >" + teamHeim.getGoals() + "< Tore.");
			
			Team teamGast = new Team();
			teamGast.setTeamName(rs.getString("gastmannschaft"));
			teamGast.setGoals(rs.getInt("gastmannschaftende"));
			System.out.println(">" + teamGast.getTeamName() + "< hat >" + teamGast.getGoals() + "< Tore.");
			
			// Tor Sieger
			if (teamHeim.getGoals() > teamGast.getGoals()) {
				teams.add(teamHeim);
			} else if(teamHeim.getGoals() < teamGast.getGoals()) {
				teams.add(teamGast);
			} else {
				// Verlängerung Sieger
				if (rs.getInt("heimmannschaftverl") > rs.getInt("gastmannschaftverl")) {
					teams.add(teamHeim);
				} else if (rs.getInt("heimmannschaftverl") < rs.getInt("gastmannschaftverl")) {
					teams.add(teamGast);
				} else {
					// Elfmeter Sieger
					if (rs.getInt("heimmannschaftelf") > rs.getInt("gastmannschaftelf")) {
						teams.add(teamHeim);
					} else if (rs.getInt("heimmannschaftelf") < rs.getInt("gastmannschaftelf")) {
						teams.add(teamGast);
					} else {
						throw new Exception("No uniq winner can be found!");
					}
				}
			}
		}
		
		return teams;
	}

}

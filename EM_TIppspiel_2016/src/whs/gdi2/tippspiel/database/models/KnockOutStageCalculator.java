package whs.gdi2.tippspiel.database.models;

import java.security.AllPermission;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Array;

import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.log.Log;

public class KnockOutStageCalculator {

	public static boolean RoundOfSixteen(ArrayList<TableRankingObject> tro_list) {
		try {
			ArrayList<Team[]> allGroups = new ArrayList<Team[]>();

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

			// Achtelfinale Spiel 1 (id 37) [2A:2C]
			String sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(0)[1].getTeamName()
					+ "', gastmannschaft='" + allGroups.get(2)[1].getTeamName()
					+ "' WHERE spieleid=37;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:37");

			// Achtelfinale Spiel 2 (id 38) [1B:3A/C/D]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(1)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(0)[2], allGroups.get(2)[2], allGroups.get(3)[2])[0].getTeamName()
					+ "' WHERE spieleid=38;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:38");
			
			// Achtelfinale Spiel 3 (id 39) [1D:3B/E/F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(3)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(1)[2], allGroups.get(4)[2], allGroups.get(5)[2])[0].getTeamName()
					+ "' WHERE spieleid=39;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:39");
			
			// Achtelfinale Spiel 4 (id 40) [1A:3C/D/E]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(0)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(2)[2], allGroups.get(3)[2], allGroups.get(4)[2])[0].getTeamName()
					+ "' WHERE spieleid=40;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:40");
			
			// Achtelfinale Spiel 5 (id 41) [1C:3A/B/F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(2)[0].getTeamName() + "', gastmannschaft='"
					+ drittbeste(allGroups.get(0)[2], allGroups.get(1)[2], allGroups.get(5)[2])[0].getTeamName()
					+ "' WHERE spieleid=41;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:41");
			
			// Achtelfinale Spiel 6 (id 42) [1F:2E]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(5)[0].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(4)[1].getTeamName()
					+ "' WHERE spieleid=42;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:42");
			
			// Achtelfinale Spiel 7 (id 42) [1E:2D]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(4)[0].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(3)[1].getTeamName()
					+ "' WHERE spieleid=43;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:43");
			
			// Achtelfinale Spiel 8 (id 42) [2B:2F]
			sql = "UPDATE spiele SET heimmannschaft='" + allGroups.get(1)[1].getTeamName() + "', gastmannschaft='"
					+ allGroups.get(5)[1].getTeamName()
					+ "' WHERE spieleid=44;";
			statement.executeUpdate(sql);
			Log.mysql("Successfully updated 'Achtelfinale' spieleid:44");

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
	
	// Halbfinale
	
	// Finale

}

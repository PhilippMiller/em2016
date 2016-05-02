import whs.gdi2.tippspiel.methods.jmm.*;

public class Main {

	public static void main(String[] args) {

		System.out.println(SQLConcerning.loadDriver());
		System.out.println(SQLConcerning.connectToLiveDB());
		System.out.println(SQLConcerning.connectToTestDB());
		System.out.println(SQLConcerning.createDB());
		System.out.println(SQLConcerning.createTables());
		System.out.println(SQLConcerning.addTestData());
		System.out.println(SQLConcerning.disconnect());

	}

}

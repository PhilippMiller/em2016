package whs.gdi2.tippspiel;

public enum RankingPoints {
	EXACT_AFTER_HALFTIME(6),
	EXACT_AFTER_REGULAR_GAME(11),
	TENDENCY_AFTER_HALFTIME(3),
	TENDENCY_AFTER_REGULAR_GAME(5),
	EXACT_AFTER_FULL_GAME(11),
	TENDENCY_AFTER_FULL_GAME(5),
	EXACT_AFTER_PENALTY(11),
	TENDENCY_AFTER_PENLTY_(5),
	CORRECT_AMOUNT_YELLOW_CARD(3),
	CORRECT_AMOUNT_RED_CARD_IF_AMOUNT_NE_ZERO(5);
	
	private int scoring;
	
	private RankingPoints(int scoring) {
		this.scoring = scoring;
	}
}

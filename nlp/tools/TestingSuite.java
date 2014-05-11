package nlp.tools;

public class TestingSuite {

	public static void main(String[] args) {
		
		NGram bigram = new NGram(); 
		String[] bigrams = bigram.getNGrams("Their only home league defeat so far this season came against Chelsea "
				+ "on 3 February. Sunderland are the only other side to have taken any points away from the Etihad in the "
				+ "2013-14 Premier League campaign, they drew 2-2 there last month.", 2);
	
		for(String abigram : bigrams) {
			System.out.println(abigram);
		}
	}
}

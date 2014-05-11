package nlp.tools;

public class TestingSuite {

	public static void main(String[] args) {
		
		NGram bigram = new NGram(); 
		String[] bigrams = bigram.getNGrams("a ha hou ha", 2);
	
		for(String abigram : bigrams) {
			System.out.println(abigram);
		}
	}
}

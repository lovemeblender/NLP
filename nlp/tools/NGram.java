package nlp.tools;

/*
 * Splits text into NGrams
 * Preprocessing is necessary prior to NGrams
 * to remove any unwanted punctuation, although some
 * punctuation is removed here as well.
 * Implemented by Michael Despotopoulos
 * May 2014
 */

public class NGram {
	
	// A collection that will hold the NGrams
	private String[] ngram;
	
	/*
	 *  Returns a String array
	 *  where each cell contains
	 *  an NGram. Throws LargeNException,
	 *  deliberately not caught by main example.
	 * 
	 */
	
	public String[] getNGrams(String text, int n)  {
		
		// Tokenizing with basic text preprocessing
		String[] tokens = text.split("[\\s.,]+");
		
		// Error checking
		if(n > tokens.length | n < 1 | text.length() < 1) {
			throw new IllegalArgumentException("Error: Check input arguments.");
		}
				
		ngram =  new String[tokens.length - (n - 1)];
		
		// Iterate through all text's tokens
		for(int i = 0; i < tokens.length - (n - 1); i++) {
			
			// remove nulls
			ngram[i] = "";
			
			int j = 0;
			// append NGrams
			while(j < n) {
				
				ngram[i] += tokens[i + j]; 
				ngram[i] += " ";
				j++;
			}
		}
		return ngram;
	}
}

package nlp.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseToDense {

	private List<String> trainingDense, features;
	private String delim;
	
	// Default delimiter blank space
	public SparseToDense() {
		this.delim = " ";
	}
	
	// Set delimiter
	public SparseToDense(String delim) {
		// If delimiter is not either ',' or blank
		// Sets delimiter to blank
		this.delim = delim;
	}
	
	public void readSparse(String filename) {
		BufferedReader br = null;
		String line = "";
		trainingDense = new ArrayList<String>();
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
			// For every line in sparse data
			while((line = br.readLine()) != null) {
				// Transform to dense and add to list of dense training examples
				trainingDense.add(sparseLinetoDense(line));
			}
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	public String sparseLinetoDense(String sparse) {
		
		features = new ArrayList<String>();
		StringBuilder dense = new StringBuilder("{");
		// Add tokens to list
		if(delim.equals(",")) {
			features = Arrays.asList(sparse.split("\\s*,\\s*"));
		}
		else {
			features = Arrays.asList(sparse.split("\\s"));
		}
		
		boolean first = true; // Do not append a ',' at the first attribute
		for(int i = 0; i < features.size(); i++) {
			if(!features.get(i).equals("0")) {
				if(first) {
					dense.append(i+" "+features.get(i));
					first = false;
				}
				else {
					dense.append(", "+i+" "+features.get(i));
				}
			}
		}
		dense.append("}");
		
		return dense.toString();
	}
	
	public void writeDense(String filename) {
		try {
			File file = new File(filename);
		 
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
		
		for(String s : trainingDense) {
			bw.write(s);
			bw.newLine();
		}
		
		bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		SparseToDense std = new SparseToDense();
		std.readSparse("data/out/wekaData");
		std.writeDense("data/out/wekaDataDense");
	}
	
}

package dictionaryLookUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class lookUpMain {

    public static void main(String[] args) {

    	int len = args.length;
		Path path = null;
		String pwd = "";
		File dictFile = null;
		if (len < 1) {
			try {
	            pwd = new java.io.File(".").getCanonicalPath();
			}
		    catch (IOException e) {
				e.printStackTrace();
			}
			path = Paths.get(pwd, "dictionary.txt");
			dictFile = path.toFile();
		}
		else {
			dictFile = new File(args[0]);
		}

		if (dictFile.exists() && !dictFile.isDirectory() && dictFile.isFile() && dictFile.canRead()) {

			String word = getWord();
			processWord(dictFile, word);

		}
		else {

			System.err.println("Error: " + dictFile.getPath() + " does not exists, it is not a regular file, or it cannont be read.");
			System.exit(1);

		}

	}

	public static String getWord() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = null;

        System.out.print("Enter a word: ");
	    try {
	        word = br.readLine();
	    }
	    catch (IOException ioe) {
	        System.err.println("getWord() IO error trying to read word, because:" + ioe.getLocalizedMessage());
	        System.exit(1);
	    }

	    word = word.toUpperCase();
		return word;

	}

	public static void processWord(File dictFile, String word) {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(dictFile));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = "";
		try {

			boolean wordFound = false;
			while ((line = br.readLine()) != null) {
				if (line.equals(word)) {
					wordFound = true;
					System.out.println(line);
					while((line = br.readLine()) != null) {
						if (line.matches("([A-Z])*") && !line.equals(word) && !line.equals(""))
							break;
						System.out.println(line);
					}
					if (line == null)
						break;
				}

			}
			if (!wordFound)
				System.out.println("Word not found. Perhaps you misspelled it.");

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
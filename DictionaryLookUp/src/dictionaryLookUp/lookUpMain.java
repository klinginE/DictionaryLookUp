package dictionaryLookUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
			System.out.println("Echo: " + word);
			processWord(dictFile, word);

		}
		else {

			System.err.println("Error: " + dictFile.getPath() + " does not exists or it is not a regular file or it cannont be read.");
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

		Scanner scanner = new Scanner(dictFile.getAbsolutePath());
		while (scanner.hasNextLine()) {

			String line = scanner.nextLine().trim();
			System.out.print(line);

		}

	}

}
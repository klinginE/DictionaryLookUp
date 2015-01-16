package dictionaryLookUp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class lookUpMain {

    public static void main(String[] args) {

    	int len = args.length;
		Path path = null;
		String pwd = "";
		File dictFile = null;
		if (len < 2) {
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
			dictFile = new File(args[1]);
		}

		if (dictFile.exists() && !dictFile.isDirectory()) {

			String word = getWord();
			processWord(dictFile);

		}
		else {

			System.err.println("Error: " + dictFile.getPath() + " does not exists or it is not a regular file");
			System.exit(1);

		}

	}

	public static String getWord() {
		return "";
	}

	public static void processWord(File dictFile) {
		
	}

}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Hari
 */
public class Naive{
	private static void compare(String fileName) throws IOException {
		BufferedReader patBr = new BufferedReader(new FileReader(
				"C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\p.txt"));
		String pattern, input;
		

		while ((pattern = patBr.readLine()) != null) {
			// process line.
			BufferedReader inputBr = new BufferedReader(
					new FileReader(fileName));
			int i = 0;
			while ((input = inputBr.readLine()) != null) {
				i++;
				Naive n = new Naive(input, pattern, i, fileName);
			}
			inputBr.close();
		}

		patBr.close();
	}
	//function to match pattern and existing files, takes files as input
	public Naive(String text, String pat, int line, String fileName) {
		for (int i = 0; i < text.length() - pat.length() + 1; i++) {
			int match = 1;
			for (int j = 0; j < pat.length(); j++) {
				if (text.charAt(i + j) != pat.charAt(j)) {
					match = 0;
				}
			}
			if (match == 1) {
				System.out
						.println("\n" + fileName + ": '" + pat
								+ "' Match found at " + i
								+ " line no " + line);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		File folder = new File(
				"C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\");
		File[] filelist = folder.listFiles();
		long start = System.currentTimeMillis();
		//loop through files in folder
		for (File file1 : filelist) {
			compare("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\"
					+ file1.getName());
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Total Time taken:" + end + "ms");
	}

}
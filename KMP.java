//Reference : http://www.sanfoundry.com/java-program-knuth-morris-pratt-algorithm/
//Reference : http://tekmarathon.com/2013/05/14/algorithm-to-find-substring-in-a-string-kmp-algorithm/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class KMP {
	//compare files pattern and text
    private static void comapareFiles(String file)  throws IOException{
        BufferedReader patArray = new BufferedReader(new FileReader("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\p.txt"));
        String pat = null;
        String input = null;
        long start = System.currentTimeMillis();
        //passing diff types of files in a function
        compareFilesLoop(file, pat, input, patArray);
        long time = System.currentTimeMillis() - start;
        System.out.println("Time required to run on files:"+time+"ms");
        patArray.close();
    }
    
    private static void compareFilesLoop(String file, String pat, String input, BufferedReader patArray) throws IOException
    {
    	//compare pattern file line in text file.
    	while ((pat = patArray.readLine()) != null) {
            BufferedReader inputRead = new BufferedReader(new FileReader(file));
            int i = 0;
            //Text file.
            while ((input = inputRead.readLine()) != null) {
                i++;
                KMP kmp = new KMP(input, pat, i, file);
            }
            inputRead.close(); 
        }
    }

    private int[] fail;

    public KMP(String text, String pattern, int ln, String file) {        
    	fail = new int[pattern.length()];
    	int n = pattern.length();
    	//Check for failure
        fail[0] = -1;
        for (int j = 1; j < n; j++) {
            int i = fail[j - 1];
            while ((pattern.charAt(j) != pattern.charAt(i + 1)) && i >= 0) {
                i = fail[i];
            }
            if (pattern.charAt(j) == pattern.charAt(i + 1)) {
            	fail[j] = i + 1;
            } else {
            	fail[j] = -1;
            }
        }
        //position match to find.
        int index = posMatch(text, pattern);
        if (index != -1) {
            System.out.println("\n"+file+": '" + pattern + "' Match found at index " + index + " at line no " + ln);
        }
    }
   
    private int posMatch(String text, String pat) {
        int lens = text.length();
        int lenp = pat.length();
        return matchWhile(lens, lenp, text, pat);
    }

   //Match the file.
    private int matchWhile( int lens, int lenp, String text, String pat)
    {
    	int i = 0, j = 0;
    	while (i < lens && j < lenp) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = fail[j - 1] + 1;
            }
        }
    	return ((j == lenp) ? (i - lenp) : -1);
    }
    
    public static void main(String[] args) throws IOException {
         File folder = new File("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\");
		File[] listOfFiles = folder.listFiles();
		for (File file1 : listOfFiles){
                     if (file1.isFile()){
        comapareFiles("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\"+file1.getName());
                     }
                }
    }

}

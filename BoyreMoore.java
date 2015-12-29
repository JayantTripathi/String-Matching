
//Ref : source: http://www.codecodex.com/wiki/index.php?title=Boyer-Moore_Algorithm_Examples&action=edit&section=2

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BoyreMoore {
    
    private static void comapareFiles(String fileName) throws IOException {
    	//Function for doing the match pattern by line of each
    	//pattern to each line of text file.
    	long start = System.currentTimeMillis();
        String ptnText = null;
        String ipText = null;
        BufferedReader pattern = new BufferedReader(new FileReader("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\p.txt"));
        //While for each pattern line
        ptnText = pattern.readLine();
        while ( ptnText != null) {
        	//get the text file.
            BufferedReader text = new BufferedReader(new FileReader(fileName));
            int i = 0;
            //while each text line
            ipText = text.readLine();
            while (ipText != null) {
                i++;
                List<Integer> matchedItem = buildPattern(ptnText, ipText);
                for (Integer positionLine : matchedItem) {
                    System.out.println("\n"+fileName+": '" + ptnText + "' There was match found at position " + positionLine + " in line no " + i);
                }
                ipText = text.readLine();
            }
            text.close();
            ptnText = pattern.readLine();
        }
        long Time = System.currentTimeMillis() - start;
        System.out.println( "Time taken to complete: "+Time+"ms");
        pattern.close();
    }

    public static List<Integer> buildPattern(String patFile, String txtFile) {
    	//Matching the build pattern with text.
        List<Integer> matchedItem = new ArrayList<Integer>();
        //Length of text file
        int xIndex = txtFile.length();
        //Length of Pattern File
        int yIndex = patFile.length();
        //Index of the char shift needed
        Map<Character, Integer> rIndex = CharShift(patFile);
        int pos = 0;
        //position of the each letter and shifting each of them.
        MatchedItem(patFile, txtFile, matchedItem, xIndex, yIndex, rIndex, pos);
        
        return matchedItem;
    }

    public static List<Integer> MatchedItem(String patFile, String txtFile, List<Integer> matchedItem, int xIndex, int yIndex, Map<Character, Integer> rIndex, int pos)
	{
	    while (pos + (yIndex - 1) < xIndex) {
	    	//position of the each letter and shifting each of them.
	        for (int indPat = yIndex - 1; indPat >= 0; indPat--) {
	        	//The index of an array
	            int indTxt = pos + indPat;
	            char charTemp = txtFile.charAt(indTxt);
	            char charPos = patFile.charAt(indPat);
	            if (charTemp != charPos) {
	                Integer r = rIndex.get(charTemp);
	                if (r == null) {
	                	pos = indTxt + 1;
	                } else {
	                    int sftPos = indTxt - (pos + r);
	                    pos += sftPos > 0 ? sftPos : 1;
	                }
	                break;
	            } 
	            if (indPat == 0) {
	            	matchedItem.add(pos);
	            	pos++;
	            }
	            if (indTxt >= xIndex) {
	                break;
	            }
	        }
	    }
	    return matchedItem;
	}
    
    
    private static Map<Character, Integer> CharShift(
            String pattern) {
        
        Map<Character, Integer> mapList = new HashMap<Character, Integer>();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char c = pattern.charAt(i);
            if (!mapList.containsKey(c)) {
            	mapList.put(c, i);
            }
        }
        return mapList;
    }

    public static void main(String[] args) throws IOException {
    	File folder = new File("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\");
		File[] listOfFiles = folder.listFiles();
		for (File file1 : listOfFiles){
			comapareFiles("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\"+file1.getName());
		}
    }
}
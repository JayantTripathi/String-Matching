
//reference: http://www.sanfoundry.com/java-program-rabin-karp-algorithm/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RabinKarp_new {

   
    private String pattern; 
    private long patternHash;    
    private int A;  
    private long B; 
    private int C;   
    private long RM;  
    int counter = 0;
    
    private long hashtable(String keycode, int A)
    { 
        long h = 0; 
        for (int j = 0; j < A; j++) 
            h = (C * h + keycode.charAt(j)) % B; 
        return h; 
    } 
    public RabinKarp_new(String text, String pattern, int lineNumber) 
    { this.pattern = pattern;    C = 256;        A = pattern.length();        B = longPrimecode();        RM = 1;
        for (int i = 1; i <= A-1; i++)
           RM = (C * RM) % B;
        patternHash = hashtable(pattern, A);
        int pos = srchstring(text);
        if (pos == -1)
        {
        }
        else
        {
            System.out.println("Pattern: "+pattern+" found at position : "+ pos+" at line No:"+lineNumber);
        }
    } 
    private int srchstring(String text) 
    {
        int N = text.length(); 
        if (N < A) return -1;
        long txtHash = hashtable(text, A); 
        if ((patternHash == txtHash) && checkstring(text, 0))
            return 0;
         for (int i = A; i < N; i++) 
        {
            txtHash = (txtHash + B - RM * text.charAt(i - A) % B) % B;       txtHash = (txtHash * C + text.charAt(i)) % B;             int offset = i - A + 1;
            if ((patternHash == txtHash) && checkstring(text, offset))
                return offset;
        }
       
        return -1;
    }
   private boolean checkstring(String text, int i) 
    {
        for (int j = 0; j < A; j++) 
            if (pattern.charAt(j) != text.charAt(i + j)) 
                return false; 
        return true;
    }
    private static long longPrimecode() 
    {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
    public static void main(String[] args) {
        StringBuffer stringBufferText = null;
        StringBuffer stringBufferPattern = null;
        int nooflines = 0;

        List<String> linesa = new LinkedList<String>();
        List<String> linesb = new LinkedList<String>();
        long startTime = System.currentTimeMillis();
        try {
            File file = new File("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\paper3_10000.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            stringBufferText = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    stringBufferText.append(line);
                    stringBufferText.append("\n");
                    linesa.add(line);
                    nooflines = nooflines + 1;
                }

            }
            fileReader.close();
            System.out.println("Contents of file:");
            System.out.println(stringBufferText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            File file = new File("C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\p.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            stringBufferPattern = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    stringBufferPattern.append(line);
                    stringBufferPattern.append("\n");
                    linesb.add(line);
                }
            }
            fileReader.close();
            System.out.println("Contents of file:");
            System.out.println(stringBufferPattern.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int m = linesa.size(), n = linesb.size();
       for(int i=0;i<n;i++)
       {
           for(int j=0;j<m;j++)
           {
               RabinKarp_new rk = new RabinKarp_new(linesa.get(j), linesb.get(i),j+1);        
           }
       }
    }
}

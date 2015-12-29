import java.io.*;
import java.util.*;

public class lcs {

	public static void main(String args[]) throws Exception {
		File folder = new File(
				"C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\");
		File[] filelist = folder.listFiles();
		//looping through all files in folder
		for (File file1 : filelist) {
			if (file1.isFile()) {
				int i, j, v = 0, k, longest = 0;
				StringBuffer stringBufa = null;
				StringBuffer stringBufb = null;
				int numberoflines = 0;
				List<String> la = new LinkedList<String>();
				List<String> lb = new LinkedList<String>();
				long start = System.currentTimeMillis();
				try {
					File file = new File(
							"C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\p.txt");
					FileReader f = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(
							f);
					stringBufa = new StringBuffer();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						line = line.trim();
						if (!line.equals("")) {
							stringBufa.append(line);
							stringBufa.append("\n");
							la.add(line);
							numberoflines = numberoflines + 1;
						}
					}
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					File file = new File(
							"C:\\apache-tomcat-8.0.26\\Algo_5311\\src\\original\\"
									+ file1.getName());
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(
							fileReader);
					stringBufb = new StringBuffer();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						line = line.trim();
						if (!line.equals("")) {
							stringBufb.append(line);
							stringBufb.append("\n");
							lb.add(line);
						}
					}
					fileReader.close();
					System.out.println("File:" + file1.getName());

				} catch (IOException e) {
					e.printStackTrace();
				}
				//compare
				int m = la.size();
				int n = lb.size();
				ArrayList<Object> lcs = new ArrayList<Object>();
				int l[][] = new int[m + 1][n + 1];
				for (i = 0; i < m; i++) {
					for (j = 0; j < n; j++) {
						if (la.get(i).compareTo(lb.get(j)) == 0) {
							v = l[i][j] + 1;
							l[i + 1][j + 1] = v;
						} else {
							l[i + 1][j + 1] = Math
									.max(l[i + 1][j], l[i][j + 1]);
						}
					}
				}
				for (int x = m, y = n; x != 0 && y != 0;) {
					if (l[x][y] == l[x - 1][y]) {
						x--;
					} else if (l[x][y] == l[x][y - 1]) {
						y--;
					} else {
						assert (la.get(x - 1).compareTo(lb.get(y - 1)) == 0);
						{
							lcs.add(la.get(x - 1));
							x--;
							y--;
						}
					}
				}
				Collections.reverse(lcs);
				long end = System.currentTimeMillis();
				long total = end - start;
				System.out.println("Time:" + total + "ms");
			}
		}
	}
}
/*
References:
https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_subsequence
*/
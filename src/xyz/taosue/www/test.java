package xyz.taosue.www;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		 ReadFiles rf = new ReadFiles();
		 CutWord cw = new CutWord();
		 TfIdf tfidf = new TfIdf();
		 String[] r = cw.cutWord(rf.readFiles("F:\\demo\\渔业.txt"));
		 HashMap<String, Float> rr=tfidf.tf(r);
		 for(String w:rr.keySet()){
			 System.out.println(w+" "+rr.get(w));
		 }
		// Map<String, HashMap<String, Float>> file = tfidf.tfOfAll("F:\\demo");
		// Map<String, HashMap<String, Float>> file2 = tfidf.tfidf("F:\\demo");
		// for (String article : file2.keySet()) {
		// int i = 0;
		// HashMap<String, Float> art = file2.get(article);
		// System.out.println("========" + article + "==========");
		// for (String word : art.keySet()) {
		// System.out.println(word + ":" + art.get(word));
		// i++;
		// }
		// System.out.println("一共" + i);
		// }
		// System.out.println("========================================");
		// int i = 0;
		// for (String word : file2.keySet()) {
		// System.out.println(word + ":" + file2.get(word));
		// i++;
		// }
		// System.out.println("一共" + i);
//		Distance dist = new Distance("F:\\demo");
//		ArrayList<String> titile = new ArrayList<String>(dist.tfidfSet.keySet());
//		// System.out.println(dist.Dist("F:\\demo\\林业.txt",
//		// "F:\\demo\\农业.txt"));
//		float[][] theresult = dist.DistOfAll();
//		for (String t : titile) {
//			System.out.print(t + " ");
//		}
//		System.out.println();
//		for (int i = 0; i < theresult.length; i++) {
//			System.out.print(titile.get(i) + " ");
//			for (int j = 0; j < theresult[0].length; j++) {
//
//				System.out.print(theresult[i][j] + " ");
//			}
//			System.out.println();
//		}
		// Scanner scan = new Scanner(System.in);
		// String text = scan.next();
		// Search s = new Search();
		// s.searchSentence(text);
	}

}

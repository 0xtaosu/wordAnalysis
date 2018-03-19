package xyz.taosue.www;

import java.util.HashMap;
import java.util.Map;

public class Distance {
	int dist;
	Map<String, HashMap<String, Float>> tfidfSet;

	Distance(String path) {
		TfIdf tfidf = new TfIdf();
		tfidfSet = tfidf.tfidf(path);
	}

	public float Dist(String file1, String file2) {

		HashMap<String, Float> f1 = tfidfSet.get(file1);
		HashMap<String, Float> f2 = tfidfSet.get(file2);
		HashMap<String, Float> newF1 = extracted(f1);
		HashMap<String, Float> newF2 = extracted(f2);

		for (String word1 : f1.keySet()) {
			int n = 0;
			for (String word2 : f2.keySet()) {
				if (word1.equals(word2)) {
					n++;
				}
			}

			if (n == 0) {
				newF2.put(word1, (float) 0);
			}
		}
		for (String word2 : f2.keySet()) {
			int n = 0;
			for (String word1 : f1.keySet()) {
				if (word2.equals(word1)) {
					n++;
				}
			}

			if (n == 0) {
				newF1.put(word2, (float) 0);
			}
		}

		//
		float top = 0;
		float bottom1 = 0;
		float bottom2 = 0;

		for (String w1 : newF1.keySet()) {
			float one = newF1.get(w1);
			float two = newF2.get(w1);
			top += one * two;
		}
		for (String w1 : newF1.keySet()) {
			bottom1 += newF1.get(w1) * newF1.get(w1);
		}
		for (String w2 : newF2.keySet()) {
			bottom2 += newF2.get(w2) * newF2.get(w2);
		}
		float bottom = (float) (Math.sqrt(bottom1) * Math.sqrt(bottom2));
		float dist = top / bottom;
		return dist;
	}

	private HashMap<String, Float> extracted(HashMap<String, Float> f1) {
		return (HashMap<String, Float>) f1.clone();
	}

	public float[][] DistOfAll() {
		float[][] dist = new float[tfidfSet.size()][tfidfSet.size()];
		int i = 0;
		for (String art1 : tfidfSet.keySet()) {
			int j = 0;
			for (String art2 : tfidfSet.keySet()) {
				dist[i][j] = Dist(art1, art2);
				j++;
			}
			i++;
		}
		return dist;

	}

}

package xyz.taosue.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TfIdf {
	public HashMap<String, Float> tf(String[] cutwords) {
		HashMap<String, Float> tf = new HashMap<>();
		int wordNum = cutwords.length;
		for (int i = 0; i < wordNum; i++) {
			int wordtf = 0;
			for (int j = 0; j < wordNum; j++) {
				if (cutwords[i] != "" && i != j) {
					if (cutwords[i].equals(cutwords[j])) {
						cutwords[j] = "";
						wordtf++;
					}
				}
			}
			if (cutwords[i] != "") {
				String key = cutwords[i];
				float value = ((float) ++wordtf) / wordNum;
				tf.put(key, value);
				cutwords[i] = "";
			}
		}
		return tf;
	}

	public Map<String, HashMap<String, Float>> tfOfAll(String filepath) {
		HashMap<String, HashMap<String, Float>> allTheTf = new HashMap<>();
		ReadFiles rfs = new ReadFiles();
		CutWord cw = new CutWord();
		List<String> fileList = rfs.readDirs(filepath);
		for (String file : fileList) {
			HashMap<String, Float> dict;
			dict = tf(cw.cutWord(rfs.readFiles(file)));
			allTheTf.put(file, dict);
		}
		return allTheTf;
	}

	public Map<String, HashMap<String, Float>> idf(String filepath) {
		Map<String, HashMap<String, Float>> path = this.tfOfAll(filepath);
		Map<String, HashMap<String, Float>> idf = new HashMap<String, HashMap<String, Float>>();
		float Dt = path.size();

		for (String art : path.keySet()) {
			HashMap<String, Float> article = path.get(art);
			HashMap<String, Float> doc = new HashMap<String, Float>();
			for (String word : article.keySet()) {
				float num = 0;
				for (String a : path.keySet()) {
					if (path.get(a).containsKey(word)) {
						num++;
					}
				}
				doc.put(word, (float) Math.log10(Dt / num));
			}
			idf.put(art, doc);
		}
		return idf;
	}

	public Map<String, HashMap<String, Float>> tfidf(String filepath) {
		HashMap<String, HashMap<String, Float>> tfidf = new HashMap<>();
		Map<String, HashMap<String, Float>> idf = this.idf(filepath);
		Map<String, HashMap<String, Float>> tf = this.tfOfAll(filepath);
		for (String file : tf.keySet()) {
			HashMap<String, Float> doc = new HashMap<>();
			Map<String, Float> singelFile = tf.get(file);
			for (String word : singelFile.keySet()) {
				float ifidfValue = tf.get(file).get(word) * idf.get(file).get(word);
				doc.put(word, ifidfValue);
			}
			tfidf.put(file, doc);
		}
		return tfidf;

	}
}

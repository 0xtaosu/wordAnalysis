package xyz.taosue.www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Search {
	HashMap<String, Float> result = new HashMap<String, Float>();

	Map<String, HashMap<String, Float>> tfidfSet;

	Search() {
		TfIdf tfidf = new TfIdf();
		tfidfSet = tfidf.tfidf("F:\\demo");
	}

	public void search(String key) {
		for (String title : tfidfSet.keySet()) {
			HashMap<String, Float> article = tfidfSet.get(title);
			float tfidf = 0;
			for (String word : article.keySet()) {
				if (word.equals(key)) {
					tfidf = article.get(word);
					result.put(title, tfidf);
					break;
				}
			}
		}
		List<Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(result.entrySet());
		Collections.sort(list, (mapping1, mapping2) -> {
            if (mapping1.getValue() > (mapping2.getValue())) {
                return -1;
            } else if (mapping1.getValue() == (mapping2.getValue())) {
                return 0;
            } else {

                return 1;
            }
        });
		Map<String, Object> sortResult = new LinkedHashMap<>();
		for (Map.Entry<String, Float> entry : list) {
			sortResult.put(entry.getKey(), entry.getValue());
		}
		if (!sortResult.isEmpty()) {
			for (String word : sortResult.keySet()) {
				System.out.println(word + ":" + sortResult.get(word));
			}
		} else {
			System.out.println("");
		}

	}

	public void searchSentence(String key) {
		CutWord cw = new CutWord();
		String[] cwResult = cw.cutWord(key);

		HashMap<String, Float> calc = new HashMap<String, Float>();

		for (String article : tfidfSet.keySet()) {
			HashMap<String, Float> WordSet = tfidfSet.get(article);
			float value = 0;
			for (int i = 0; i < cwResult.length; i++) {
				for (String word : WordSet.keySet()) {
					if (word.equals(cwResult[i])) {
						value += WordSet.get(word);
					}
				}
			}
			calc.put(article, value);
		}

		List<Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(calc.entrySet());
		Collections.sort(list, (mapping1, mapping2) -> {
            if (mapping1.getValue() > (mapping2.getValue())) {
                return -1;
            } else if (mapping1.getValue() == (mapping2.getValue())) {
                return 0;
            } else {

                return 1;
            }
        });
		Map<String, Object> sortResult = new LinkedHashMap<>();
		for (Map.Entry<String, Float> entry : list) {
			sortResult.put(entry.getKey(), entry.getValue());
		}
		if (!sortResult.isEmpty()) {
			for (String word : sortResult.keySet()) {
				System.out.println(word + ":" + sortResult.get(word));
			}
		} else {
			System.out.println("");
		}

		
	}
}

package xyz.taosue.www;

import java.util.Iterator;
import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

/**
 * 
 * @author tao
 *
 */
public class CutWord {

	private String words;

	public String getWord() {
		return words;
	}

	public void setWord(String words) {
		this.words = words;
	}

	public String[] cutWord(String words) {
		this.words = words;
		String[] cutwordResult = null;
		List<Word> w = WordSegmenter.seg(words);
		cutwordResult = new String[w.size()];
		int i = 0;
		for (Iterator<Word> iterator = w.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			cutwordResult[i] = word.getText();
			i++;
		}
		return cutwordResult;
	}
}

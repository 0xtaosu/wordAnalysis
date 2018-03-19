package xyz.taosue.www;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 * 
 */
public class ReadFiles {
	private String file;

	private String filepath;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String readFiles(String file) {
		this.file = file;
		StringBuffer sb = new StringBuffer();
		InputStreamReader is = null;
		BufferedReader br = null;
		String line = null;
		try {
			is = new InputStreamReader(new FileInputStream(file), "gbk");
		} catch (UnsupportedEncodingException e) {
			System.out.println("");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("");
			e.printStackTrace();
		}
		br = new BufferedReader(is);
		try {
			line = br.readLine();

			while (line != null) {
				sb.append(line).append("\r\n");
				line = br.readLine();
			}
			br.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public List<String> readDirs(String filepath) {
		this.filepath = filepath;
		List<String> fileList = new ArrayList<String>();
		File file = new File(filepath);
		if (!file.isDirectory()) {
			System.out.println("");
			System.out.println("filepath: " + file.getAbsolutePath());
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					fileList.add(readfile.getAbsolutePath());
				} else if (readfile.isDirectory()) {
					readDirs(filepath + "\\" + filelist[i]);
				}
			}
		}
		return fileList;
	}
}

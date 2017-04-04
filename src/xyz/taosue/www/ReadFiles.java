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
	// 文件路径
	private String file;

	// 文件夹路径
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

	// 读取文件
	public String readFiles(String file) {
		this.file = file;
		StringBuffer sb = new StringBuffer();
		InputStreamReader is = null;
		BufferedReader br = null;
		String line = null;
		try {
			is = new InputStreamReader(new FileInputStream(file), "gbk");
		} catch (UnsupportedEncodingException e) {
			System.out.println("编码错误，请使用gbk编码");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件");
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

	// 读取文件夹下的文件
	public List<String> readDirs(String filepath) {
		this.filepath = filepath;
		List<String> fileList = new ArrayList<String>();
		File file = new File(filepath);
		if (!file.isDirectory()) {
			System.out.println("输入的参数应该为[文件夹名]");
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

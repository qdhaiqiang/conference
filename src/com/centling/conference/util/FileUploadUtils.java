package com.centling.conference.util;

import java.io.File;

public class FileUploadUtils {

	/**
	 * 根据文件的目录，直接删除文件
	 * @param path 文件所在的目录，包括文件目录和文件名
	 * @return
	 */
	public static boolean deletefileBypath(String path){
		boolean isDel = false;
		File file = new File(path);
		if(file.exists() && file.isFile()){
			file.delete();
			isDel = true;
		}
		return isDel;
	}
	
	/**
	 * 文件夹目录下的所有文件，进行模糊匹配，如果可以匹配，则删除该文件
	 * @param path 文件所在目录，不包括文件名
	 * @param samename 需要迷糊匹配的文件名，根据文件名字从第一个字符开始匹配
	 * @return
	 */
	public static boolean deleteFileByFuzzyMatch(String path,String samename){
		boolean isDel = false;
		File file = new File(path);
		//是个目录并且是个文件夹，不是直接去匹配的文件
		if(file.exists() && file.isDirectory()){
			File[] tempFile = file.listFiles();
			for (File file2 : tempFile) {
				if(file2.isFile() && file2.getName().startsWith(samename)){
					file2.delete();
					isDel = true;
				}
			}
		}
		
		return isDel;
	}
}

package com.fangj;

import java.io.*;

/**
 * Created by fangj on 2015/5/20.
 */
public class SplitHandleWithRegex {

	/*
	 * js split 正则表达式过滤引号中的逗号 var s = "1,2,'a,b',3"; var a = s.split(",");
	 * 得到的数组是 1 3 a,b 3
	 */
	// 第一种：s.split(",(?=(?:[^']*(?:'[^']*')?[^']*)*$)")，不过效率可能有点低，如果只是少量字符串应该可以。这是正则的方法。

	// 第二种：var a = eval("["+s+"]");

	public static void main(String[] args) throws IOException {
		FileInputStream f = new FileInputStream(SplitHandleWithRegex.class
				.getResource("/test.txt").getPath());
		BufferedReader dr = new BufferedReader(new InputStreamReader(f));
		String tmp = "";
		while ((tmp = dr.readLine()) != null) {
			String[] arr = tmp.split(",(?=(?:[^\"]*(?:\"[^\"]*\")?[^\"]*)*$)");
			for (int i = 0; i < arr.length; i++) {
				String str = arr[i];
				if (str.startsWith("\"") && str.endsWith("\"")) {
					str = arr[i].substring(1, arr[i].length() - 1);
				}
				System.out.print(str + "  ");
			}
		}
		dr.close();
	}

}

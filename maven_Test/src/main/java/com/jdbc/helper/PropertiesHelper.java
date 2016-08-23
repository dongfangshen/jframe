package com.jdbc.helper;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesHelper {
	// 根据Key读取Value
	public static String GetValueByKey(String filePath, String key) {
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);
			String value = pps.getProperty(key);
			System.out.println(key + " = " + value);
			return value;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 读取Properties的全部信息
	public static void GetAllProperties(String filePath) throws IOException {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		Enumeration en = pps.propertyNames(); // 得到配置文件的名字

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}

	}
	
	//写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
       Properties pps = new Properties();
       
       InputStream in = new FileInputStream(filePath);
       //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
       //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }
}

package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

import entity.BookInfo;
import entity.User;

/**
 * 文件工具类
 * 
 * @author mi
 *
 */
public class FileUtil {

	private static final String BookInfoFilePath = "BookInfo.dat";// 相对本程序的工程根目录

	private static final String UserFilePath = "User.dat";

	public static void SaveUserSet(Set<User> userSet) {
		SaveObject(userSet, UserFilePath);
	}

	public static Set<User> ReadUserSet() {
		Object obj = ReadObject(UserFilePath);
		if (obj == null) {
			System.out.println("没读取到内容");
			return null;
		}
		return (Set<User>) obj;
	}

	/**
	 * 将传入的BookInfo的map信息存放在对应的文件中
	 * 
	 * @param bookInfoMap
	 */
	public static void SaveBookInfoMap(Map<String, BookInfo> bookInfoMap) {
		SaveObject(bookInfoMap, BookInfoFilePath);
	}

	public static Map<String, BookInfo> ReadBookInfoMap() {
		Object obj = ReadObject(BookInfoFilePath);
		if (null == obj)
			return null;
		return (Map<String, BookInfo>) obj;
	}

	public static void SaveObject(Object object, String filePath) {
		try (FileOutputStream fout = new FileOutputStream(filePath, false);
				ObjectOutputStream out = new ObjectOutputStream(fout);) {
			out.writeObject(object);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Object ReadObject(String filePath) {
		try (FileInputStream fin = new FileInputStream(filePath); ObjectInputStream in = new ObjectInputStream(fin);) {
			return in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}
		return null;

	}
}

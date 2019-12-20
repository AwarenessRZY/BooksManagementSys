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
 * �ļ�������
 * 
 * @author mi
 *
 */
public class FileUtil {

	private static final String BookInfoFilePath = "BookInfo.dat";// ��Ա�����Ĺ��̸�Ŀ¼

	private static final String UserFilePath = "User.dat";

	public static void SaveUserSet(Set<User> userSet) {
		SaveObject(userSet, UserFilePath);
	}

	public static Set<User> ReadUserSet() {
		Object obj = ReadObject(UserFilePath);
		if (obj == null) {
			System.out.println("û��ȡ������");
			return null;
		}
		return (Set<User>) obj;
	}

	/**
	 * �������BookInfo��map��Ϣ����ڶ�Ӧ���ļ���
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

package biz;

import entity.Book;
import entity.BookInfo;

public interface BookInfoBiz extends Biz<BookInfo> {
	
	/**
	 * ����
	 * @param isbn
	 * @param outCount
	 * @return
	 */
	public boolean outStore(String isbn, int outCount);
	/**
	 * ���
	 * @param isbn
	 * @param inCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount);
}

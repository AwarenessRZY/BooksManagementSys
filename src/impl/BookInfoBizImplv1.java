package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import biz.BookInfoBiz;
import entity.Book;
import entity.BookInfo;
import util.FileUtil;

/**
 * BookInfoBiz第一版实现类
 * @author mi
 *
 */
public class BookInfoBizImplv1 implements BookInfoBiz{
	private static final long serialVersionUID = 3791313640142683341L;

	@Override
	public boolean add(BookInfo bookInfo) {
		if(bookInfo == null) return false;
		Map<String,BookInfo> bookInfoMap = findAll();
		if(bookInfoMap.containsKey(bookInfo.getIsbn())) return false;
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean delete(BookInfo bookInfo) {
		if(bookInfo == null) return false;
		Map<String,BookInfo> bookInfoMap = findAll();
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())) return false;
		bookInfoMap.remove(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
		
	}

	@Override
	public BookInfo update(BookInfo bookInfo) {
		if(bookInfo == null) return null;
		Map<String,BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return null;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())) {
			add(bookInfo);
			return bookInfo;
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return bookInfo;
	}

	@Override
	public BookInfo searchByIsbn(String isbn) {
		Map<String,BookInfo> bookInfoMap = findAll();
		if(bookInfoMap == null) return null;
		if(!bookInfoMap.containsKey(isbn)) return null;
		
		return bookInfoMap.get(isbn);
	}

	@Override
	public Map<String, BookInfo> findAll() {
		
		return FileUtil.ReadBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		BookInfo bookInfo = searchByIsbn(isbn);
		if(bookInfo == null) return false;
		if(bookInfo.getInStoreCount() < outCount) return false;
		bookInfo.setInStoreCount(bookInfo.getInStoreCount() - outCount);
		update(bookInfo);
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		BookInfo bookInfo = searchByIsbn(isbn);
		if(bookInfo == null) {
			BookInfo bookInfo2 =new BookInfo();
			bookInfo2.setIsbn(isbn);
			bookInfo2.setInStoreCount(inCount);
			add(bookInfo2);
			return true;
			}
		bookInfo.setInStoreCount(bookInfo.getInStoreCount() + inCount);
		update(bookInfo);
		return true;
	}

	@Override
	public BookInfo searchById(String id) {
		Map<String,BookInfo> bookInfoMap = findAll();
		for(Entry<String,BookInfo> entry : bookInfoMap.entrySet()) {
			BookInfo bookInfo = entry.getValue();
			List<Book> list = bookInfo.getBookList();	
			if(list == null) {continue;}
				for(Book book : list) {
					if(book.getBookId().equals(id)) return bookInfo;
				}
			}
			
		System.out.println("没有该bookID的图书！");
		return null;
	}

}

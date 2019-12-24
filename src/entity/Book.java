package entity;

import java.io.Serializable;

/**
 * 真实的图书对象
 * @author mi
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = 440845979976865893L;
	private String isbn;
	private String bookId;	//图书馆对于每一本书的内部编号
	private BookInfo bookInfo;
	private BookState state;
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Book)) return false;
		Book book = (Book) obj;
		
		return bookId.equals(book.getBookId());
	}
	
	@Override
	public int hashCode() {

		return 0;
	}
	
	public BookInfo getBookInfo() {
		return bookInfo;
	}


	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}


	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public BookState getState() {
		return state;
	}
	public void setState(BookState state) {
		this.state = state;
	}
	
	
}


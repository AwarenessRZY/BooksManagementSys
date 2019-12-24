package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import biz.BizFactory;
import biz.BookInfoBiz;
import impl.BookInfoBizImplv1;
import util.FileUtil;

/**
 * 以图书的ISBN为主键的图书信息类
 * 某一个ISBN会对应很多本真实存在的图书对象
 * attributess:ISBN、类别、作者、出版社、库存量、出版时间、单价
 * @author mi
 *
 */

public class BookInfo implements Serializable{
	private static final long serialVersionUID = 7140774242247686558L;
	private String isbn;
	private String name;
	private String type;
	private String author;
	private String publisher;
	private int inStoreCount;
	private Date publishDate;
	private double price;
	private List<Book> bookList;
	public BookInfoBiz  biz;
	//定义构造方法，重写toString,equals等操作，用的时候再来完成
	
	public BookInfo() {
		biz= (BookInfoBiz) BizFactory.getBiz("BookInfoBiz");
	}
	
	public void addBook(Book book) {
		if(bookList == null) bookList = new ArrayList<Book>();
		if(book == null) return;
		if(!isbn.equals(book.getIsbn())) return;
		if(bookList.contains(book)) return;
		book.setBookInfo(this);
		bookList.add(book);
		biz.update(this);
	}
	
	public void showBooks() {
		System.out.println("*********所有该种图书的状态信息***********");
		for(Book book:bookList ) {
			System.out.println("图书名：" + name + "\tIsbn:" + isbn + "\t该书状态:" + book.getState());
		}
	}
	
	public Book searchByBookId(String bookId) {
		for(Book book : bookList) {
			if(book.getBookId().equals(bookId)) {
				System.out.println("图书名：" + name + "\tIsbn:" + isbn + "\t该书状态:" + book.getState());
				return book;
			}
		}	
		return null;
	}
	
	public void updateBooks(String bookId, BookState state) {
		int i = 0;
		for(; i < bookList.size();) {
			if(bookList.get(i).getBookId().equals(bookId)) {
				bookList.get(i).setState(state);
				System.out.println("该书的状态更新成功！");
				biz.update(this);
				break;
			}
			i++;
			if(i == bookList.size())
				System.out.println("没有这个内部ID的图书！");
		}
			}
	
	public void deleteBooks(String bookId) {
		int i = 0;
		for(; i < bookList.size();) {
			if(bookList.get(i).getBookId().equals(bookId)) {
				bookList.remove(i);
				System.out.println("该书的状态删除成功！");
				biz.update(this);
				break;
			}
			i++;
			if(i == bookList.size())
				System.out.println("没有这个内部ID的图书！");
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getInStoreCount() {
		return inStoreCount;
	}
	public void setInStoreCount(int inStoreCount) {
		this.inStoreCount = inStoreCount;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List getBookList() {
		return bookList;
	}
}

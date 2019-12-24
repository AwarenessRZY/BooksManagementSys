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
 * ��ͼ���ISBNΪ������ͼ����Ϣ��
 * ĳһ��ISBN���Ӧ�ܶ౾��ʵ���ڵ�ͼ�����
 * attributess:ISBN��������ߡ������硢�����������ʱ�䡢����
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
	//���幹�췽������дtoString,equals�Ȳ������õ�ʱ���������
	
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
		System.out.println("*********���и���ͼ���״̬��Ϣ***********");
		for(Book book:bookList ) {
			System.out.println("ͼ������" + name + "\tIsbn:" + isbn + "\t����״̬:" + book.getState());
		}
	}
	
	public Book searchByBookId(String bookId) {
		for(Book book : bookList) {
			if(book.getBookId().equals(bookId)) {
				System.out.println("ͼ������" + name + "\tIsbn:" + isbn + "\t����״̬:" + book.getState());
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
				System.out.println("�����״̬���³ɹ���");
				biz.update(this);
				break;
			}
			i++;
			if(i == bookList.size())
				System.out.println("û������ڲ�ID��ͼ�飡");
		}
			}
	
	public void deleteBooks(String bookId) {
		int i = 0;
		for(; i < bookList.size();) {
			if(bookList.get(i).getBookId().equals(bookId)) {
				bookList.remove(i);
				System.out.println("�����״̬ɾ���ɹ���");
				biz.update(this);
				break;
			}
			i++;
			if(i == bookList.size())
				System.out.println("û������ڲ�ID��ͼ�飡");
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

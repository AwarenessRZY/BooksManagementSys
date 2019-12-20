package biz;

import java.io.Serializable;
import java.util.Map;

public interface Biz<T> extends Serializable{
	public boolean add(T t);
	public boolean delete(T t);
	public T update(T t);
	public T searchByIsbn(String Isbn);
	public T searchById(String id);
	public Map<String,T> findAll();
}

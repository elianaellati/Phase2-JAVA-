package application;
import java.util.ArrayList;

public interface MediaRentInt {
	void addCustomer(String name, String address, String ID ,String Mobile, String plan);
	void addMovie(String title, int copiesAvailable,String code,String rating);
	void addGame(String title, int copiesAvailable,String code,double weight);
	void addAlbum(String title,int copiesAvailable,String code,String artist,String songs);
	String getAllCustomersInfo();
	String  getAllMediaInfo();
	boolean addToCart2(String id,String code);
	boolean removeFromCart (String customerName,String mediaTitle);
	String processRequests1();
	ArrayList<String> searchMedia(String title,String rating, String artist,String songs);
	boolean returnMedia(String id,String mediaTitle);
}

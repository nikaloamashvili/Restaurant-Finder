import java.io.Serializable;

public class Restaurant implements Serializable{

	private String name;
	private String raw_ranking;
	private String address;
	private String photo;
	private String userEmail;
	

	public Restaurant(String name, String raw_ranking, String address, String photo, String userEmail) {
		
	}

	public String getName() {
		return name;
	}
	public String getraw_ranking() {
		return raw_ranking;
	}
	public String getaddress() {
		return address;
	}
	public String getphoto() {
		return photo;
	}
	public String getuserEmail() {
		return userEmail;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public void setraw_ranking(String raw_ranking) {
		this.raw_ranking=raw_ranking;
	}
	public void setaddress(String address) {
		this.address=address;
	}
	public void setphoto(String photo) {
		this.photo=photo;
	}
	public void setuserEmail(String userEmail) {
		this.userEmail=userEmail;
	}
	
	
}

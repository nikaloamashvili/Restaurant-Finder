import java.io.Serializable;

public class employee implements Serializable{

	private String id;
	private String name;
	private String phone;
	private String age;
	private String address;
	private String mail;
	public employee(String id, String name, String phone, String age, String address, String mail) {
		
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.address = address;
		this.mail = mail;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	public String getMail() {
		return mail;
	}
	
}

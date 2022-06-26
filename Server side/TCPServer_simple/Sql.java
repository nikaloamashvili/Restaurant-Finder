import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;


public class Sql {

	private  Connection connect; 
	
	public  void delete_statement(String name,String userEmail){
		String sqldelete = "delete from android2.restaurant where name = "+name+" AND userEmail ="+ userEmail;


		try {
			PreparedStatement pst = connect.prepareStatement(sqldelete);

			
			




			
			pst.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public  void update_statement(){
		
String sqlupdate = "UPDATE student SET name=?  WHERE id =? ";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);
			
			pst.setString(1, "effi");
			pst.setString(2, "3344");
		//	pst.setString(3, "66127762");
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  void insert_statement( String name, String phone, String age, String address,String mail,String admin  ){
		
		String sqlInsert = "insert into android2.users (name, phone,age,address, mail,admin) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			//pst.setString(1, id);
			pst.setString(1, name);
			pst.setString(2, phone);
			pst.setString(3, age);
			pst.setString(4, address);
			pst.setString(5, mail);
			pst.setString(6, admin);
			pst.execute();	

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public  void insert_statement2( String name, String raw_ranking, String address, String photo,String userEmail  ){
		
		String sqlInsert = "insert into android2.restaurant (name, raw_ranking,address,photo, userEmail) values (?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			//pst.setString(1, id);
			pst.setString(1, name);
			pst.setString(2, raw_ranking);
			pst.setString(3, address);
			pst.setString(4, photo);
			pst.setString(5, userEmail);


					
			pst.execute();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  String select_query(String choice)
	{
		String str="";
		PreparedStatement statement = null;
		try {

			statement = connect.prepareStatement("SELECT name,phone,age,address, mail,admin FROM android2.users where mail ="+choice);

			ResultSet result = statement.executeQuery();
			
			while(result.next())
			{
				
				for(int i=1 ; i<= 6 ; i++ ) {
					str += result.getString(i)+" ";
				
				}
				
				str +="!";
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public  String select_query2(String choice)
	{
		String str="";
		PreparedStatement statement = null;
		try {

			statement = connect.prepareStatement("SELECT name, raw_ranking,address,photo, userEmail FROM android2.restaurant where userEmail ="+choice+" OR "+ "userEmail ='ads'");

			ResultSet result = statement.executeQuery();
			
			while(result.next())
			{
				
				for(int i=1 ; i<= 5 ; i++ ) 
					str += result.getString(i)+"*";
				
				
				
			}
			str +="!";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	
	public  void connection()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Works");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void ConectingToSQL ()
	{
		
		connection();
		String host = "jdbc:mysql://localhost:3306/db";
		String username = "root";
		String password = "Niogio11";
		
		
		try {
			 connect = (Connection) DriverManager.getConnection(host, username, password);
		System.out.println("work");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;

public class socketHandler extends Thread {
	Socket incoming;
	Mongo mongo;
	Sql sql;
	
	socketHandler(Socket _in , Sql sql)
	{
		incoming=_in;
		
		this.sql = sql; 
		
	}
	
	public void run()
	{
		String clientSentence; 
	    String capitalizedSentence = null; 
	    int sum = 0; 
	   double result = 0 ; 
	  
		try
		{
	    
           BufferedReader inFromClient = 
              new BufferedReader(new
              InputStreamReader(incoming.getInputStream())); 
            
           DataOutputStream  outToClient = 
        		   new DataOutputStream (incoming.getOutputStream() );
         
           
           String mailget;         
           String mailget1; 
           String rmailget;         
           String rmailget1; 
           String drmailget;         
           String drmailget1; 
           String drmailget2;         
           String drmailget12; 
           
           String name; 
           String phone; 
           String age; 
           String address; 
           String mail;
           String admin; 
           String output;
           Restaurant restaurant=new Restaurant("0","0","0","0","0");
			
			//str = inFromClient.readLine();

			
				
			//all data - db
            String line = inFromClient.readLine();

            if(line.equals("0")) {
            	name=inFromClient.readLine();
            	phone=inFromClient.readLine();
            	age=inFromClient.readLine();
            	address=inFromClient.readLine();
            	mail=inFromClient.readLine();
            	admin=inFromClient.readLine();

    			sql.insert_statement( name, phone, age, address, mail,admin);
    			//outToClient.writeBytes(line.toUpperCase()+"\n");
            }else if(line.equals("1")) {
               
                mailget1=inFromClient.readLine();
                mailget="'"+mailget1+"'";
                output=sql.select_query(mailget);
                outToClient.writeBytes(output);
            }else if(line.equals("2")) {
            	   restaurant.setName(inFromClient.readLine());
            	   restaurant.setraw_ranking(inFromClient.readLine());
            	   restaurant.setaddress(inFromClient.readLine());
            	   restaurant.setphoto(inFromClient.readLine());
            	   restaurant.setuserEmail(inFromClient.readLine());

       				sql.insert_statement2( restaurant.getName(), restaurant.getraw_ranking(), restaurant.getaddress(), restaurant.getphoto(), restaurant.getuserEmail());
       				//outToClient.writeBytes(line.toUpperCase()+"\n");
            }else if(line.equals("3") ){
                rmailget1=inFromClient.readLine();
                rmailget="'"+rmailget1+"'";
                output=sql.select_query2(rmailget);
                outToClient.writeBytes(output);
            	
            }else if(line.equals("4")) {

            	   drmailget1=inFromClient.readLine();
                   drmailget="'"+drmailget1+"'";
            	   drmailget12=inFromClient.readLine();
                   drmailget2="'"+drmailget12+"'";
                   sql.delete_statement(drmailget,drmailget2);
                   //outToClient.writeBytes(output);
            }
            	
       
            
           


			
			
		
		}
		catch(IOException e)
		{
			e.getMessage();
		} 
		try {
			incoming.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

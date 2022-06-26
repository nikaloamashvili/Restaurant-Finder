import java.io.*; 
import java.net.*; 

class TCPServer { 
    
  public static void main(String argv[]) throws Exception 
    { 
	  
	//  Mongo mongo = new Mongo();
	  Sql sql = new Sql();
	  
	  ServerSocket s = null;
	 
		try {
		    s = new ServerSocket(10000);
	//	    mongo.connect();
		    sql.ConectingToSQL();
		    
		} catch(IOException e) {
		    System.out.println(e);
		    System.exit(1);
		}

		while (true) {
		    Socket incoming = null;
		    
		    try {
			incoming = s.accept();
			
		    } catch(IOException e) {
			System.out.println(e);
			continue;
		    }
		    	
		    new socketHandler(incoming , sql).start();
		    	
		    System.out.println(incoming);
		    
		}
    } 
} 

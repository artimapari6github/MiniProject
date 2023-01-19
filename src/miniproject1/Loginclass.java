package miniproject1;



	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

	public class Loginclass {

	    public  void main(String[] args) {
	    	try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedata","root","Artimapari6@");
				
				//select query
			PreparedStatement ps1=con1.prepareStatement("select * from login");
				
				ResultSet rs1=ps1.executeQuery();
				
			if(rs1.next()) {
//					
//				System.out.println("ID="+rs.getInt(1));
//					System.out.println("Username="+rs.getString(2));
//					System.out.println("Password="+rs.getString(3));
				Scanner scanner = new Scanner(System.in); 
		            System.out.print(" Enter user name => ");
		            String userName = scanner.nextLine();

		            System.out.print(" Enter password => ");
		            String password = scanner.nextLine();

		            if ("Admin".equals(userName) && "Admin".equals(password)) {
		                System.out.println(" User successfully logged-in.. ");
		                super.getClass();
		            } else {
		                System.out.println(" In valid userName of password ");
		            
	        }
				
			}
				con1.close();
				ps1.close();
				rs1.close();
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}


	        
	    }
	}


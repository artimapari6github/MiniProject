package miniproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.mysql.cj.xdevapi.Statement;

class question{
	private int qid;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	public question(int qid, String question, String option1, String option2, String option3, String option4,
			String answer) {
		super();
		this.qid = qid;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}

public class DBConnection {
	
	public static Connection getConnection()
	{
		
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedata","root","Artimapari6@");
		}catch(SQLException e)
		{
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,e);
		}
		return con;
	}
		
	public static void main(String[] args) { 
		int countTotal = 0;
        int countRight = 0;
        int countWrong = 0;
        
		
		HashMap<Integer,question>hm=new HashMap<Integer,question>();
		Statement st=null;
		ResultSet rs=null;
		Connection con=getConnection();
		question q;
		try {
		st= con.createStatement();
		Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedata","root","Artimapari6@");
		
		//select query
		
	PreparedStatement ps1=con1.prepareStatement("select * from login");
	
	
		ResultSet rs1=ps1.executeQuery();
		
	if(rs1.next()) {
//			
//		System.out.println("ID="+rs.getInt(1));
//			System.out.println("Username="+rs.getString(2));
//			System.out.println("Password="+rs.getString(3));
		Scanner sc = new Scanner(System.in); 
            System.out.print(" Enter user name => ");
            String usename = sc.nextLine();

            System.out.print(" Enter password => ");
            String Password = sc.nextLine();

            if (rs1.getString(2).equals(usename) && rs1.getString(3).equals(Password)) {
                System.out.println(" User successfully logged-in.. ");
                System.out.println("Quize Start");
                
                try {
        			Class.forName("com.mysql.jdbc.Driver");
        			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedata", "root", "Artimapari6@");

        			PreparedStatement stmt = conn.prepareStatement("insert into student(Sname,Lname)values(?,?)");
Scanner scanner=new Scanner(System.in);
        			System.out.println("Enter your first name:");
String sname=scanner.next();
System.out.println("Enter your last name:");
String lname=scanner.next();
        			stmt.setString(2, sname); //1 first parameter in query.
        			stmt.setString(3, lname);

        			int i=stmt.executeUpdate();
        			System.out.println("Record is inserted."+i);

        			
        		} catch (Exception e) {
        			e.getMessage();
        		}

           
System.out.println("************************************************************************");
		
		rs=st.executeQuery("select * from question");
            
          
		while(rs.next())
		{
			Integer qid=rs.getInt(1);
			String question=rs.getString(2);
			String option1=rs.getString(3);
			String option2=rs.getString(4);
			String option3=rs.getString(5);
			String option4=rs.getString(6);
			String answer=rs.getString(7);
			
			q=new question(qid,question,option1,option2,option3,option4,answer);
			hm.put(qid, q);
			
			
		}
	}
		else {
            System.out.println(" Invalid userName or password ");
        
}
	}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(Integer i:hm.keySet())
		{
			question qu=hm.get(i);
			//System.out.println(qu.getQid());
			System.out.println(qu.getQuestion());
			System.out.println(qu.getOption1());
			System.out.println(qu.getOption2());
			System.out.println(qu.getOption3());
			System.out.println(qu.getOption4());
			
			String answer="";
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice:");
		    String ans=sc.next();
		
		    switch(ans)
            {
                case "A":
                    answer = qu.getOption1();
                    break;
                case "B":
                    answer = qu.getOption2();
                    break;
                case "c":
                    answer = qu.getOption3();
                    break;
                case "D":
                    answer = qu.getOption4();
                    break;
                default:break;
            }
		if(ans.equals(qu.getAnswer())){
		System.out.println("====================================================================================");
			System.out.println("Your answer is correct:");
			countRight++;
		System.out.println("====================================================================================");	
		}
		else {
			System.out.println("====================================================================================");
			System.out.println("Wrong anwer.");
			countWrong++;
			System.out.println("====================================================================================");
		}
		}
		System.out.println();
		System.out.println("------------------result---------------------------");
		System.out.println("Total  number of question"+hm.size());
		System.out.println("Correct answer :"+countRight);
		  try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedata", "root", "Artimapari6@");

  			PreparedStatement stmt = connection.prepareStatement("insert into studentdata(Score)values(?,?)");

  			stmt.setInt(3, countRight); //1 first parameter in query.
  			

  			int i=stmt.executeUpdate();
  			//System.out.println("Record is inserted."+i);

  			
  		} catch (Exception e) {
  			e.getMessage();
  		}

		
		
		
		System.out.println("Wrong answer "+countWrong);
		if(countRight>=8) {
			System.out.println("Class A");
		}
		else if(countRight>6) {
			System.out.println("Class B");
		}
		else if(countRight==5) {
			System.out.println("Class c");
		}
		else
		{System.out.println("Class c");
			
		}
		}
                 
		
		
		
	}




import java.sql.*;

public class JDBCjava {

	public static void main(String[] args) throws SQLException {
		//batchDemo();
		readRecord();
		//insertUsingPst();
	}
	public static void readRecord() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		String query="select name from employee";
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		Statement st=con.createStatement();//
		ResultSet rs=st.executeQuery(query);//--------------
		
		while(rs.next()) {
		System.out.println("employee id is "+rs.getInt(1));
		System.out.println("employee id is "+rs.getString(2));
		System.out.println("employee id is "+rs.getInt(3));
		}
		
		con.close();
	}
	
	public static void insertRecord() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		String query="insert into employee(name,salary) values ('priya',200000)";
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);//------------
		
		System.out.println("no of rows affected:"+rows);
		
		con.close();
	}
	//insertion with variables
	
	public static void insertVar() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		String name="hi";
		int salary=500000;
		String query="insert into employee(name,salary) values ('"+name+"',"+salary+")";
		
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);//------------
		
		System.out.println("no of rows affected:"+rows);
		
		con.close();
	}
	
	public static void insertUsingPst() throws SQLException {//prepared statement
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";		String password="";
		
		String name="hi";
		int salary=500000;
		String query="insert into employee(name,salary) values (?,?);";
	
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj

		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, name);//1st question (?)mark la name set panrom -1st argument defines which question mark 2nd arguments define replacing the question mark with value
		pst.setInt(2, salary);//2nd question (?)mark la salary set panrom -1st argument defines which question mark 2nd arguments define replacing the question mark with value
		int rows=pst.executeUpdate();
		
		
		
		System.out.println("no of rows affected:"+rows);
		
		con.close();
	}
	
	public static void delete() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		int id=103;
		String query="delete from employee where id="+id;
		
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);//------------
		
		System.out.println("no of rows affected:"+rows);
		
		con.close();
	}
	
	public static void update() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		int id=104;
		String query="update employee set salary=696969 where id="+id;
		
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);//------------
		
		System.out.println("no of rows affected:"+rows);
		
		con.close();
	}
	
	public static void sp() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		
		
		
		Connection con=DriverManager.getConnection(url,userName,password);//getConnection static class returns connection obj
		CallableStatement cst=con.prepareCall("{call getEmp()}");
		ResultSet rs=cst.executeQuery();//------------
		
		
		while(rs.next()) {
			System.out.println("id is "+ rs.getInt(1));
			System.out.println("Name is "+ rs.getString(2));
			System.out.println("Salary is "+ rs.getInt(3));
		}
		
		con.close();
	}
	
	
	public static void sp2() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		Connection con =DriverManager.getConnection(url,userName,password);
		int id=1;
		
		CallableStatement cst=con.prepareCall("{call getEmpById(?)}");
		cst.setInt(1,id);
		
		ResultSet rs=cst.executeQuery();
		rs.next();
		System.out.println("id is"+rs.getInt(1));
		System.out.println("id is"+rs.getString(2));
		System.out.println("id is"+rs.getInt(3));
	}
	
	//callableStatement using OUT parameter  
	public static void sp3() throws SQLException {
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		Connection con =DriverManager.getConnection(url,userName,password);
		int id=1;
		
		CallableStatement cst=con.prepareCall("{call getEmpNameById(?,?)}");
		cst.setInt(1,id);
		cst.registerOutParameter(2, Types.VARCHAR);
		
		cst.executeUpdate();
		System.out.println(cst.getString(2));
		con.close();
	}
	
	//commit and autocommit
	public static void comitDemo() throws SQLException{
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		
		String query1="update employee set salary =20 where id=3;";
		String query2="update employee set salary =10 where id=2;";

		Connection con =DriverManager.getConnection(url,userName,password);
		con.setAutoCommit(false);//-------------------------------default ah true nu erukkum
		//commit and rollback  nu ethulayo padichom
		
		
		
		Statement st=con.createStatement();
		int a=st.executeUpdate(query1);
		System.out.println("rows affected "+a);
		
		int b=st.executeUpdate(query2);
		System.out.println("rows affected "+b);
		if(a>0 && b>0) {
			con.commit();//----------------------ithu kudutha tha table la update agu illana update agathu
		}
		
	}
	
	//batch processing
	public static void batchDemo() throws SQLException{
		String url="jdbc:mysql://localhost:3306/proj";
		String userName="root";
		String password="";
		
		
		String query1="updat employee set salary =6 where id=3;";
		String query2="update employee set salary =6 where id=2;";
		String query3="update employee set salary =6 where id=1;";
		String query4="update employee set salary =6 where id=104;";
		
		Connection con =DriverManager.getConnection(url,userName,password);
		con.setAutoCommit(false);//-------------------------------default ah true nu erukkum
		Statement st=con.createStatement();
		//batchuuuuuu
		st.addBatch(query4);
		st.addBatch(query3);
		st.addBatch(query2);
		st.addBatch(query1);
		
		int result[]=st.executeBatch();
		
		
		for (int i: result) {
			if (i>0) {
				continue;
			}
			else {
				con.rollback();//rollback - if i=0 then the rollback function executed it cancels the pending changes
			}
		}
		con.commit();//----------------------ithu kudutha tha table la update agu illana update agathu
		con.close();
		
	}
	
	
	
}

package busReserv;
import java.util.Date;
import java.sql.*;

public class BookingDAO {
	public int getBookedCount(int busno,Date date) throws SQLException{
		String query="select count(passenger_name) from booking where bus_no=? && travel_date=?";
		
		Connection con = DBConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,busno );
		
		java.sql.Date sqldate=new java.sql.Date (date.getTime());//////
		pst.setDate(2,sqldate);
		
		
		ResultSet rs=pst.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}
	
	public void addBooking(Booking booking) throws SQLException{
		
		String query="insert into booking values(?,?,?);";
		Connection con =DBConnection.getConnection();
		PreparedStatement pst= con.prepareStatement(query);
		
		pst.setString(1, booking.passengerName);
		pst.setInt(2, booking.busno);
		
		java.sql.Date sqldate=new java.sql.Date(booking.date.getTime());
		pst.setDate(3, sqldate);
		
		pst.executeUpdate();
	}
}

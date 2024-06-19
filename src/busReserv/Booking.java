package busReserv;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Booking {
	String passengerName;
	int busno;
	Date date;
	Scanner sc=new Scanner(System.in);
	
	Booking(){
		System.out.print("Enter your name:");
		passengerName=sc.next();
		System.out.print("Enter bus no:");
		busno=sc.nextInt();
		System.out.print("Enter date dd-mm-yyyy:");
		String dateInput=sc.next();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		
		
		try {
			date=dateFormat.parse(dateInput);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public boolean isAvailable() throws Exception {
	BusDAO busdao=new BusDAO();
	
	
	BookingDAO bookingdao=new BookingDAO();
	
	
	int capacity=busdao.getCapacity(busno);
	

	int booked=bookingdao.getBookedCount(busno,date);
	
	return booked<capacity;
	
	}
}

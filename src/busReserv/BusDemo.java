package busReserv;
import java.util.Scanner;

public class BusDemo {

	public static void main(String[] args) throws Exception {
		
		BusDAO busdao=new BusDAO();
		busdao.displayBusInfo();
		
		
		Scanner sc=new Scanner(System.in);
		int useropt=1;
		
		
		while(true){
			System.out.println("Enter 1 to Book and 2 to exit");
			useropt=sc.nextInt();
			if(useropt==1) {
				Booking booking=new Booking();
				
				if(booking.isAvailable()) {
					BookingDAO bookingdao =new BookingDAO();
					bookingdao.addBooking(booking);
					System.out.println("your booking is confirmed");
				}
				else {
					System.out.println("Sorry , your booking is unavailable try another bus or date");
				}
				
			}
			else if(useropt==2) {
				System.out.println("Exiting....");
				break;
			}
		}
		sc.close();

	}

}

package RAILWAYRESERVATION;
import java.util.*;

public class Railway{

        public static void book_ticket(Passenger p){
                Ticket_booker booker=new Ticket_booker();
                if(Ticket_booker.available_WL<=0){
                        System.out.println("No tickets available....................");
                        return;
                }
                else if(Ticket_booker.available_Lower>0 && p.req_berth.equals("L") || Ticket_booker.available_middle>0 && p.req_berth.equals("M") || Ticket_booker.available_upper>0 && p.req_berth.equals("U")){
                        System.out.println("Preffered berth is given...........");
                        if(p.req_berth.equals("L")){
                                booker.bookTicket(p,booker.lowerBerth_no.get(0),"L");
                                p.allotted_berth="L";
                                p.seat_no=booker.lowerBerth_no.get(0);

                                booker.lowerBerth_no.remove(0);
                                booker.available_Lower--;
                        }
                        else if(p.req_berth.equals("M")){
                                booker.bookTicket(p,booker.middleBerth_no.get(0),"M");
                                p.allotted_berth="M";
                                p.seat_no=booker.middleBerth_no.get(0);

                                booker.middleBerth_no.remove(0);
                                booker.available_middle--;
                        }
                        else if(p.req_berth.equals("U")){
                                booker.bookTicket(p,booker.UpperBerth_no.get(0),"U");
                                p.allotted_berth="U";
                                p.seat_no=booker.UpperBerth_no.get(0);

                                booker.UpperBerth_no.remove(0);
                                booker.available_upper--;
                        }
                }
                else if(Ticket_booker.available_Lower>0){
                        System.out.println("Lower berth is given............");
                        booker.bookTicket(p,booker.lowerBerth_no.get(0),"L");
                                p.allotted_berth="L";
                                p.seat_no=booker.lowerBerth_no.get(0);

                                booker.lowerBerth_no.remove(0);
                                booker.available_Lower--;
                }
                else if(Ticket_booker.available_middle>0){
                        System.out.println("middle berth  is given...........");
                        booker.bookTicket(p,booker.middleBerth_no.get(0),"M");
                                p.allotted_berth="M";
                                p.seat_no=booker.middleBerth_no.get(0);

                                booker.middleBerth_no.remove(0);
                                booker.available_middle--;
                        
                }
                else if(Ticket_booker.available_upper>0){
                        System.out.println("upper berth is given........");
                        booker.bookTicket(p,booker.UpperBerth_no.get(0),"U");

                                booker.UpperBerth_no.remove(0);
                                booker.available_upper--;
                        
                }
                else if(Ticket_booker.available_rac>0){
                        System.out.println("rac is available.......");
                        booker.bookRac(p,booker.rac_no.get(0),"Rac");

                        booker.rac_no.remove(0);
                        booker.available_rac--;
                        
                }
                else if(Ticket_booker.available_WL>0){
                        System.out.println("WL");
                        booker.bookWL(p,booker.WL_no.get(0),"WL");

                        booker.WL_no.remove(0);
                        booker.available_WL--;
                        
                
                }
        }

        public static void showAvailableTickets(){
                System.out.println("-----------------------------------");
                System.out.println("Available lower berth===="+ Ticket_booker.available_Lower);
                System.out.println("Available middle berth===="+ Ticket_booker.available_middle);
                System.out.println("Available upper berth===="+ Ticket_booker.available_upper);
                System.out.println("Available rac===="+Ticket_booker.available_rac);
                System.out.println("Available wl===="+Ticket_booker.available_WL);
                System.out.println("------------------------------------");
        }

        public static void cancelTicket(int p_id){
                Ticket_booker booker=new Ticket_booker();
                if(!(booker.passengers.containsKey(p_id))){
                        System.out.println("Unknown passenger id.. Please enter valid id");
                }
                else
                        booker.cancelBooking(p_id);
        }
 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean bool=true;
        new Ticket_booker();
        new Passenger();

        while(bool){
            System.out.println("RRS \n 1. Book tickets \n 2. Cancel tickets \n 3. Available ticekts \n 4. Passenger details \n 5. Exit \nEnter the option ");
            int user_option=sc.nextInt();
            
        switch(user_option){
            case 1:
                    System.out.println("Enter passenger details");
                    System.out.println("Enter name:");
                    String name=sc.next();
                    System.out.println("Enter age:");
                    int age=sc.nextInt();
                    System.out.println("Prefered berth  L/M/U");
                    String req_berth=sc.next(); 
                    req_berth=req_berth.toUpperCase();

                    Passenger p=new Passenger(name,age,req_berth);
                    
                    book_ticket(p);
                
                    break;
            case 2:
                    
                    System.out.println("Enter passenger id:");
                    int p_id=sc.nextInt();
                    cancelTicket(p_id);
                    break;
            case 3:
                    showAvailableTickets();
                    break;
            case 4:
                    Ticket_booker booker=new Ticket_booker();
                    booker.showPassengers(); 
                    break;
            case 5:
                    bool=false;
                    break;
            default:
                    System.out.println("Please enter the valid option...........");

        }
     }

    }

}
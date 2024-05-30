package RAILWAYRESERVATION;

import java.util.*;

public class Ticket_booker {
    static int available_Lower=1;
    static int available_middle=1;
    static int available_upper=1;
    static int available_rac=1;
    static int available_WL=1;

    static List<Integer> book_tickets_list=new ArrayList<>();
    static Queue<Integer> rac_lists=new LinkedList<>();
    static Queue<Integer> WL_lists=new LinkedList<>();

    static List<Integer>  lowerBerth_no=new ArrayList<>(Arrays.asList(1));
    static List<Integer>  middleBerth_no=new ArrayList<>(Arrays.asList(1));
    static List<Integer>  UpperBerth_no=new ArrayList<>(Arrays.asList(1));
    static List<Integer>  rac_no=new ArrayList<>(Arrays.asList(1));
    static List<Integer>  WL_no=new ArrayList<>(Arrays.asList(1));

    static HashMap<Integer,Passenger> passengers=new HashMap<>();

    public void bookTicket(Passenger p,int seat_no,String allotted_berth){
        p.allotted_berth=allotted_berth;
        p.seat_no=seat_no;

        passengers.put(p.p_id,p);
        book_tickets_list.add(p.p_id);
        System.out.println("-------------------------------Booked successfully");
        showPassengers();
    }
    public void bookRac(Passenger p,int seat_no,String allotted_berth){
        p.allotted_berth=allotted_berth;
        p.seat_no=seat_no;

        passengers.put(p.p_id, p);
        rac_lists.add(p.p_id);
        System.out.println("-------------------------added to rac");
    }
    public void bookWL(Passenger p,int seat_no,String allotted_berth){
        p.allotted_berth=allotted_berth;
        p.seat_no=seat_no;

        passengers.put(p.p_id, p);
        WL_lists.add(p.p_id);
        System.out.println("-------------------------added to WL");
    }

    public void cancelBooking(int p_id){
        Passenger p=passengers.get(p_id);
        passengers.remove(Integer.valueOf(p_id));
        book_tickets_list.remove(Integer.valueOf(p_id));


        int given_seat_no=p.seat_no;
        if(p.allotted_berth.equals("L")){
            available_Lower++;
            lowerBerth_no.add(given_seat_no);
        }
        else if(p.allotted_berth.equals("M")){
            available_middle++;
            middleBerth_no.add(given_seat_no);

        }
        else if(p.allotted_berth.equals("U")){
            available_upper++;
            UpperBerth_no.add(given_seat_no);

        }
        System.out.println("---------Booking cancelled successfully ");

        if(!rac_lists.isEmpty()){
            Passenger passengerFromRac=passengers.get(rac_lists.poll());
            int given_rac_no=passengerFromRac.seat_no;
            rac_no.add(given_rac_no);


            rac_lists.remove(Integer.valueOf(passengerFromRac.p_id));
            available_rac++;
            if(!WL_lists.isEmpty()){
                Passenger passengeFromWL=passengers.get(WL_lists.poll());
                int given_wl_no=passengeFromWL.seat_no;
                WL_no.add(given_wl_no);
                WL_lists.remove(Integer.valueOf(passengeFromWL.p_id));

                passengeFromWL.seat_no=rac_no.get(0);
                passengeFromWL.allotted_berth="Rac";
                rac_no.remove(0);
                rac_lists.add(passengeFromWL.p_id);

                available_WL++;
                available_rac--;
            }

            Railway.book_ticket(passengerFromRac);
        }
    }
    public void showPassengers(){
        if(passengers.isEmpty()){
            System.out.println("no passenger details is available");
            return;
        }
        for(Passenger p1:passengers.values()){
                System.out.println("--------------------------------");
                System.out.println("passenger id ="+p1.p_id);
                System.out.println("passenger name="+p1.name);
                System.out.println("passenger age="+p1.age);
                System.out.println("Allottted berth="+p1.allotted_berth);
                System.out.println("Seat number="+p1.seat_no);
                System.out.println("--------------------------------");
        }
    }
}

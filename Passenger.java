package RAILWAYRESERVATION;
public class Passenger {
            static int id=1;
            String name;
            int age;
            String req_berth;
            int p_id;
            String allotted_berth;
            int seat_no;
    Passenger(){
        
    }
        Passenger(String name,int age,String req_berth){
            this.name=name;
            this.age=age;
            this.req_berth=req_berth;
            p_id=id++;
            allotted_berth="";
            seat_no=0;
        }

}

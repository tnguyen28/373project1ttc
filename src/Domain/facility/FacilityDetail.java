package Domain.facility;

public class FacilityDetail extends Facility{
    private String name;
    private int roomNumber;
    private int phoneNumber;

    public FacilityDetail(){

    }

    public String getName(){
        return name;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }



}

package use;
import java.time.LocalDate;
import facility.facility;

public class FacilityUse extends facility{
    private int roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getRoomNumber(){
        return roomNumber;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

}

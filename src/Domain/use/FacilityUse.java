package Domain.use;
import java.time.LocalDate;
import Domain.facility.Facility;
import Domain.facility.FacilityDetail;


public class FacilityUse{

    private Facility facility;
    private int roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private int facilityID = facility.getFacilityID();
    private FacilityDetail facilityDetail = facility.getFacilityDetails();

    public int getRoomNumber(){
        return roomNumber;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public int getFacilityID() { return facilityID; }
    public Facility getFacility(){return facility;}
    public FacilityDetail getFacilityDetails() { return facilityDetail; }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }
    public void setFacilityID(int facilityID) { this.facilityID = facilityID; }
    public void setFacilityDetails(FacilityDetail fd) { this.facilityDetail = fd; }

}

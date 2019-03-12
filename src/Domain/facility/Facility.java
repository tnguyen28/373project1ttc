package Domain.facility;
import Domain.use.FacilityUse;
import Domain.maintenance.Maintenance;

public class Facility {
    private int facilityID;
    private FacilityDetail facilityDetails;
    private FacilityUse facilityUse;
    private Maintenance maintenance;

    public Facility(){

    }




    public FacilityDetail getFacilityDetails(){
        return facilityDetails;
    }
    public int getFacilityID(){
        return facilityID;
    }

    public void setFacilityDetails(FacilityDetail facilityDetails){
        this.facilityDetails = facilityDetails;
    }

    public void setFacilityID(int facilityID){

        this.facilityID = facilityID;
    }
}

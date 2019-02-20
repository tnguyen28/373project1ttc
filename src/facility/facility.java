package facility;

public class facility {
    private int facilityID;
    private facilityDetail facilityDetails;

    public facility(){

    }

    public facilityDetail getFacilityDetails(){
        return facilityDetails;
    }
    public int getFacilityID(){
        return facilityID;
    }

    public void setFacilityDetails(facilityDetail facilityDetails){
        this.facilityDetails = facilityDetails;
    }

    public void setFacilityID(int facilityID){
        this.facilityID = facilityID;
    }
}

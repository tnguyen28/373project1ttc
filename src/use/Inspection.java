package use;

public class Inspection {
    private int facilityID;
    private String inspectionLocation;
    private String inspectionDetail;

    public int getFacilityID(){
        return facilityID;
    }
    public String getInspectionLocation(){

        return inspectionLocation;
    }
    public String getInspectionDetail(){
        return inspectionDetail;
    }

    public void setFacilityID(int facilityID){
        this.facilityID = facilityID;
    }
    public void setInspectionLocation(String inspectionLocation){
        this.inspectionLocation = inspectionLocation;
    }
    public void setInspectionDetail(String inspectionDetail){
        this.inspectionDetail = inspectionDetail;
    }

}

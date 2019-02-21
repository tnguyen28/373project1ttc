package use;

public class Inspection {
    private int facilityID;
    private String inspectionType;
    private String inspectionDetail;

    public int getFacilityID(){
        return facilityID;
    }
//    public String getInspectionType(){
//
//        return inspectionType;
//    }
    public String getInspectionDetail(){
        return inspectionDetail;
    }

    public void setFacilityID(int facilityID){
        this.facilityID = facilityID;
    }
//    public void setInspectionType(String inspectionType){
//        this.inspectionType = inspectionType;
//    }
    public void setInspectionDetail(String inspectionDetail){
        this.inspectionDetail = inspectionDetail;
    }

}

package Domain.Inspection;
import Domain.facility.Facility;

public class Inspection extends Facility {

    private String inspectionLocation;
    private String inspectionDetail;


    public String getInspectionLocation(){

        return inspectionLocation;
    }
    public String getInspectionDetail(){
        return inspectionDetail;
    }


    public void setInspectionLocation(String inspectionLocation){
        this.inspectionLocation = inspectionLocation;
    }
    public void setInspectionDetail(String inspectionDetail){
        this.inspectionDetail = inspectionDetail;
    }

}

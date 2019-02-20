package maintenance;
import facility.facility;

public class maintenance extends facility {
    private String details;
    private int cost;

    public maintenance(){

    }

    public String getDetails(){
        return details;
    }
    public int getCost(){
        return cost;
    }

    public void setDetails(String details){
        this.details = details;
    }
    public void setCost(int cost){
        this.cost = cost;
    }

}

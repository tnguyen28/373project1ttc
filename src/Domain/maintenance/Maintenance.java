package Domain.maintenance;

public class Maintenance{
    private String details;
    private int cost;


    public Maintenance(){

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

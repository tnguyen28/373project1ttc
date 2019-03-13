package Domain.maintenance;

import Domain.facility.Facility;

public class Maintenance{

    private Facility facility;
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
    public Facility getFacility(){ return facility;}

    public void setDetails(String details){
        this.details = details;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public void setFacility(Facility fac) { this.facility = fac; }

}

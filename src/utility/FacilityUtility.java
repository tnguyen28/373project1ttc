package utility;
import facility.*;
import use.*;
import java.util.List;
import java.time.LocalDate;
import database.*;

public class FacilityUtility {

    private FacilityDAO facDAO = new FacilityDAO();
    private UseDAO useDAO = new UseDAO();

    public void addNewFacility(facility facility){
        //add to database
        try {
            facDAO.addNewFacility(facility);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception adding new facility.");
            System.err.println(se.getMessage());
        }
    }
    public void removeFacility(int id){
        //delete from database
        try {
            facDAO.removeFacility(id);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception removing facility.");
            System.err.println(se.getMessage());
        }
    }
    public void addFacilityDetail(int ID, int phoneNumber){
        //add a detail
        try {
            facDAO.addFacilityDetail(ID, phoneNumber);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception updating phone in facility_detail.");
            System.err.println(se.getMessage());
        }
    }

    public facility getFacilityInformation(int facilityID){
        //fetch information

        try {
            facility fac = facDAO.getFacilityInformation(facilityID);
            return fac;
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving facility.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<facility> listFacilities(){
        //list all facilities
        try {
            return facDAO.listFacilities();
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving list of facilities.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public int requestAvailableCapacity(facility facility){
        //request available capacity
        try {

            List<FacilityUse> usage = useDAO.listActualUsage(facility);
            //default room number
            int usedRooms = 0;
            if (usage.size() > 0) {
                for (FacilityUse fu : usage) {
                    //if currently in use
                    if ((LocalDate.now().equals(fu.getStartDate()) || LocalDate.now().isAfter(fu.getStartDate())) &
                            LocalDate.now().equals(fu.getEndDate()) || LocalDate.now().isBefore(fu.getEndDate())) {
                        if (fu.getRoomNumber() == 0) {
                            return 0;
                        } else {
                            usedRooms = usedRooms + 1;
                        }
                    }
                }
            }

            return facility.getFacilityDetails().getRoomNumber() - usedRooms;

        } catch (Exception se) {
            System.err.println("UseService: Threw an Exception requesting the available capacity of a facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }

}

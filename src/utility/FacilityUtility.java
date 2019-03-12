package utility;
import Domain.use.*;
import Domain.facility.Facility;
import java.util.List;
import java.time.LocalDate;
import DataAccess.database.*;

public class FacilityUtility {

    private FacilityDAO facDAO = new FacilityDAO();
    private UseDAO useDAO = new UseDAO();

    public void addNewFacility(Facility facility){
        //add to DataAccess.database
        try {
            facDAO.addNewFacility(facility);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception adding new Domain.facility.");
            System.err.println(se.getMessage());
        }
    }
    public void removeFacility(int id){
        //delete from DataAccess.database
        try {
            facDAO.removeFacility(id);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception removing Domain.facility.");
            System.err.println(se.getMessage());
        }
    }
    public void addFacilityDetail(int ID, int phoneNumber){
        //add detail
        try {
            facDAO.addFacilityDetail(ID, phoneNumber);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception updating phone in facility_detail.");
            System.err.println(se.getMessage());
        }
    }

    public Facility getFacilityInformation(int facilityID){
        //fetch information

        try {
            Facility fac = facDAO.getFacilityInformation(facilityID);
            return fac;
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving Domain.facility.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<Facility> listFacilities(){
        //list all facilities
        try {
            return facDAO.listFacilities();
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving list of facilities.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public int requestAvailableCapacity(Facility facility){
        //request available capacity
        try {

            List<FacilityUse> usage = useDAO.listActualUsage(facility);
            //default room number
            int usedRooms = 0;
            if (usage.size() > 0) {
                for (FacilityUse fu : usage) {
                    //if currently in Domain.use
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
            System.err.println("UseService: Threw an Exception requesting the available capacity of a Domain.facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }

}

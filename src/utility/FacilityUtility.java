package utility;
import facility.*;
import use.*;
import java.util.List;
import java.time.LocalDate;

public class FacilityUtility {

//    private FacilityDAO facDAO = new FacilityDAO();
//    private UseDAO useDAO = new UseDAO();

    public void addNewFacility(facility facility){
        //add to database
        try {
           // facDAO.addNewFacility(facility);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception adding new facility.");
            System.err.println(se.getMessage());
        }
    }
    public void removeFacility(int id){
        //delete from database
        try {
            //facDAO.removeFacility(id);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception removing facility.");
            System.err.println(se.getMessage());
        }
    }
    public void addFacilityDetail(int ID, int phoneNumber){
        //add a detail
        try {
            //facDAO.addFacilityDetail(ID, phoneNumber);
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception updating phone in facility_detail.");
            System.err.println(se.getMessage());
        }
    }

    public facility getFacilityInformation(int facilityID){
        //fetch information

        try {
            //Facility fac = facDAO.getFacilityInformation(facilityId);
            //return fac;
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving facility.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<facility> listFacilities(){
        //list all facilities
        try {
            //return facDAO.listFacilities();
        } catch (Exception se) {
            System.err.println("FacilityService: Threw an Exception retrieving list of facilities.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public int requestAvailableCapacity(facility facility){
        //request available capacity
        try {

//            List<FacilityUse> usage = useDAO.listActualUsage(fac);
//            int roomsInUse = 0;
//            if (usage.size() > 0) {
//                for (FacilityUse facUse : usage) {
//                    //if currently in use
//                    if ((LocalDate.now().equals(facUse.getStartDate()) || LocalDate.now().isAfter(facUse.getStartDate())) &
//                            LocalDate.now().equals(facUse.getEndDate()) || LocalDate.now().isBefore(facUse.getEndDate())) {
//                        if (facUse.getRoomNumber() == 0) {
//                            return 0;
//                        } else {
//                            roomsInUse = roomsInUse + 1;
//                        }
//                    }
//                }
//            }
//
//            return fac.getDetailsAboutFacility().getNumberOfRooms() - roomsInUse;

        } catch (Exception se) {
            System.err.println("UseService: Threw an Exception requesting the available capacity of a facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }

}

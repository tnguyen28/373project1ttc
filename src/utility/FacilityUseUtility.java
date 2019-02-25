package utility;
import facility.*;
import use.*;
import java.util.List;
import java.time.LocalDate;
import database.*;

public class FacilityUseUtility {

    private UseDAO useDAO = new UseDAO();

    public boolean isInUseDuringInterval(FacilityUse fu){
        if(fu.getRoomNumber() > fu.getFacilityDetails().getRoomNumber()){
            System.out.println("There are only " + fu.getFacilityDetails().getRoomNumber() + " number of rooms available.");
        } else if(fu.getStartDate().isAfter(fu.getEndDate())){
            System.out.println("End date cannot be before start date.");
        } else {
            try {
                return useDAO.isInUseDuringInterval(fu);
            } catch (Exception se) {
                System.err.println("FacilityUseUtility: Threw an Exception checking if facility is in use during interval.");
                System.err.println(se.getMessage());
            }
        }
        return true;
    }

    public void assignFacilityToUse(FacilityUse fu){
        if(fu.getRoomNumber() > fu.getFacilityDetails().getRoomNumber()){
            System.out.println("There are only " + fu.getFacilityDetails().getRoomNumber() + " number of rooms available.");
        } else if(isInUseDuringInterval(fu)){
            System.out.println("This room is already being used");
        } else {
            try {
                useDAO.assignFacilityToUse(fu);
            } catch (Exception se) {
                System.err.println("FacilityUseUtility: Threw an Exception assigning a facility to use.");
                System.err.println(se.getMessage());
            }
        }
    }
    public void vacateFacility(facility fac, int roomNumber){
        try {
            List<FacilityUse> usageList = listActualUsage(fac);
            if (roomNumber > fac.getFacilityDetails().getRoomNumber()) {
                System.out.println("There are only " + fac.getFacilityDetails().getRoomNumber() + " rooms at this facility.");
            } else {
                for (FacilityUse use : usageList) {
                    if ((use.getRoomNumber() == roomNumber))  {
                        if ((LocalDate.now().equals(use.getStartDate())) || LocalDate.now().isAfter(use.getStartDate())) {
                            if ((LocalDate.now().equals(use.getEndDate())) || (LocalDate.now().isBefore(use.getEndDate()))) {
                                useDAO.vacateFacility(fac, roomNumber);
                            }
                        } else {
                            System.out.println("Cannot vacate");
                        }
                    }
                }
            }
        }
        catch (Exception se) {
            System.err.println("FacilityUseUtility: Threw an Exception vacating a facility.");
            System.err.println(se.getMessage());
        }
    }

    public List<Inspection> listInspections(facility facility){
        try {
            return useDAO.listInspections(facility);
        } catch (Exception se) {
            System.err.println("FacilityUseUtility: Threw an Exception retrieving list of inspections.");
            System.err.println(se.getMessage());
        }
        return null;
    }
    public List<FacilityUse> listActualUsage(facility facility){
        try {
            return useDAO.listActualUsage(facility);
        } catch (Exception se) {
            System.err.println("FacilityUseUtility: Threw an Exception retrieving list of usage.");
            System.err.println(se.getMessage());
        }
        return null;
    }
    public double calcUsageRate(facility fac){
        try {
            FacilityUtility FS = new FacilityUtility();
            int totalRooms = fac.getFacilityDetails().getRoomNumber();
            int availableRooms = FS.requestAvailableCapacity(fac);
            int usedRooms = totalRooms - availableRooms;
            return Math.round(((double)usedRooms / totalRooms) * 100d)/100d;

        } catch (Exception se) {
            System.err.println("FacilityUseUtility: Threw an Exception retrieving calculate the usage rate.");
            System.err.println(se.getMessage());
        }

        return 0.00;
    }
    public LocalDate getFacilityStartDate(facility facility){
        try {
            return useDAO.getFacilityStartDate(facility);
        } catch (Exception se) {
            System.err.println("FacilityUseUtility: Threw an Exception retrieving the facility start date.");
            System.err.println(se.getMessage());
        }
        return null;
    }
}

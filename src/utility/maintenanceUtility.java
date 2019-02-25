package utility;
import maintenance.maintenance;
import facility.facility;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import database.*;

public class maintenanceUtility {

    private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    public maintenance makeFacilityMaintRequest(facility facility, String maintenanceDetails, int cost){
        //make facility request
        try {
           return maintenanceDAO.makeFacilityMaintRequest(facility, maintenanceDetails, cost);
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception making a facility maintenance request.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public void scheduleMaintenance(maintenance maintRequest){
          //schedule maintenance
        try {
            maintenanceDAO.scheduleMaintenance(maintRequest);
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception scheduling maintenance.");
            System.err.println(se.getMessage());
        }
    }

    public int calcMaintenanceCostForFacility(facility fac){
        try {
            return maintenanceDAO.calcMaintenanceCostForFacility(fac);
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception calculating "
                    + "maintenance cost for facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }
    public double calcProblemRateForFacility(facility facility){
        FacilityUseUtility useUtility = new FacilityUseUtility();
        try {
            LocalDate facilityStartDate = useUtility.getFacilityStartDate(facility);
            double totalDay = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
            return calcDownTimeForFacility(facility) / totalDay;
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception calculating the down time for a facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }
    public int calcDownTimeForFacility(facility facility){
        int downTime= 0;
        try {
            int completedMaintItems = maintenanceDAO.listMaintenance(facility).size();
            downTime = completedMaintItems * 3;
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception calculating the down time for a facility.");
            System.err.println(se.getMessage());
        }
        return downTime;
    }

    public List<maintenance> listMaintRequest(facility facility){
        try {
            return maintenanceDAO.listMaintRequests(facility);
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception listing maintenance requests.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<maintenance> listMaintenance(facility facility){
        try {
            return maintenanceDAO.listMaintenance(facility);
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception listing maintenance");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<maintenance> listFacilityProblems(facility facility){
        List<maintenance> facilityProblems = new ArrayList<maintenance>();
        try {
            facilityProblems.addAll(maintenanceDAO.listMaintRequests(facility));
            facilityProblems.addAll(maintenanceDAO.listMaintenance(facility));

            return facilityProblems;
        } catch (Exception se) {
            System.err.println("MaintenanceUtility: Threw an Exception listing facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }

}

package utility;
import maintenance.maintenance;
import facility.facility;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class maintenanceUtility {

    public maintenance makeFacilityMaintRequest(facility facility, int cost, String maintenanceDetails){
        //make facility request
        try {
          //  return maintenanceDAO.makeFacilityMaintRequest(facility, maintenanceDetails, cost);
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception making a facility maintenance request.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public void scheduleMaintenance(maintenance maintRequest){
          //schedule maintenance
        try {
           // maintenanceDAO.scheduleMaintenance(maintRequest);
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception scheduling maintenance.");
            System.err.println(se.getMessage());
        }
    }

    public int calcMaintenanceCostForFacility(facility fac){
        try {
           // return maintenanceDAO.calcMaintenanceCostForFacility(fac);
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception calculating "
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
            System.err.println("MaintenanceService: Threw an Exception calculating the down time for a facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }
    public int calcDownTimeForFacility(facility facility){
        int daysOfDownTime = 0;
        try {
            //int numberOfCompletedMaintItems = maintenanceDAO.listMaintenance(facility).size();
           // daysOfDownTime = numberOfCompletedMaintItems * 7;
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception calculating the down time for a facility.");
            System.err.println(se.getMessage());
        }
        return daysOfDownTime;
    }

    public List<maintenance> listMaintRequest(facility facility){
        try {
            //return maintenanceDAO.listMaintRequests(fac);
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception listing "
                    + "maintenance requests.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<maintenance> listMaintenance(facility facility){
        try {
           // return maintenanceDAO.listMaintenance(fac);
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception listing "
                    + "completed maintenance.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    public List<maintenance> listFacilityProblems(facility facility){
        List<maintenance> facilityProblems = new ArrayList<maintenance>();
        try {
           // facilityProblems.addAll(maintenanceDAO.listMaintRequests(fac));
           // facilityProblems.addAll(maintenanceDAO.listMaintenance(fac));

            //sort problems by cost
            Collections.sort(facilityProblems, new Comparator<maintenance>() {
                @Override
                public int compare(maintenance m1, maintenance m2) {
                    return (m2.getCost() > m1.getCost()) ? -1 : 1;
                }
            });

            return facilityProblems;
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception listing all facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }

}

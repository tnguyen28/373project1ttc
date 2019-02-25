package testClients;
import facility.*;
import utility.maintenanceUtility;
import maintenance.maintenance;
import java.util.*;


public class MaintenanceTestClient {
    public MaintenanceTestClient() throws Exception {

        maintenanceUtility ms = new maintenanceUtility();

        //TEST FACILITIES
        facility f1 = new facility();
        facility f2 = new facility();
        facility f3 = new facility();
        facilityDetail fd1 = new facilityDetail();
        facilityDetail fd2 = new facilityDetail();
        facilityDetail fd3 = new facilityDetail();

        f1.setFacilityID(1);
        f2.setFacilityID(2);
        f3.setFacilityID(3);


        fd1.setName("Mobile Dev");
        fd2.setName("Artificial Intelligence");
        fd3.setName("Virtual Reality");

        fd1.setRoomNumber(4);
        fd2.setRoomNumber(10);
        fd3.setRoomNumber(5);

        fd1.setPhoneNumber(1324567890);
        fd2.setPhoneNumber(1243567890);
        fd3.setPhoneNumber(1235467890);

        f1.setFacilityDetails(fd1);
        f2.setFacilityDetails(fd2);
        f3.setFacilityDetails(fd3);

        //make facility maintenance request
        System.out.println("Making Facility Maintenance Request");
        maintenance maintenance = ms.makeFacilityMaintRequest(f1, "water leak", 200);
        System.out.println("Request submitted");
        ms.scheduleMaintenance(maintenance);
        System.out.println("Maintenance scheduled");

        //calculate cost of request
        System.out.println("Calculate total maintenance cost");
        int totalCost = ms.calcMaintenanceCostForFacility(f1);
        System.out.println("Total cost for maintenance is " + totalCost + " at facility #" + f1.getFacilityID());

        //list maintenance requests
        System.out.println("Listing maintenance requests");
        List<maintenance> maintRequestList = ms.listMaintRequest(f3);
        Object[][] requests = new Object[maintRequestList.size() + 1][2];
        requests[0] = new Object[] {"Maintenance Request Details", "Cost"};
        for (int i = 1; i <= maintRequestList.size(); i++) {
            requests[i] = new Object[] {maintRequestList.get(i-1).getDetails(), maintRequestList.get(i-1).getCost()};
        }
        System.out.println("maintenance requests at Facility #" + f3.getFacilityID() + ":");
        for (Object[] row : requests) {
            System.out.format("\n", row);
        }
        System.out.println("List maintenance completed at a facility");
        List<maintenance> maintenanceList = ms.listMaintenance(f3);
        Object[][] maintenanceTable = new Object[maintenanceList.size() + 1][2];
        maintenanceTable[0] = new Object[] {"Maintenance Details", "Cost"};
        for (int i = 1; i <= maintenanceList.size(); i++) {
            maintenanceTable[i] = new Object[] {maintenanceList.get(i-1).getDetails(), maintenanceList.get(i-1).getCost()};
        }
        System.out.println("Maintenance completed at Facility #" + f3.getFacilityID() + ":");
        for (Object[] row : maintenanceTable) {
            System.out.format("\n", row);
        }

        //uses sample data to list facility problems, formatted as a table
        System.out.println("List all problems that have affected a facility");
        List<maintenance> fpList = ms.listFacilityProblems(f3);
        Object[][] problems = new Object[fpList.size() + 1][2];
        problems[0] = new Object[] {"Facility Problem", "Cost"};
        for (int i = 1; i <= fpList.size(); i++) {
            problems[i] = new Object[] {fpList.get(i-1).getDetails(), fpList.get(i-1).getCost()};
        }
        System.out.println("Problems at Facility #" + f3.getFacilityID() + ":");
        for (Object[] row : problems) {
            System.out.format("\n", row);
        }

        System.out.println("Calculating the down time for a facility");
        int downTime = ms.calcDownTimeForFacility(f3);
        System.out.println("Facility #" + f3.getFacilityID() + " was down for " + downTime + " days. ");

        System.out.println("Calculating the problem rate for a facility");
        double problemRate = ms.calcProblemRateForFacility(f3) * 100;
        System.out.print("\nThe problem rate at Facility #" + f3.getFacilityID() + " is %" + problemRate);


    }
}

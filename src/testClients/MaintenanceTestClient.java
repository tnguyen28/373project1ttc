package testClients;
import facility.*;
import utility.maintenanceUtility;
import maintenance.maintenance;


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
        maintenance maintenance = ms.makeFacilityMaintRequest(f1, 100, "water leak");
        System.out.println("Request submitted");

        //schedule facility maintenance request
        System.out.println("Scheduling maintenance request");
        ms.scheduleMaintenance(maintenance);
        System.out.println("Request Scheduled");

        //calculate cost of request
        System.out.println("Calculate total maintenance cost");
        int totalCost = ms.calcMaintenanceCostForFacility(f1);
        System.out.println("Total cost for maintenance is " + totalCost + " at facility #" + f1.getFacilityID());

        //list maintenance requests
        System.out.println("Listing maintenance requests");

        //list completed requests

    }
}

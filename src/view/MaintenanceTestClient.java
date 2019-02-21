package view;
import facility.*;
import utility.maintenanceUtility;
import maintenance.maintenance;
import java.util.*;


public class MaintenanceTestClient {
    public MaintenanceTestClient() throws Exception {
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
    }
}

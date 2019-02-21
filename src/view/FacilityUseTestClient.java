package view;

import facility.*;
import utility.*;
import use.*;

import java.time.LocalDate;

public class FacilityUseTestClient {
    public FacilityUseTestClient() throws Exception {
        FacilityUtility FU = new FacilityUtility();
        FacilityUseUtility FUU = new FacilityUseUtility();


        //TEST FACILITIES
        facility f1 = new facility();
        facility f2 = new facility();
        facility f3 = new facility();
        FacilityUse fuse = new FacilityUse();

        facilityDetail fd1 = new facilityDetail();
        facilityDetail fd2 = new facilityDetail();
        facilityDetail fd3 = new facilityDetail();
        facilityDetail fufd = new facilityDetail();

        f1.setFacilityID(1);
        f2.setFacilityID(2);
        f3.setFacilityID(3);
        fuse.setFacilityID(4);
        fufd.setFacilityID(4);

        fd1.setName("Mobile Dev");
        fd2.setName("Artificial Intelligence");
        fd3.setName("Virtual Reality");
        fufd.setName("Continuous Integration");

        fd1.setRoomNumber(4);
        fd2.setRoomNumber(10);
        fd3.setRoomNumber(5);
        fufd.setRoomNumber(1);

        fd1.setPhoneNumber(1324567890);
        fd2.setPhoneNumber(1243567890);
        fd3.setPhoneNumber(1235467890);

        f1.setFacilityDetails(fd1);
        f2.setFacilityDetails(fd2);
        f3.setFacilityDetails(fd3);
        fuse.setFacilityDetails(fufd);

        FU.addNewFacility(fuse);
        fuse.setStartDate(LocalDate.of(2019, 1, 20));
        fuse.setEndDate(LocalDate.of(2019, 2,15));
        fuse.setRoomNumber(1);


        //listing example  inspection
        System.out.println("Inspection for: " + f3.getFacilityDetails().getName());
        for(Inspection inspection : FUU.listInspections(f3)){
            System.out.println("Detail: " + inspection.getInspectionDetail());
        }

        //check if it is being used
        boolean intervalCheck = FUU.isInUseDuringInterval(fuse);

    }
}

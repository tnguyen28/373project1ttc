package testClients;

import facility.*;
import utility.*;
import use.*;
import java.util.List;
import java.time.LocalDate;

public class FacilityUseTestClient {
    public FacilityUseTestClient() throws Exception {
        FacilityUtility FU = new FacilityUtility();
        FacilityUseUtility FUU = new FacilityUseUtility();

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



        //listing example inspection
        System.out.println("Inspection for Number" + f3.getFacilityID() + ": " + f3.getFacilityDetails().getName());
        for(Inspection inspection : FUU.listInspections(f3)){
            System.out.println("Detail: " + inspection.getInspectionDetail());
            System.out.println("Location: " + inspection.getInspectionLocation());
        }

        //check if it is being used
        System.out.println("Check if facility is being used during an interval");
        boolean intervalCheck = FUU.isInUseDuringInterval(fuse);

        System.out.println("Facility Number" + fuse.getFacilityID());
        if(fuse.getRoomNumber() != 0){
            System.out.println("Room: " + fuse.getRoomNumber());
        }
        if(intervalCheck){
            System.out.print(" is being used from " + fuse.getStartDate() + " until " + fuse.getEndDate());
        } else {
            System.out.println("is not being used ");
        }

        //assign facility to use
        FUU.assignFacilityToUse(fuse);
        System.out.println("Facility assigned");

        //check if in use
        boolean intervalCheck1 = FUU.isInUseDuringInterval(fuse);

        System.out.println("Facility Number" + fuse.getFacilityID());
        System.out.println("Room: " + fuse.getRoomNumber());

        if(intervalCheck1){
            System.out.print(" is being used from " + fuse.getStartDate() + " until " + fuse.getEndDate() + ".");
        } else {
            System.out.println("is not being used.");
        }

        //list usage from database
        List<FacilityUse> usageList = FUU.listActualUsage(fuse);
        Object[][] usage = new Object[usageList.size() + 1][3];
        usage[0] = new Object[] {"Room Number", "Start Date", "End Date"};
        for (int i = 1; i <= usageList.size(); i++) {
            usage[i] = new Object[] {usageList.get(i-1).getRoomNumber(), usageList.get(i-1).getStartDate().toString(),
                    usageList.get(i-1).getEndDate().toString()};
        }
        System.out.println("Usage at Facility Number" + fuse.getFacilityID());
        for (Object[] row : usage) {
            System.out.format("\n", row);

        }

        //vacating a facility
        System.out.println("Vacating a facility");
        FUU.vacateFacility(fuse, 1);
        System.out.println("Facility vacated");

        //list usage from database
        List<FacilityUse> usageList2 = FUU.listActualUsage(fuse);
        Object[][] usage2 = new Object[usageList2.size() + 1][3];
        usage2[0] = new Object[] {"Room Number", "Start Date", "End Date"};
        for (int i = 1; i <= usageList2.size(); i++) {
            usage2[i] = new Object[] {usageList2.get(i-1).getRoomNumber(), usageList2.get(i-1).getStartDate().toString(),
                    usageList2.get(i-1).getEndDate().toString()};
        }
        System.out.println("Usage at Facility Number" + fuse.getFacilityID());
        for (Object[] row : usage2) {
            System.out.format("\n", row);
        }

        //calculate usage rate
        double rate = (int) FUU.calcUsageRate(f3) * 100;
        System.out.println("Usage rate at facility Number" + f3.getFacilityID() + ": "  + rate + "%");


    }
}

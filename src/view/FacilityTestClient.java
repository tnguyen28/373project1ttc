package view;
import facility.*;
import use.FacilityUse;
import utility.*;

public class FacilityTestClient {
    public FacilityTestClient() throws Exception{
        FacilityUse FU = new FacilityUse();

        //set up test facilities for testing
        facility f1 = new facility();
        facility f2 = new facility();
        facility f3 = new facility();
        facility f4 = new facility();

        facilityDetail fd1 = new facilityDetail();
        facilityDetail fd2 = new facilityDetail();
        facilityDetail fd3 = new facilityDetail();
        facilityDetail fd4 = new facilityDetail();

        f1.setFacilityID(1);
        f2.setFacilityID(2);
        f3.setFacilityID(3);
        f4.setFacilityID(4);

        fd1.setRoomNumber(10);
        fd2.setRoomNumber(1);
        fd3.setRoomNumber(5);
        fd4.setRoomNumber(8);

        f1.setFacilityDetails(fd1);
        f2.setFacilityDetails(fd2);
        f3.setFacilityDetails(fd3);
        f4.setFacilityDetails(fd4);
    }
}

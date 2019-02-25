package testClients;
import facility.*;
import utility.*;
import java.util.List;


public class FacilityTestClient {
    public FacilityTestClient() throws Exception{

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


        //SPECIFIC FACILITY EXAMPLE
        FacilityUtility FU = new FacilityUtility();

        facility facility = new facility();
        facility.setFacilityID(100);
        facilityDetail facilityDetail = new facilityDetail();
        facilityDetail.setFacilityID(100);
        facilityDetail.setName("IoT Department");
        facilityDetail.setRoomNumber(3);
        //facilityDetail.setPhoneNumber(1234567890);
        facility.setFacilityDetails(facilityDetail);

        FU.addNewFacility(facility);
        System.out.println("Facility " + facilityDetail.getName() + " has been added to the database.");

        System.out.println("Here are the details:");

        //initial display of facility

        System.out.println("FacilityID: " + facility.getFacilityID());
        System.out.println("Facility Name: " + facilityDetail.getName());
        System.out.println("Facility Room Number: " + facilityDetail.getRoomNumber());
        System.out.println("Facility Phone Number: " + facilityDetail.getPhoneNumber());

        //adding phone number
        System.out.println("Trying to add a phone number");
        FU.addFacilityDetail(100, 1234567890);

        System.out.println("FacilityID: " + facility.getFacilityID());
        System.out.println("Facility Name: " + facilityDetail.getName());
        System.out.println("Facility Room Number: " + facilityDetail.getRoomNumber());
        System.out.println("Facility Phone Number: " + facilityDetail.getPhoneNumber());

        //requesting available capacity
        int availableRooms = FU.requestAvailableCapacity(facility);
        System.out.println("There are " + availableRooms + " rooms available in " + facility.getFacilityDetails().getName());

        //removing facility
        FU.removeFacility(100);
        System.out.println("Facility #100 has been removed.");

        //show list of all facilities
        System.out.println("Here is the list of facilities");
        List<facility> facilityList = FU.listFacilities();
        for(facility f : facilityList){
            facilityDetail fd = f.getFacilityDetails();
            System.out.println(fd.getName());

        }

    }

}

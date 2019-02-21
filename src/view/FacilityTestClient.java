package view;
import facility.*;
import use.FacilityUse;
import utility.*;

public class FacilityTestClient {
    public FacilityTestClient() throws Exception{
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

        //facility fetchFacility = FU.getFacilityInformation(100);
        //facilityDetail fetchFacilityDetail = fetchFacility.getFacilityDetails();

        displayFacilityDetails(FU.getFacilityInformation(100));

        //adding facilityDetail
        FU.addFacilityDetail(100, 1234567890);


    }

    public void displayFacilityDetails(facility fetchFacility){
        facilityDetail fetchFacilityDetail = fetchFacility.getFacilityDetails();
        System.out.println("FacilityID: " + fetchFacility.getFacilityID());
        System.out.println("Facility Name: " + fetchFacilityDetail.getName());
        System.out.println("Facility Room Number: " + fetchFacilityDetail.getRoomNumber());
        System.out.println("Facility Phone Number: " + fetchFacilityDetail.getPhoneNumber());
    }
}

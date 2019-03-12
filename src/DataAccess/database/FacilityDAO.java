package DataAccess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.facility.*;

public class FacilityDAO {

    public FacilityDAO() {}

    public void removeFacility(int ID) {

        try {
            //remove from facility_detail table
            Statement st = DBHelper.getConnection().createStatement();
            String removeFacilityDetailQuery = "delete from facility_detail where facility_id = '" + ID + "'";
            st.execute(removeFacilityDetailQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityDetailQuery);
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException removing the Domain.facility");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from Domain.facility table
            Statement st = DBHelper.getConnection().createStatement();
            String removeFacilityQuery = "delete from facility where id = '" + ID + "'";
            st.execute(removeFacilityQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityQuery);
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException removing the Domain.facility ");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
        try {
            Statement st = DBHelper.getConnection().createStatement();
            String removeFacilityUseQuery = "delete from use where facility_id = '" + ID + "'";
            st.execute(removeFacilityUseQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityUseQuery);
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException removing the Domain.facility");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public Facility getFacilityInformation(int ID) {

        try {

            Facility f1 = new Facility();
            f1.setFacilityID(ID);

            //Get details about Domain.facility
            Statement st = DBHelper.getConnection().createStatement();
            String selectDetailQuery = "SELECT name,facility_id, number_of_rooms, phone_number FROM facility_detail WHERE facility_id = '" + ID + "'";
            ResultSet detailRS = st.executeQuery(selectDetailQuery);
            FacilityDetail detail = new FacilityDetail();

            System.out.println("FacilityDAO: *************** Query " + selectDetailQuery);

            while ( detailRS.next() ) {
                detail.setName(detailRS.getString("name"));
                detail.setFacilityID(detailRS.getInt("facility_id"));
                detail.setRoomNumber(detailRS.getInt("number_of_rooms"));
                if (detailRS.getInt("phone") != 0) {
                    detail.setPhoneNumber(detailRS.getInt("phone"));
                }
            }

            f1.setFacilityDetails(detail);

            //close to manage resources
            detailRS.close();

            return f1;
        }

        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException retrieving the Domain.facility");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    public void addNewFacility(Facility fac) {
        Connection con = DBHelper.getConnection();
        PreparedStatement facPst = null;
        PreparedStatement addPst = null;

        try {
            //Insert the Domain.facility object
            String facStm = "INSERT INTO facility(id) VALUES(?)";
            facPst = con.prepareStatement(facStm);
            facPst.setInt(1, fac.getFacilityID());
            facPst.executeUpdate();

            //Insert the facility_detail object
            String addStm = "INSERT INTO facility_detail(name, facility_id, number_of_rooms, phone_number) VALUES(?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            addPst.setString(1, fac.getFacilityDetails().getName());
            addPst.setInt(2, fac.getFacilityDetails().getFacilityID());
            addPst.setInt(3, fac.getFacilityDetails().getRoomNumber());
            addPst.executeUpdate();
        } catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                    addPst.close();
                    facPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("FacilityDAO: Threw SQLException saving Domain.facility");
                System.err.println(ex.getMessage());
            }
        }
    }

    public void addFacilityDetail(int ID, int phoneNumber) {

        try {
            Connection con = DBHelper.getConnection();
            PreparedStatement facPst = null;
            //Get Domain.facility

            String updateFacilityDetailQuery = "UPDATE facility_detail SET phone_number = ? WHERE facility_id = ?";

            facPst = con.prepareStatement(updateFacilityDetailQuery);
            facPst.setInt(1, phoneNumber);
            facPst.setInt(2, ID);
            facPst.executeUpdate();

            System.out.println("FacilityDAO: *************** Query " + updateFacilityDetailQuery);

            //close to manage resources
            facPst.close();
            con.close();

        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException updating the phone number.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public List<Facility> listFacilities() {

        List<Facility> listOfFac = new ArrayList<Facility>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String facilitiesQuery = "SELECT * FROM facility";

            ResultSet facRS = st.executeQuery(facilitiesQuery);
            System.out.println("FacilityDAO: *************** Query " + facilitiesQuery);

            Facility f1 = new Facility();
            while ( facRS.next() ) {
                f1.setFacilityID(facRS.getInt("id"));
                listOfFac.add(getFacilityInformation(f1.getFacilityID()));
            }

            //close to manage resources
            facRS.close();
            st.close();

            return listOfFac;

        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException retrieving list of facilities.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;

    }

}
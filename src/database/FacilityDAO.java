package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import facility.*;

public class FacilityDAO {

    public FacilityDAO() {}

    public void removeFacility(int ID) {

        try {
            Statement st = DBHelper.getConnection().createStatement();
            String removeFacilityUseQuery = "delete from use where facility_id = '" + ID + "'";
            st.execute(removeFacilityUseQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityUseQuery);
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException removing the Facility from Use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

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
            System.err.println("FacilityDAO: Threw SQLException removing the Facility Detail from Facility Detail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from facility table
            Statement st = DBHelper.getConnection().createStatement();
            String removeFacilityQuery = "delete from facility where id = '" + ID + "'";
            st.execute(removeFacilityQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityQuery);
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException removing the Facility object from Facility table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public facility getFacilityInformation(int ID) {

        try {

            facility f1 = new facility();
            f1.setFacilityID(ID);

            //Get details about facility
            Statement st = DBHelper.getConnection().createStatement();
            String selectDetailQuery = "SELECT name,facility_id, number_of_rooms, phone_number FROM facility_detail WHERE facility_id = '" + ID + "'";
            ResultSet dRS = st.executeQuery(selectDetailQuery);
            facilityDetail detail = new facilityDetail();

            System.out.println("FacilityDAO: *************** Query " + selectDetailQuery);

            while ( dRS.next() ) {
                detail.setName(dRS.getString("name"));
                detail.setFacilityID(dRS.getInt("facility_id"));
                detail.setRoomNumber(dRS.getInt("number_of_rooms"));
                if (dRS.getInt("phone") != 0) {
                    detail.setPhoneNumber(dRS.getInt("phone"));
                }
            }

            f1.setFacilityDetails(detail);

            //close to manage resources
            dRS.close();

            return f1;
        }

        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw SQLException retrieving the Facility object.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    public void addNewFacility(facility fac) {
        Connection con = DBHelper.getConnection();
        PreparedStatement facPst = null;
        PreparedStatement addPst = null;

        try {
            //Insert the facility object
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
                System.err.println("FacilityDAO: Threw SQLException saving facility object.");
                System.err.println(ex.getMessage());
            }
        }
    }

    public void addFacilityDetail(int ID, int phoneNumber) {

        try {
            Connection con = DBHelper.getConnection();
            PreparedStatement facPst = null;
            //Get facility

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

    public List<facility> listFacilities() {

        List<facility> listOfFac = new ArrayList<facility>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String facilitiesQuery = "SELECT * FROM facility";

            ResultSet facRS = st.executeQuery(facilitiesQuery);
            System.out.println("FacilityDAO: *************** Query " + facilitiesQuery);

            facility f1 = new facility();
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
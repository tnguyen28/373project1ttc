package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import facility.*;
import use.*;
public class UseDAO {

    public UseDAO() {}


    public List<Inspection> listInspections(facility fac) {

        List<Inspection> listOfInspec = new ArrayList<Inspection>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listInspectionsQuery = "SELECT * FROM inspection WHERE "
                    + "facility_id = '" + fac.getFacilityID() + "'";

            ResultSet useRS = st.executeQuery(listInspectionsQuery);
            System.out.println("UseDAO: *************** Query " + listInspectionsQuery + "\n");

            while ( useRS.next() ) {
                Inspection ins = new Inspection();
                ins.setInspectionLocation(useRS.getString("inspection_location"));
                ins.setInspectionDetail(useRS.getString("inspection_detail"));
                ins.setFacilityID(fac.getFacilityID());
                listOfInspec.add(ins);
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retrieving ");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfInspec;

    }

    public boolean isInUseDuringInterval(FacilityUse facUse) {

        boolean result = false;
        try {
            Statement st = DBHelper.getConnection().createStatement();
            String selectUse = "SELECT * FROM use WHERE facility_id = " + facUse.getFacilityID() +
                    " AND room_number IN (0, " + facUse.getRoomNumber() + ")";

            ResultSet useRS = st.executeQuery(selectUse);
            System.out.println("UseDAO: *************** Query " + selectUse + "\n");

            while (useRS.next()) {
                LocalDate start = useRS.getDate("start_date").toLocalDate();
                LocalDate end = useRS.getDate("end_date").toLocalDate();
                if (facUse.getStartDate().isBefore(end) && (start.isBefore(facUse.getEndDate()) ||
                        start.equals(facUse.getEndDate()))) {
                    result = true;
                }
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException checking if facility is in use during an interval.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return result;

    }

    public void assignFacilityToUse(FacilityUse facUse) {

        Connection con = DBHelper.getConnection();
        PreparedStatement usePst = null;

        try {
            //Insert the facility ID, room number, and start/end dates into use table
            String useStm = "INSERT INTO use (facility_id, room_number, start_date, "
                    + "end_date) VALUES (?, ?, ?, ?)";
            usePst = con.prepareStatement(useStm);
            usePst.setInt(1, facUse.getFacilityID());
            usePst.setInt(2, facUse.getRoomNumber());
            usePst.setDate(3, Date.valueOf(facUse.getStartDate()));
            usePst.setDate(4, Date.valueOf(facUse.getEndDate()));
            usePst.executeUpdate();
            System.out.println("UseDAO: *************** Query " + usePst + "\n");

            //close to manage resources
            usePst.close();
            con.close();
        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException assigning a facility "
                    + "to use in the use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public List<FacilityUse> listActualUsage(facility fac) {

        List<FacilityUse> listOfUsage = new ArrayList<FacilityUse>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listUsageQuery = "SELECT * FROM use WHERE facility_id = '" +
                    fac.getFacilityID() + "' ORDER BY room_number, start_date";

            ResultSet useRS = st.executeQuery(listUsageQuery);
            System.out.println("UseDAO: *************** Query " + listUsageQuery);

            while ( useRS.next() ) {
                FacilityUse use = new FacilityUse();
                use.setFacilityID(fac.getFacilityID());
                use.setRoomNumber(useRS.getInt("room_number"));
                use.setStartDate(useRS.getDate("start_date").toLocalDate());
                use.setEndDate(useRS.getDate("end_date").toLocalDate());
                listOfUsage.add(use);
            }

            //close to manage resources
            useRS.close();
            st.close();
            return listOfUsage;

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retrieving usage list.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;

    }


    public void vacateFacility(facility facility, int roomNumber) {

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String vacate = "";

            List<FacilityUse> usageList = listActualUsage(facility);
            for (FacilityUse use : usageList) {
                if ((use.getRoomNumber() == roomNumber) & ((LocalDate.now().isAfter(use.getStartDate())) & LocalDate.now().isBefore(use.getEndDate()))) {
                    vacate = "UPDATE use SET end_date = '" + Date.valueOf(use.getEndDate().plusDays(1)) +
                            "' WHERE facility_id = " + facility.getFacilityID() + "AND room_number = " + roomNumber +
                            "AND start_date = '" + Date.valueOf(use.getStartDate()) + "'";
                }
            }

            st.execute(vacate);
            System.out.println("UseDAO: *************** Query " + vacate);

        }
        catch (SQLException se){
            System.err.println("UseDAO: Threw a SQLException vacating the facility.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public LocalDate getFacilityStartDate(facility fac) {

        LocalDate facilityStartDate = null;
        try {

            Statement st = DBHelper.getConnection().createStatement();
            String getFacilityStartDateQuery = "SELECT start_date FROM use WHERE facility_id = '" +
                    fac.getFacilityID() + "' ORDER BY start_date LIMIT 1";

            ResultSet useRS = st.executeQuery(getFacilityStartDateQuery);
            System.out.println("UseDAO: *************** Query " + getFacilityStartDateQuery + "\n");

            while ( useRS.next() ) {
                facilityStartDate = useRS.getDate("start_date").toLocalDate();
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retrieving facility start date.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return facilityStartDate;
    }


}

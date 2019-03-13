package DataAccess.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Domain.maintenance.*;
import Domain.facility.*;

public class MaintenanceDAO {

    public MaintenanceDAO() {}

    public Maintenance makeFacilityMaintRequest(Facility facility, String maintenanceDetails, int cost) {

        try {

            Maintenance maint = new Maintenance();
            maint.setDetails(maintenanceDetails);
            maint.setCost(cost);

            Statement st = DBHelper.getConnection().createStatement();
            String makeMaintRequestQuery = "INSERT INTO maint_request (facility_id, details, cost) VALUES (" +
                    facility.getFacilityID() + ", '" + maintenanceDetails + "', " + cost + ")";
            st.execute(makeMaintRequestQuery);
            System.out.println("MaintenanceDAO: *************** Query " + makeMaintRequestQuery);

            //close to manage resources
            st.close();

            return maint;

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException making a Domain.maintenance request.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;

    }

    public void scheduleMaintenance(Maintenance maintRequest, Facility fac) {

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String scheduleMaintenanceAddQuery = "INSERT INTO maintenance (facility_id, details, cost) VALUES (" +
                    fac.getFacilityID() + ", '" + maintRequest.getDetails() +
                    "', " + maintRequest.getCost() + ")";
            st.execute(scheduleMaintenanceAddQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceAddQuery );

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException adding a Domain.maintenance ");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String scheduleMaintenanceRemoveQuery = "DELETE FROM maint_request WHERE facility_id = " +
                    fac.getFacilityID() + " AND details = " + maintRequest.getDetails();
            st.execute(scheduleMaintenanceRemoveQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceRemoveQuery);

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException removing scheduled Domain.maintenance");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public int calcMaintenanceCostForFacility(Facility facility) {
        int total= 0;

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM maintenance "
                    + "WHERE facility_id = " + facility.getFacilityID();
            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while ( maintRS.next() ) {
                total = maintRS.getInt(1);
            }
            System.out.println("MaintenanceDAO: *************** Query " + calcMaintenanceCostQuery);

            //close to manage resources
            maintRS.close();
            st.close();

            return total;

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException calculating total.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return 0;
    }

    public List<Maintenance> listMaintRequests(Facility facility) {

        List<Maintenance> listOfMaintRequests = new ArrayList<Maintenance>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listMaintRequestsQuery = "SELECT * FROM maint_request WHERE facility_id = " +
                    facility.getFacilityID();

            ResultSet maintRS = st.executeQuery(listMaintRequestsQuery);
            System.out.println("MaintenanceDAO: *************** Query " + listMaintRequestsQuery);

            while ( maintRS.next() ) {
                Maintenance maintenanceRequest = new Maintenance();
                maintenanceRequest.setDetails(maintRS.getString("details"));
                maintenanceRequest.setCost(maintRS.getInt("cost"));
                listOfMaintRequests.add(maintenanceRequest);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException retreiving list of Domain.maintenance ");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfMaintRequests;

    }

    public List<Maintenance> listMaintenance(Facility fac) {

        List<Maintenance> listOfCompletedMaintenance = new ArrayList<Maintenance>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listMaintenanceQuery = "SELECT * FROM maintenance WHERE facility_id = " +
                    fac.getFacilityID();

            ResultSet maintRS = st.executeQuery(listMaintenanceQuery);
            System.out.println("UseDAO: *************** Query " + listMaintenanceQuery);

            while ( maintRS.next() ) {
                Maintenance maintenance = new Maintenance();
                maintenance.setDetails(maintRS.getString("details"));
                maintenance.setCost(maintRS.getInt("cost"));
                listOfCompletedMaintenance.add(maintenance);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retrieving list of Domain.maintenance ");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfCompletedMaintenance;

    }

}
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import facility.*;
import maintenance.*;

public class MaintenanceDAO {

    public MaintenanceDAO() {}

    public maintenance makeFacilityMaintRequest(facility fac, String maintenanceDetails, int cost) {

        try {

            maintenance maint = new maintenance();
            maint.setDetails(maintenanceDetails);
            maint.setCost(cost);
            maint.setFacilityID(fac.getFacilityID());

            Statement st = DBHelper.getConnection().createStatement();
            String makeMaintRequestQuery = "INSERT INTO maint_request (facility_id, details, cost) VALUES (" +
                    fac.getFacilityID() + ", '" + maintenanceDetails + "', " + cost + ")";
            st.execute(makeMaintRequestQuery);
            System.out.println("MaintenanceDAO: *************** Query " + makeMaintRequestQuery + "\n");

            //close to manage resources
            st.close();

            return maint;

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException making a maintenance request.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;

    }

    public void scheduleMaintenance(maintenance maintRequest) {

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String scheduleMaintenanceAddQuery = "INSERT INTO maintenance (facility_id, details, cost) VALUES (" +
                    maintRequest.getFacilityID() + ", '" + maintRequest.getDetails() +
                    "', " + maintRequest.getCost() + ")";
            st.execute(scheduleMaintenanceAddQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceAddQuery + "\n");

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException adding a maintenance "
                    + "request to maintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String scheduleMaintenanceRemoveQuery = "DELETE FROM maint_request WHERE facility_id = " +
                    maintRequest.getFacilityID() + " AND details = '" + maintRequest.getDetails() +
                    "' AND cost = " + maintRequest.getCost();
            st.execute(scheduleMaintenanceRemoveQuery);
            System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException removing a "
                    + "maintenance request from maint_request table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }

    public int calcMaintenanceCostForFacility(facility fac) {

        try {

            int totalCost = 0;

            Statement st = DBHelper.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM maintenance "
                    + "WHERE facility_id = " + fac.getFacilityID();
            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while ( maintRS.next() ) {
                totalCost = maintRS.getInt(1);
            }
            System.out.println("MaintenanceDAO: *************** Query " + calcMaintenanceCostQuery + "\n");

            //close to manage resources
            maintRS.close();
            st.close();

            return totalCost;

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException calculating total "
                    + "maintenance cost from maintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return 0;
    }

    public List<maintenance> listMaintRequests(facility fac) {

        List<maintenance> listOfMaintRequests = new ArrayList<maintenance>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listMaintRequestsQuery = "SELECT * FROM maint_request WHERE facility_id = '" +
                    fac.getFacilityID() + "' ORDER BY cost";

            ResultSet maintRS = st.executeQuery(listMaintRequestsQuery);
            System.out.println("UseDAO: *************** Query " + listMaintRequestsQuery + "\n");

            while ( maintRS.next() ) {
                maintenance maintenanceRequest = new maintenance();
                maintenanceRequest.setDetails(maintRS.getString("details"));
                maintenanceRequest.setCost(maintRS.getInt("cost"));
                maintenanceRequest.setFacilityID(fac.getFacilityID());
                listOfMaintRequests.add(maintenanceRequest);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
                    + "requests from maint_request table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfMaintRequests;

    }

    public List<maintenance> listMaintenance(facility fac) {

        List<maintenance> listOfCompletedMaintenance = new ArrayList<maintenance>();

        try {

            Statement st = DBHelper.getConnection().createStatement();
            String listMaintenanceQuery = "SELECT * FROM maintenance WHERE facility_id = '" +
                    fac.getFacilityID() + "' ORDER BY cost";

            ResultSet maintRS = st.executeQuery(listMaintenanceQuery);
            System.out.println("UseDAO: *************** Query " + listMaintenanceQuery + "\n");

            while ( maintRS.next() ) {
                maintenance maintenance = new maintenance();
                maintenance.setDetails(maintRS.getString("details"));
                maintenance.setCost(maintRS.getInt("cost"));
                maintenance.setFacilityID(fac.getFacilityID());
                listOfCompletedMaintenance.add(maintenance);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
                    + "from maintenanace table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfCompletedMaintenance;

    }

}
package testClients;

public class MainClient {
    public static void main(String[] args) throws Exception{
        FacilityTestClient ftc = new FacilityTestClient();
        MaintenanceTestClient mtc = new MaintenanceTestClient();
        FacilityUseTestClient utc = new FacilityUseTestClient();
    }
}

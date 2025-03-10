package utils;

public class Constant
{
    public static final String URL = "https://www.kurryzo.com";
    public static final String Username = "testuser_1";
    public static final String Password = "Test@123";
    
    // Use user directory for test data paths
    private static final String USER_DIR = System.getProperty("user.dir");
    
    public static final String Path_TestData = USER_DIR + "\\src\\test\\resources";
    public static final String Path_jsonTestData = USER_DIR + "\\src\\test\\java\\testDataTypes";

    // Test files, using user directory
    public static final String File_Testdata_DailyMenu = "dailyMenu.json";
    public static final String File_Testdata_PartnerLogin = "PartnerLogin.json";
    public static final String File_Testdata_CustomerLogin = "CustomerLogin.json";
    public static final String File_Testdata_labelheader = "LabelheaderandLink.json";
    public static final String File_Testdata_DailyMenu1 = "nested.json";
    public static final String File_Testdata_CouponList = "CouponList.json";
    public static final String File_QuickAddJsonPath = "QuickAdd.json";
    
    // Full path for QuickAdd.json using user directory
    public static final String File_QuickAddJsonFilePath = USER_DIR + "\\src\\test\\java\\testDataTypes\\QuickAdd.json";
    
    public static final String StoreName = "Mavendhan Automation Service";
    public static final String StoreNameSmokeTest =  "Mavendhan Automation Service Store Level";
    public static final String SuperAdminSmokeTest =  "SuperAdmin";
    public static final String StoreNameRambhavanTest =  "Rambhavan Test";
    public static final String SupervisorLogin =  "SupervisorLogin";
    public static final String File_Testdata_PartyMenu = "PartyOrderMenu.json";
    
    
    //Order Status Message
    
    public static final String TabNew = "New";
    public static final String TabInProcess = "in-Process";
    public static final String TabPreparation = "Preparation";
    public static final String TabPickupDelivery_Pickup = "Ready for Pickup";
    public static final String TabPickupDelivery_Delivery = "Send for Delivery";
    public static final String TabComplete = "Delivered";

}

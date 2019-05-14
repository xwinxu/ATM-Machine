//package test;
//
//import main.clients.BankManager;
//import main.utilities.KeyValueFileHandler;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//public class BankManagerUITest {
//
//    private static final String ALERTS_FILENAME = "phase1/alerts.txt";
//
//    private KeyValueFileHandler handler;
//
//    private BankManager manager;
//
//    @Before
//    public void SetUp(){
//        manager = new BankManager();
//        handler = new KeyValueFileHandler();
//    }
//
//    @Test
//    public void testLogin() {
//        // Pretend to be a user and enter the login name ...
//        String id = "Sally\n";
//        //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
//        InputStream inputStream = new ByteArrayInputStream(id.getBytes(Charset.forName("UTF-8")));
//
//        System.setIn(inputStream);
//
//        BankManager bankManager = new BankManager();
////        boolean loginSuccess = bankManager.login();
//
////        assertTrue(loginSuccess);
//    }
//
//}

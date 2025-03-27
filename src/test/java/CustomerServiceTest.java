import edu.icet.mos.Main;
import edu.icet.mos.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class CustomerServiceTest {


    @Autowired
    CustomerService service;

    @Test
    public void testCals(){
        Assertions.assertEquals(20,service.cal(10,20));
        Assertions.assertEquals(20,service.cal(10,10));
        Assertions.assertEquals(20,service.cal(30,10));
    }
}

import com.yzp.spring.springbootsamples.basic.BasicApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicApplication.class)
public class Test {
    @org.junit.Test
    public void test(){
        System.out.println("sdf");
    }
}

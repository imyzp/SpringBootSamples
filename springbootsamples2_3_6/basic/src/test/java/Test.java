import com.yzp.spring.springbootsamples.basic.BasicApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BasicApplication.class)
public class Test {
    @org.junit.Test
    public void test(){
        System.out.println("sdf");
    }
}

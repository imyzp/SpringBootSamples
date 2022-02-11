import com.yzp.spring.springbootsamples.elasticjob.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @org.junit.Test
    public void test(){
        String sql="insert into person (name, sex, age, state) values (?,?,?,?)";
        Random random=new Random();
        for (int i=0;i<1000;i++){
            jdbcTemplate.update(sql,new Object[]{"david"+i,random.nextInt(2),random.nextInt(100),0});
        }
    }
}

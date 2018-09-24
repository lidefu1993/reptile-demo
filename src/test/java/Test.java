import com.ldf.reptile.App;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ldf on 2018/9/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Test {

    public void test(){
        System.out.println("------");
    }

}

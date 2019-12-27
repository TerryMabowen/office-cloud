package cn.mbw.oc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRunner.class)
public class OfficeCloudAdminApplicationTests {

    @Test
    public void test1() {
        String str = "5487,487974,15,1584,1847,89";
        System.out.println(str);
    }

    @Test
    public void test2() {

    }

}

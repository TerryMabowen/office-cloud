package cn.mbw.ofcd;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Service(version = "1.0.0")
class OfficeCloudBizApplicationTests {

    @Reference(version = "1.0.0")
    private UUID uuid;

    @Test
    void contextLoads() {

    }

}

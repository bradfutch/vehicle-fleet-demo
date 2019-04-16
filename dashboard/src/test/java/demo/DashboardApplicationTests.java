package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.DashboardApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DashboardApplication.class)
@WebAppConfiguration
public class DashboardApplicationTests {

	@Test
	public void contextLoads() {
	}

}

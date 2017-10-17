package ca.purpleowl.examples.spring.boot.docker.data;

import ca.purpleowl.examples.spring.boot.docker.data.annotations.IntegrationTestProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@IntegrationTestProfile
public class DataServiceApplicationTests {
	@Test
	public void contextLoads() {
	}

}

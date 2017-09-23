package ca.purpleowl.examples.spring.boot.docker.data;

import ca.purpleowl.examples.spring.boot.docker.data.annotations.IntegrationTestProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@IntegrationTestProfile
public class DataServiceApplicationTests {
//TODO Figure out a way to fix this... for now, we just have to disable it, because the URL to the DB only exists when we run docker compose...
	@Test
	public void contextLoads() {
	}

}

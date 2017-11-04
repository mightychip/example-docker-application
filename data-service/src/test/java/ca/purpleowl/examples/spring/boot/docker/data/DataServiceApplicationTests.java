package ca.purpleowl.examples.spring.boot.docker.data;

import ca.purpleowl.examples.spring.boot.docker.data.annotations.IntegrationTestProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//TODO The way this is configured requires a specific config file to be present.  Fix this.
//The particular issue is that DataServiceApplicationTests-context.xml or DataServiceApplicationTestsContext.groovy
//are missing.  We need to create those to silence the screams.
@RunWith(SpringRunner.class)
@SpringBootTest
@IntegrationTestProfile
public class DataServiceApplicationTests {
	@Test
	public void contextLoads() {
	}

}

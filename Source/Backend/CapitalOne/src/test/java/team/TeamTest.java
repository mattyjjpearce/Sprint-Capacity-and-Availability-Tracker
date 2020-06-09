package team;

import com.team19.entity.Team;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeamTest {

    Team team;

    @Before
    public void setUp() {
        team = new Team();
    }

    @After
    public void tearDown() {
        team = null;
    }

    @Test
    public void teamIdTest() {
        team.setTeamId(2);
        Assert.assertEquals(Integer.valueOf(2), team.getTeamId());
    }

    @Test
    public void teamNameTest() {
        team.setTeamName("A Team");
        Assert.assertEquals("A Team", team.getTeamName());
    }

    @Test
    public void teamManagerIdTest() {
        team.setTeamManagerId(2);
        Assert.assertEquals(Integer.valueOf(2), team.getTeamManagerId());
    }
}

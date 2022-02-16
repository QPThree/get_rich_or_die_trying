import controller.Game;
import models.Careers;
import models.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {


    Person player = new Person();

    @Test
    public void addSalaryWithEduction() {
        player.setNetWorth(0); //reset net worth to 0
        player.setCareer(Careers.DANGER);
        player.setEducation(true);
        player.addStrength(4);
        player.addSalary(); //give player 5 years of salary
        assertTrue(player.getNetWorth() > (player.getCareer().getSalaryAmount() * 5 * 1.5));
    }

    @Test
    public void educatedPlayerShouldGetMoreFromSalary(){
        player.setNetWorth(0); //reset net worth to 0
        player.setCareer(Careers.DANGER);
        player.setEducation(true);
        player.addStrength(4);
        player.addSalary(); //give player 5 years of salary

        //player 2 is identical except for education
        Person player2 = new Person();
        player2.setNetWorth(0); //zero out net worth
        player2.setCareer(Careers.DANGER);
        player2.setEducation(false);
        player2.addStrength(4);

        assertTrue(player.getNetWorth() > player2.getNetWorth());
    }

    @Test
    public void incomeMultiplierShouldIncreaseByOneTenthForEachPointInStat() {
        //e.g. a strength of 5 should yield 1.5 multiplier.
        // A strength of 2 -> 1.2
        Person playerStrength = new Person();
        playerStrength.setCareer(Careers.DANGER); //danger matches strength

        playerStrength.addStrength(2);
        assertEquals(playerStrength.getStrength(), 2);
       assertTrue(1.2 == (playerStrength.getIncomeMultiplier()));
        //knowledge --> intellect
        Person playerIntellect = new Person();
        playerIntellect.setCareer(Careers.KNOWLEDGE); //danger matches strength

        playerIntellect.addIntellect(4);
        assertEquals(playerIntellect.getIntellect(), 4);
        assertTrue(1.4 == (playerIntellect.getIncomeMultiplier()));
    }

    @Test
    public void getPartner_shouldReturnNull_whenDivorced() {
        player.addPartner(1);
        player.addDivorce();

        assertTrue(null == player.getPartner());
        assertTrue(player.getPartnerStatus().equals("single"));

    }



}

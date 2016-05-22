package register.operations;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Family;
import domain.Player;
import forms.FamilyForm;
import forms.PlayerForm;
import services.FamilyService;
import services.PlayerService;
import utilities.PopulateDatabase;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FamilyServiceTest {

	 	@Autowired
	    private FamilyService familyService;
		 
		@Autowired
	    private PlayerService playerService;

	    @Before
	    public void setUp() {
	        PopulateDatabase.main(null);
	    }

	    @Test
	    public void testRegister() {
	    	Player player;
	        Date date= new Date("12/12/2000");
	        Category category = new Category();
	        category.setCname("Juvenil");
	        PlayerForm playerForm = new PlayerForm();
	        playerForm.setUsername("player1");
	        playerForm.setDate(date);
	        playerForm.setName("player1");
	        playerForm.setSurname("player1");
	        playerForm.setCategory(category);
	        playerForm.setEmail("player1@mail.com");
	        playerForm.setAddress("c/Pedro Leon");
	        playerForm.setPhone("+34666666666");
	        playerForm.setPassword("5d2bbc279b5ce75815849d5e3f0533ec");
	        playerForm.setVerifyPassword("5d2bbc279b5ce75815849d5e3f0533ec");
	        playerForm.setAvailable(true);
	        player = playerService.reconstruct(playerForm);
	        playerService.save(player);
	    		          
	        Family family;
	        FamilyForm familyForm = new FamilyForm();
	        familyForm.setUsername("family1");
	        familyForm.setPassword("f66c54467198093c357c8288fdc30198");
	        familyForm.setVerifyPassword("f66c54467198093c357c8288fdc30198");
	        familyForm.setName("family1");
	        familyForm.setSurname("family1");
	        familyForm.setEmail("family1@mail.com");
	        familyForm.setAddress("c/Pedro Leon");
	        familyForm.setPhone("+34666666666");
	        familyForm.setPlayer(player);
	        familyForm.setAvailable(true);
	        family = familyService.reconstruct(familyForm );
	        familyService.save(family);
	        
	    }
	}

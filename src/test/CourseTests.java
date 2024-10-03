package test;

import org.junit.Test;
import main.*;

public class CourseTests {

	/**
	 * This single test is not really a formal test, but exists simply to
	 * check that your parameterization is (at least half) correct.  If this
	 * file fails to compile, you've missed something.  
	 */
	@Test
	public void courseTests() {
		Undergraduate luke = new Undergraduate("1234", "Skywalker", "Luke");
		Undergraduate wedge = new Undergraduate("5678", "Antilles", "Wedge");
		
		Graduate ben = new Graduate("4411", "Kenobi", "Ben");
		Graduate mace = new Graduate("0021", "Windu", "Mace");
		
		Droid c3po = new Droid("C3PO", "Protocol");
		Droid r2d2 = new Droid("R2D2", "Astromech");
		
		Course c = new Course("CS", "222");
		Section<Graduate> grads = new Section<>("01");
		grads.enroll(ben);
		grads.enroll(mace);
		Section<Undergraduate> ugrads = new Section<>("02");
		ugrads.enroll(luke);
		ugrads.enroll(wedge);
		c.addSection(grads);
		c.addSection(ugrads);
		//this should not be possible:
		// Section<Droid> bots = new Section<>("300");

	}
}

package tr.com.sedatpolat.kalah.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tr.com.sedatpolat.kalah.AppConfig;
import tr.com.sedatpolat.kalah.core.service.KalahService;
import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.constant.Constants;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;

/**
 * 
 * @author sedpol
 *
 */
public class KalahServiceTest {

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	KalahService kalahService = context.getBean(KalahService.class);
	
	KalahBoard kalah = null;
	
	@Before
	public void setup() {
		kalah = new KalahBoard();
	}
	
	@Test
	public void testSowFirstPit() throws Exception {

		if (Constants.NUMBER_OF_PITS - 1 > Constants.MAX_STONES_IN_PIT) { // if there are more than 13 stones, case will change
			kalahService.sow(kalah, 0);
			assertEquals(0, kalah.getPits()[0]);
			assertEquals(Constants.MAX_STONES_IN_PIT + 1, kalah.getPits()[1]);
		
			assertEquals(1, kalah.getFirstKalah());
			assertEquals(0, kalah.getSecondKalah());
		} else {
			throw new Exception(); // if Constants.MAX_STONES_IN_PIT is more than 13 stones, this test case won't work properly. 
		}
	}
	
	@Test
	public void testPickUpFrontStonesIntoKalah() throws Exception {

		preparePickUpFrontStonesCase();

		kalahService.sow(kalah, 0);
		kalahService.pickUpFront(kalah, 0, 1);
		assertEquals(Constants.MAX_STONES_IN_PIT + 1, kalah.getFirstKalah());
	}

	/**
	 * Manipulating pits for pick up front pit case.
	 */
	private void preparePickUpFrontStonesCase() {
		kalah.getPits()[0] = 1;
		kalah.getPits()[1] = 0;
	}
	
	@Test
	public void testWhoseTurn() {
		assertEquals(GameStatus.PLAYER1_TURN, kalahService.whoseTurn(0, 6));
		assertEquals(GameStatus.PLAYER2_TURN, kalahService.whoseTurn(1, 8));
	}
	
	@Test
	public void testIsGameFinishedAndWhoWins() {
		clearFirstPlayerPits();
		assertEquals(true, kalahService.isGameFinished(kalah));
		kalahService.sowRemainedStonesIntoKalah(kalah);
		assertEquals(GameStatus.PLAYER2_WIN, kalahService.whoWon(kalah));
	}
	
	/**
	 * manipulating pits and kalah
	 */
	private void clearFirstPlayerPits() {
		for (int i = 0; i < Constants.FIRST_KALAH; i++) {
			kalah.getPits()[i] = 0;
		}		
		kalah.getPits()[Constants.FIRST_KALAH] = 30;
		kalah.getPits()[Constants.SECOND_KALAH] = 6;
	}

	/***************************************************************************
	 * invalid operation exception cases (may be moved to different test class.)
	 ***************************************************************************/
	
	@Test(expected=InvalidOperationException.class)
	public void startWithZeroStonedPitTest() throws Exception {
		kalah.getPits()[0] = 0; // manipulate number of pit first.
		kalahService.sow(kalah, 0);
	}
	
	@Test(expected=InvalidOperationException.class)
	public void startWithFirstKalahTest() throws Exception {
		kalahService.sow(kalah, Constants.FIRST_KALAH);
	}

	@Test(expected=InvalidOperationException.class)
	public void startWithGreaterIndexTest() throws Exception {
		kalahService.sow(kalah, Constants.NUMBER_OF_PITS);
	}
	
	@Test(expected=InvalidOperationException.class)
	public void startWithSecondKalahTest() throws Exception {
		kalahService.sow(kalah, Constants.SECOND_KALAH);
	}
}

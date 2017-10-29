package tr.com.sedatpolat.kalah.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tr.com.sedatpolat.kalah.AppConfig;
import tr.com.sedatpolat.kalah.core.service.PlayerService;
import tr.com.sedatpolat.kalah.model.bean.Player;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;

/**
 * 
 * @author sedpol
 *
 */
public class PleyerServiceTest {

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	PlayerService playerService = context.getBean(PlayerService.class);
	
	private Player firstPlayer;
	private Player secondPlayer;
	
	@Before
	public void setup() {
		firstPlayer 	= new Player("Player1");
		secondPlayer 	= new Player("Player2");
	}
	
	@Test
	public void testPlayerWins() throws Exception {

		playerService.increasePlayerScor(GameStatus.PLAYER1_WIN, firstPlayer, secondPlayer);
		assertEquals(1, firstPlayer.getScore());
		assertEquals(0, secondPlayer.getScore());

		playerService.increasePlayerScor(GameStatus.PLAYER2_WIN, firstPlayer, secondPlayer);
		playerService.increasePlayerScor(GameStatus.PLAYER2_WIN, firstPlayer, secondPlayer);

		assertEquals(1, firstPlayer.getScore());
		assertEquals(2, secondPlayer.getScore());
		
		playerService.increasePlayerScor(GameStatus.DEUCE, firstPlayer, secondPlayer);
		assertEquals(1, firstPlayer.getScore());
		assertEquals(2, secondPlayer.getScore());
	}
	
}

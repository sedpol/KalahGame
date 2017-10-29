package tr.com.sedatpolat.kalah.core.service;

import org.springframework.stereotype.Service;

import tr.com.sedatpolat.kalah.model.bean.Player;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
/**
 * 
 * @author sedpol
 *
 */
@Service
public class PlayerServiceImpl implements PlayerService {

	public void increasePlayerScor(GameStatus status, Player firstPlayer, Player secondPlayer) {
		if (status == GameStatus.PLAYER1_WIN)
			firstPlayer.increaseScore();
		else if (status == GameStatus.PLAYER2_WIN)
			secondPlayer.increaseScore();
//		else deuce
	}
}

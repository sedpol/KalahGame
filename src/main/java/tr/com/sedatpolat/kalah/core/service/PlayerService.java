package tr.com.sedatpolat.kalah.core.service;

import tr.com.sedatpolat.kalah.model.bean.Player;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;

/**
 * 
 * @author sedpol
 *
 */
public interface PlayerService {
	public void increasePlayerScor(GameStatus status, Player firstPlayer, Player secondPlayer);
}

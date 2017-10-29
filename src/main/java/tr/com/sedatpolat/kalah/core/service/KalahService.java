package tr.com.sedatpolat.kalah.core.service;

import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;

/**
 * 
 * @author sedpol
 *
 */
public interface KalahService {
	public int sow(KalahBoard kalah, int startedPitIndex) throws InvalidOperationException;
	public void pickUpFront(KalahBoard kalah, int startedPitIndex, int lastPitIndex);
	public boolean isGameFinished(KalahBoard kalah);
	public void sowRemainedStonesIntoKalah(KalahBoard kalah);
	public GameStatus whoWon(KalahBoard kalah);	
	public GameStatus whoseTurn(int startedPitIndex, int lastPitIndex);
}

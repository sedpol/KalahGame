package tr.com.sedatpolat.kalah.core.service;

import org.springframework.stereotype.Service;

import tr.com.sedatpolat.kalah.core.service.util.GameUtil;
import tr.com.sedatpolat.kalah.core.util.ValidationUtil;
import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;

/**
 * 
 * @author sedpol
 *
 */
@Service
public class KalahServiceImpl implements KalahService {

	public int sow(KalahBoard kalah, int startedPitIndex) throws InvalidOperationException {
		
		ValidationUtil.validateStartIndex(kalah, startedPitIndex);
		
		int numberOfStones = kalah.getPits()[startedPitIndex];
		
		kalah.getPits()[startedPitIndex] = 0;
		
		int lastPitIndex = startedPitIndex;
		
		return GameUtil.sowStones(kalah, startedPitIndex, numberOfStones, lastPitIndex);
	}

	@Override
	public void pickUpFront(KalahBoard kalah, int startedPitIndex, int lastPitIndex) {
		GameUtil.pickUpFront(kalah, startedPitIndex, lastPitIndex);
	}
	
	@Override
	public boolean isGameFinished(KalahBoard kalah) {
		return GameUtil.isGameFinished(kalah.getPits());
	}
	
	@Override
	public void sowRemainedStonesIntoKalah(KalahBoard kalah) {
		GameUtil.sowRemainedStonesIntoKalah(kalah);
	}
	
	@Override
	public GameStatus whoWon(KalahBoard kalah) {
		return GameUtil.whoWon(kalah);
	}
	
	@Override
	public GameStatus whoseTurn(int startedPitIndex, int lastPitIndex){
		return GameUtil.whoseTurn(startedPitIndex, lastPitIndex);
	}
}

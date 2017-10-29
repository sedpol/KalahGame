package tr.com.sedatpolat.kalah.core.service;

import org.springframework.stereotype.Service;

import tr.com.sedatpolat.kalah.core.util.ValidationUtil;
import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.constant.Constants;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;

/**
 * 
 * @author sedpol
 *
 */
@Service
public class KalahServiceImpl implements KalahService {

	public GameStatus sow(KalahBoard kalah, int startedPitIndex) throws InvalidOperationException {
		
		ValidationUtil.validateStartIndex(kalah, startedPitIndex);
		
		int numberOfStones = kalah.getPits()[startedPitIndex];
		
		kalah.getPits()[startedPitIndex] = 0;
		
		int lastPitIndex = -1;
		
		for (int i = 1; i <= numberOfStones; i++) {
			lastPitIndex = getIndex(startedPitIndex, i + startedPitIndex);
			kalah.getPits()[lastPitIndex] = kalah.getPits()[lastPitIndex] + 1;
		}
		
		pickUpFront(kalah, startedPitIndex, lastPitIndex);
		
		if (isGameFinished(kalah.getPits())) {
			
			sowRemainedStonesIntoKalah(kalah);
			
			return whoWon(kalah);
		}
		
		return whoseTurn(startedPitIndex, lastPitIndex);
	}
	
	private boolean isGameFinished(int [] pits) {
		int firstPlayerStones = 0;
		int secondPlayerStones = 0;
		for (int i = 0; i < pits.length; i++) {
			if (i < Constants.FIRST_KALAH) // 6 is first player's kalah
				firstPlayerStones += pits[i];
			if (i > Constants.FIRST_KALAH && i < Constants.SECOND_KALAH)  // 13 is second player's kalah
				secondPlayerStones += pits[i];
		}
		
		if (firstPlayerStones == 0 || secondPlayerStones == 0) 
			return true;
		
		return false;
	}

	private GameStatus whoWon(KalahBoard kalah) {
		if (kalah.getFirstKalah() == kalah.getSecondKalah())
			return GameStatus.DEUCE;
		else if (kalah.getFirstKalah() > kalah.getSecondKalah())
			return GameStatus.PLAYER1_WIN;
		else if (kalah.getFirstKalah() < kalah.getSecondKalah())
			return GameStatus.PLAYER2_WIN;
		
		throw new RuntimeException("Game is not finished"); // this means game is not finished! 
	}
	
	/**
	 * if pit, which is sown last, contains one stone, pick up and sow it and front stones of pit into kalah.
	 *  "13 - (lastIndex + 1)" gives the index of front pit.
	 *  
	 * @param kalah
	 * @param startedPitIndex
	 * @param lastPitIndex
	 */
	private void pickUpFront(KalahBoard kalah, int startedPitIndex, int lastPitIndex) {
		
		int frontIndex = Constants.SECOND_KALAH - (lastPitIndex + 1);

		if (startedPitIndex < Constants.FIRST_KALAH && lastPitIndex < Constants.FIRST_KALAH) {
			
			if (kalah.getPits()[lastPitIndex] == 1 && kalah.getPits()[frontIndex] != 0) {
				kalah.getPits() [Constants.FIRST_KALAH] += (kalah.getPits() [lastPitIndex] + kalah.getPits() [frontIndex]);
				kalah.getPits() [lastPitIndex] = 0;
				kalah.getPits() [frontIndex] = 0;
			}
			
		} else if (startedPitIndex > Constants.FIRST_KALAH && startedPitIndex < Constants.SECOND_KALAH  &&
				lastPitIndex > Constants.FIRST_KALAH && lastPitIndex < Constants.SECOND_KALAH) {
			if (kalah.getPits()[lastPitIndex] == 1 && kalah.getPits()[frontIndex] != 0) {
				kalah.getPits() [Constants.SECOND_KALAH] += (kalah.getPits() [lastPitIndex] + kalah.getPits() [frontIndex]);
				kalah.getPits() [lastPitIndex] = 0;
				kalah.getPits() [frontIndex] = 0;
			}
		}
	}
	
	private void sowRemainedStonesIntoKalah(KalahBoard kalah) {
		
		for (int i = 0; i < kalah.getPits().length; i++) {
			if (i < Constants.FIRST_KALAH) {// 6 is first player's kalah
				kalah.getPits() [Constants.FIRST_KALAH] += kalah.getPits()[i];

				kalah.getPits()[i] = 0;
			}
			if (i > Constants.FIRST_KALAH && i < Constants.SECOND_KALAH) { // 13 is second player's kalah
				kalah.getPits()[Constants.SECOND_KALAH] += kalah.getPits()[i];
				
				kalah.getPits()[i] = 0;
			}
		}
	}
	
	private GameStatus whoseTurn(int index, int lastIndex) {
		if (index < Constants.FIRST_KALAH) {
			 if(lastIndex == Constants.FIRST_KALAH)
				 return GameStatus.PLAYER1_TURN;
			 else 
				 return GameStatus.PLAYER2_TURN;
		} else {
			if(lastIndex == Constants.SECOND_KALAH)
				 return GameStatus.PLAYER2_TURN;
			else 
				 return GameStatus.PLAYER1_TURN;
		}
	}
	
	private int getIndex(int index, int currentIndex) {
		currentIndex = currentIndex % Constants.NUMBER_OF_PITS;

		// this means "player can not sow stone other player's kalah"
		if ((currentIndex == Constants.FIRST_KALAH && index > Constants.FIRST_KALAH) 
				|| (currentIndex == Constants.SECOND_KALAH && index < Constants.FIRST_KALAH)) 
			++currentIndex;
		
		return currentIndex % Constants.NUMBER_OF_PITS;
	}

}

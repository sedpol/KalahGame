package tr.com.sedatpolat.kalah.model.bean;

import java.io.Serializable;

import tr.com.sedatpolat.kalah.model.constant.Constants;
/**
 * 
 * @author sedpol
 *
 */
public class KalahBoard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int[] pits = new int[14];

	public KalahBoard() {
		pits [0] = Constants.MAX_STONES_IN_PIT;
		pits [1] = Constants.MAX_STONES_IN_PIT;
		pits [2] = Constants.MAX_STONES_IN_PIT;
		pits [3] = Constants.MAX_STONES_IN_PIT;
		pits [4] = Constants.MAX_STONES_IN_PIT;
		pits [5] = Constants.MAX_STONES_IN_PIT;
		pits [6] = 0; // index 6 first kalah
		pits [7] = Constants.MAX_STONES_IN_PIT;
		pits [8] = Constants.MAX_STONES_IN_PIT;
		pits [9] = Constants.MAX_STONES_IN_PIT;
		pits [10] = Constants.MAX_STONES_IN_PIT;
		pits [11] = Constants.MAX_STONES_IN_PIT;
		pits [12] = Constants.MAX_STONES_IN_PIT;
		pits [13] = 0; // index 13 second kalah
	}

	public int[] getPits() {
		return pits;
	}
	
	public int getFirstKalah() {
		return getPits() [6];
	}
	
	public int getSecondKalah() {
		return getPits() [13];
	}
}

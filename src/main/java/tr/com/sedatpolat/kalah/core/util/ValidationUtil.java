package tr.com.sedatpolat.kalah.core.util;

import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.constant.Constants;
import tr.com.sedatpolat.kalah.model.constant.ExceptionMessages;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;

/**
 * 
 * @author sedpol
 *
 */
public class ValidationUtil {

	public static void validateStartIndex(KalahBoard kalah, int startedPitIndex) throws InvalidOperationException {

		if (startedPitIndex >= Constants.NUMBER_OF_PITS)
			throw new InvalidOperationException(ExceptionMessages.INVALID_START_PIT_INDEX);
		
		if (startedPitIndex == Constants.FIRST_KALAH || startedPitIndex == Constants.SECOND_KALAH)
			throw new InvalidOperationException(ExceptionMessages.CANNOT_START_WITH_KALAH);
		
		if (kalah.getPits()[startedPitIndex] == 0)
			throw new InvalidOperationException(ExceptionMessages.START_WITH_ZERO_STONED_PIT);
		
	}
}

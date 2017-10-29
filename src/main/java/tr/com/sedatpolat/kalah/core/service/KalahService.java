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
	public GameStatus sow(KalahBoard kalah, int startedPitIndex) throws InvalidOperationException;
}

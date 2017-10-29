package tr.com.sedatpolat.kalah.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tr.com.sedatpolat.kalah.AppConfig;
import tr.com.sedatpolat.kalah.core.service.KalahService;
import tr.com.sedatpolat.kalah.core.service.PlayerService;
import tr.com.sedatpolat.kalah.model.bean.KalahBoard;
import tr.com.sedatpolat.kalah.model.bean.Player;
import tr.com.sedatpolat.kalah.model.constant.GameStatus;
import tr.com.sedatpolat.kalah.model.exception.InvalidOperationException;
/**
 * 
 * @author sedpol
 *
 */
@ManagedBean
@SessionScoped
//@Import(AppConfig.class)
public class GameView implements Serializable {

	private static final long serialVersionUID = 1L;
	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	private KalahService kalahService = context.getBean(KalahService.class);
	
	private PlayerService playerService = context.getBean(PlayerService.class);
	
	private Player firstPlayer;
	private Player secondPlayer;
	
	private KalahBoard kalah;
	private boolean disableFirstPlayer;
	private boolean disableSecondPlayer;
	
	@PostConstruct
	public void loadGame() {
		init();
	}
	
	public void move(Integer index) {
		try {
			GameStatus status = kalahService.sow(kalah, index);
			
			disableSecondPlayer = status == GameStatus.PLAYER1_TURN;
			disableFirstPlayer = status == GameStatus.PLAYER2_TURN;
			
			if (isGameFinished(status)) {
				
				disableFirstPlayer = disableSecondPlayer = true;
				
				playerService.increasePlayerScor(status, firstPlayer, secondPlayer);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(status), null));
			}
		} catch (InvalidOperationException ioe) { 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ioe.getErrorCode(), ioe.getMessage()));
		} catch (Exception e) {
			e.printStackTrace(); // Logger should be used
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error!", null));
		}
	}
	
	public void newGame() {
		init();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New game is started!", null));
	}
	
	public void resetGame() {
		reset();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Game is started!", null));
	}

	private void init() {
		setFirstPlayer(new Player("player1"));
		setSecondPlayer(new Player("player2"));
		
		reset();
	}
	
	private void reset() {
		kalah = new KalahBoard();
		disableFirstPlayer = false;
		disableSecondPlayer = true;
	}
	
	private boolean isGameFinished (GameStatus status) {
		return status == GameStatus.DEUCE ||status == GameStatus.PLAYER1_WIN || status == GameStatus.PLAYER2_WIN;
	}

	private String getStatusMessage(GameStatus status) {
		String message;
		if (status ==  GameStatus.DEUCE)
			 message = "Deuce!";
		else if (status ==  GameStatus.PLAYER1_WIN)
			message = "First player won!";
		else
			message = "Second player won!";
		return message;
	}
	
	public KalahBoard getKalah() {
		return kalah;
	}

	public void setKalah(KalahBoard kalah) {
		this.kalah = kalah;
	}

	public boolean isDisableFirstPlayer() {
		return disableFirstPlayer;
	}

	public void setDisableFirstPlayer(boolean disableFirstPlayer) {
		this.disableFirstPlayer = disableFirstPlayer;
	}

	public boolean isDisableSecondPlayer() {
		return disableSecondPlayer;
	}

	public void setDisableSecondPlayer(boolean disableSecondPlayer) {
		this.disableSecondPlayer = disableSecondPlayer;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
}

package tr.com.sedatpolat.kalah.model.bean;

import java.io.Serializable;

/**
 * 
 * @author sedpol
 *
 */
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int score = 0;

	public Player(String name) {
		super();
		this.name = name;
	}

	public void increaseScore() {
		this.score++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScor(int score) {
		this.score = score;
	}
}

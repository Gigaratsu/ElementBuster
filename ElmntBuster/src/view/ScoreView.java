package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel scoreText;
	private int score;
	
	public ScoreView() {
		score = 0;
		scoreText = new JLabel("Score: " + score);

		add(scoreText, BorderLayout.WEST);
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int points){
		score += points;
		scoreText.setText("Score: " + score);
	}

	public JLabel getScoreText() {
		return scoreText;
	}

	public void setScoreText(JLabel scoreText) {
		this.scoreText = scoreText;
	}

}

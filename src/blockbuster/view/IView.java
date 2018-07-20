/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

/**
 *
 * @author Andrea
 */
public interface IView {
    	public void openStartWindow();

	public void closeStartWindow();

	public void openNewGameWindow(); // Menu incorporato applicazione (repaint)
	public void closeNewGameWindow();
        public void openOptionWindow();
        public void openScoreBoardWindow();
        public void closeScoreBoardWindow();
        
        public void openHowToPlayWindow();
	public void openMainGUI();
	public void updateLineLabel();
	public void updateScoreLabel(int score);
	public void updateLineLabel(int lines);
        public void updatePlayerName(String name);
	public void gameOverEvent();
        public void nextLevelEvent();
        public void nextLevelAnimation();
}

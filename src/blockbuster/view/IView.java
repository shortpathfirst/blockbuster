package blockbuster.view;


public interface IView {
    	public void openStartWindow();
	public void closeStartWindow();
        public void openOptionWindow();
        public void openScoreBoardWindow();
        public void closeScoreBoardWindow();
        public void openHowToPlayWindow();
	public void openMainGUI();
        public void closeMainGUI();
        
	public void updateLineLabel();
	public void updateScoreLabel(int score);
	public void updateLineLabel(int lines);
        public void updatePlayerName(String name);
        public void updateBoard();
        
	public void gameOverEvent();
        
        public void nextLevelEvent();
        public void nextLevelAnimation();
}

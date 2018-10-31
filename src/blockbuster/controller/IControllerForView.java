package blockbuster.controller;

public interface IControllerForView {
    
    public void openStartWindow();
    public void closeStartWindow();
    
    public void openHowToPlayWindow();
    public void openMainGUI();
    public void closeMainGUI();
    public void openOptionWindow();
    
    public void openScoreBoardWindow(); 
    public void closeScoreBoardWindow();

    public void initGame();
    
    public int getNumColumnsOfBoard(); 
    public int getNumRowsOfBoard();

    public boolean isLevelMode();
    
    public void setLevelMode(boolean state);
    public void setPlayerName(String playerName);
    
    public String getPlayerName();
    public int getScore();
    public int getLevel();
    public int getLineLeft();
    public int getIncrementedScore();
    public int getIncrementDelay();
    public int getIncrementBlock(int j);
    public int getBoardBlock(int i,int j);
    
    public void nextIncrementLine();
    public boolean isGameOver();
    public boolean isLevelCompleted();

}

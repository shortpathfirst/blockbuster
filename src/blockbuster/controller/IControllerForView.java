/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.controller;

/**
 *
 * @author Andrea
 */
public interface IControllerForView {
    
    public void openStartWindow();
    
    public void closeStartWindow();
    
//    public void openNewGameWindow();
//    
//    public void closeNewGameWindow();
    public void openHowToPlayWindow();
    public void openMainGUI();
    public void openOptionWindow();
    
    
    public void openScoreBoardWindow(); 

    public void closeScoreBoardWindow(); 
    
    public void loadPreviouslySavedGame(String configFile);
    
    public int getNumColumnsOfBoard(); 

    public int getNumRowsOfBoard();

    public String getPlayerName();
    public boolean isLevelMode();
    public void setLevelMode(boolean state);

    public void setPlayerName(String playerName);

    public int getScore();
   
    public void initGame();

    
    public int getIncrementDelay();
    public void remove(int i,int j,int blockType);
    public void nextIncrementLine();
    public int getLineLeft();
    public boolean isGameOver();
    public int getLevel();
    public boolean isLevelCompleted();
    public int getIncrementedScore();
//    public int[][] getboardArray();
    public int getBoardBlock(int i,int j);
}

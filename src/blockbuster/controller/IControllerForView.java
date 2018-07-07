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
    
    public void openNewGameWindow();
    
    public void closeNewGameWindow();
    
    public void openMainGUI();
    
    
    
//    public void openScoreBoardWindow(); //ADD
//
//    public void closeScoreBoardWindow(); //ADD
    
    public void loadPreviouslySavedGame(String configFile);
    
    public int getNumColumnsOfBoard(); // Do i lost?

    public int getNumRowsOfBoard();

    public String getPlayerName();

    public void setPlayerName(String playerName);

    public String getScore();

    public void incrementScore(int increment);
   
    public void initGame();
    ///////////////////
    public boolean isEmptyCell(int i, int j); // To compact() //da rimuovere insieme al model forse
    public int getBlock(int i,int j);
    
    //TO DO
    //Click -> If adiacent() remove() compact()
    public void remove(int i,int j,int blockType);
    public void nextIncrementLine();
}

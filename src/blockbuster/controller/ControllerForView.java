/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.controller;

import blockbuster.view.View;
import blockbuster.model.Model;                                                   //CONTROLLER FOR MODEL
import blockbuster.utils.Config;
import blockbuster.utils.Score;
import blockbuster.utils.SoundPlayer;
/**
 *
 * @author Andrea
 */
public class ControllerForView implements IControllerForView{
    
    	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static ControllerForView instance = null;

	private ControllerForView() {
		//to-do
	}

        //---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void openStartWindow() {
		View.getInstance().openStartWindow();
	}
        public void openOptionWindow() {
		View.getInstance().openOptionWindow();
	}
	public void closeStartWindow() {
		View.getInstance().closeStartWindow();
	}
	public void openNewGameWindow() {
            if(Model.getInstance().getScore()==0)
		View.getInstance().openNewGameWindow();
            else
                View.getInstance().openMainGUI();                               //set new instance
	}
	public void loadPreviouslySavedGame(String file) {
		//TO-DO
	}
         public void openScoreBoardWindow(){
             View.getInstance().openScoreBoardWindow();
         }

        public void closeScoreBoardWindow(){
            View.getInstance().closeScoreBoardWindow();
        }

	public void closeNewGameWindow() {
		View.getInstance().closeNewGameWindow();
	}
        public void openHowToPlayWindow(){
            View.getInstance().openHowToPlayWindow();
        }
	public void openMainGUI() {
		closeNewGameWindow();
		View.getInstance().openMainGUI();
	}

	public int getNumColumnsOfBoard() {
		return Model.getInstance().getNumColumnsOfBoard();
	}

	public int getNumRowsOfBoard() {
		return Model.getInstance().getNumRowsOfBoard();
	}

	public String getPlayerName() {
		return Model.getInstance().getPlayerName();
	}
        public int getLevel() {
		return Model.getInstance().getLevel();
	}
        public boolean isLevelCompleted() {
		return Model.getInstance().isLevelCompleted(); // find usage e fai qua la condizione
	}
	public void setPlayerName(String playerName) {
		Model.getInstance().setPlayerName(playerName);
                Config.getInstance().setPlayerName(playerName);
//                     View.getInstance().updatePlayerName(playerName);
	}

	public String getScore() {
		return String.valueOf(Model.getInstance().getScore());
	}
        public int getIncrementDelay(){
            return Model.getInstance().getIncrementlDelay();
        }
        public boolean isLevelMode(){
            return Model.getInstance().isLevelMode();
        }
        public void setLevelMode(boolean state){
            Model.getInstance().setLevelMode(state);
        }
	public void initGame() {
		Model.getInstance().initGame();
	}

	public boolean isEmptyCell(int i, int j) {
		return Model.getInstance().isEmptyCell(i, j);
	}

        public int getLineLeft(){
            return Model.getInstance().getLineLeft();
        }
        
        public void nextIncrementLine() {
            if(Model.getInstance().isIncrementLineFull()){
                    if (isGameOver()){
                        Model.getInstance().initGame();
                        View.getInstance().gameOverEvent();
                        Score.getInstance().setPlayerScore(Model.getInstance().getPlayerName(), Model.getInstance().getScore());
                    }else{
                        Model.getInstance().pushIncrement();
                        Model.getInstance().updateLine();
                        View.getInstance().updateLineLabel(getLineLeft());
                    }
            }
            else if(Model.getInstance().isLevelCompleted()) {//next level
                View.getInstance().nextLevelEvent();
                View.getInstance().nextLevelAnimation();                        
            }else 
                    Model.getInstance().incrementLine();
	} // end method next()
        public boolean isGameOver(){
            return !Model.getInstance().islastRowEmpty();
        }
        //---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------

        public void remove(int i,int j,int blockType) { 
                if(blockType != 0 && !isGameOver()){
                    if(blockType==6){}
                    if(blockType==7){
                            Model.getInstance().removeColor(i,j);
                            SoundPlayer s= new SoundPlayer("bubble");
                            s.play();
                        }
                    if(blockType==8)
                        Model.getInstance().paintSquare(i, j);
                    if(blockType==9)
                        Model.getInstance().removeSquare(i, j);
                    else
                        Model.getInstance().setVisitedBlocks(i,j,blockType);
                    
                    
                    if(Model.getInstance().getVisitedNum() >=3){
                        Model.getInstance().removeVisitedBlocks();
                        Model.getInstance().addScore();
                        View.getInstance().updateScoreLabel(Model.getInstance().getScore()); 
                        SoundPlayer s= new SoundPlayer("bubble");
                        s.play();
                    }
                    Model.getInstance().resetVisited();
                }

	}

        //---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IControllerForView getInstance() {
		if (instance == null)
			instance = new ControllerForView();
		return instance;
	}

}// end class

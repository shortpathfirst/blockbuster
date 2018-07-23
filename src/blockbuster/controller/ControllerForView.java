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

	private ControllerForView() {}
        
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
         public void openScoreBoardWindow(){
                View.getInstance().openScoreBoardWindow();
        }
        public void closeScoreBoardWindow(){
                View.getInstance().closeScoreBoardWindow();
        }
        public void openHowToPlayWindow(){
                View.getInstance().openHowToPlayWindow();
        }
	public void openMainGUI() {
                View.getInstance().openMainGUI();
	}
        public void closeMainGUI(){
                View.getInstance().closeMainGUI();
        }
        public void initGame() {
		Model.getInstance().initGame();
	}
        
	public int getNumColumnsOfBoard() {
		return Model.getInstance().getNumColumnsOfBoard();
	}
        
	public int getNumRowsOfBoard() {
		return Model.getInstance().getNumRowsOfBoard();
	}
        
        public boolean isLevelMode(){
            return Model.getInstance().isLevelMode();
        }
        
        public void setLevelMode(boolean state){
            Model.getInstance().setLevelMode(state);
        }
        
        public void setPlayerName(String playerName) {
		Model.getInstance().setPlayerName(playerName);
	}
        
	public String getPlayerName() {
		return Model.getInstance().getPlayerName();
	}
        
        public int getLevel() {
		return Model.getInstance().getLevel();
	}
        
        public int getScore(){
		return Model.getInstance().getScore();
	}
        
        public int getLineLeft(){
            return Model.getInstance().getLineLeft();
        }
        
        public int getIncrementedScore() {
		return Model.getInstance().getIncrementedScore();
	}
        
        public int getIncrementDelay(){
            return Model.getInstance().getIncrementlDelay();
        }

        public int getIncrementBlock(int j){
            return Model.getInstance().getIncrementBlock(j);
        }
        
        public int getBoardBlock(int i,int j){
            return Model.getInstance().getBoardBlock(i, j);
        }
        
        public boolean isLevelCompleted() {
		return Model.getInstance().isLevelCompleted();
	}
	public boolean isGameOver(){
            return !Model.getInstance().islastRowEmpty();
        }
        
        public void nextIncrementLine() {
            if(Model.getInstance().isIncrementLineFull()){
                    if (isGameOver()){
                        View.getInstance().gameOverEvent();
                        Score.getInstance().setPlayerScore(Model.getInstance().getPlayerName(), Model.getInstance().getScore(),Model.getInstance().isLevelMode());
                        Config.getInstance().removeSavedGame();
                        Model.getInstance().initGame();
                    }else{
                        Model.getInstance().pushIncrement();
                        Model.getInstance().updateLine();
                        View.getInstance().updateLineLabel(getLineLeft());
                        View.getInstance().updateScoreLabel(getScore()); 
                    }
            }
            else if(Model.getInstance().isLevelCompleted()) {
                View.getInstance().nextLevelEvent();
                if(Config.getInstance().isEndLevelAnimationOn())
                     View.getInstance().nextLevelAnimation();                        
            }else 
                    Model.getInstance().addIncrementBlock();
	} 

        public int remove(int i,int j,int blockType) { 
                if(blockType==6){
                    new SoundPlayer("anvil").play();
                }
                if(blockType==7){
                    Model.getInstance().removeColor(i,j);
                    new SoundPlayer("bubble").play();
                }
                if(blockType==8)
                    Model.getInstance().paintSquare(i, j);
                if(blockType==9)
                    Model.getInstance().setVisitedSquare(i, j);
                else
                    Model.getInstance().setVisitedBlocks(i,j,blockType);


                if(Model.getInstance().getVisitedBlockNumber() >=3){
                    Model.getInstance().removeVisitedBlocks();
                    Model.getInstance().addScore();
                    View.getInstance().updateScoreLabel(getScore()); 
                    if(Config.getInstance().isBlockEffectOn())
                        new SoundPlayer("bubble").play();
                }
                int score=Model.getInstance().getIncrementedScore();
                Model.getInstance().resetVisited();
                return score;
                  
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

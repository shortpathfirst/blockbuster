package blockbuster.controller;

import blockbuster.view.View;
import blockbuster.model.Model;
import blockbuster.utils.Config;
import blockbuster.utils.Score;

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

        //---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IControllerForView getInstance() {
		if (instance == null)
			instance = new ControllerForView();
		return instance;
	}

}// end class

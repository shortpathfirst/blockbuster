package blockbuster.controller;

import blockbuster.model.Model;
import blockbuster.utils.Config;
import blockbuster.utils.SoundPlayer;
import blockbuster.view.View;

public class ControllerForModel implements IControllerForModel {
        //---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static ControllerForModel instance = null;

	private ControllerForModel() {
		//to-do
	}
        //---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
        public void nextLevel(){
            Model.getInstance().nextLevel();
            ControllerForView.getInstance().nextIncrementLine();
        }
        public void loadGame(){
            Model.getInstance().loadGame();
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


                if(Model.getInstance().getVisitedBlockNumber() >=3 && blockType != 6){
                    Model.getInstance().removeVisitedBlocks();
                    Model.getInstance().addScore();
                    View.getInstance().updateScoreLabel(ControllerForView.getInstance().getScore()); 
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
	public static IControllerForModel getInstance() {
		if (instance == null)
			instance = new ControllerForModel();
		return instance;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.controller;

import blockbuster.view.View;
import blockbuster.model.Model;
/**
 *
 * @author Andrea
 */
public class ControllerForView implements IControllerForView{
    
    	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	//private final static int SCORE_FACTOR = 10;
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

	public void closeStartWindow() {
		View.getInstance().closeStartWindow();
	}

	public void openNewGameWindow() {
		View.getInstance().openNewGameWindow();
	}

	public void loadPreviouslySavedGame(String file) {
		//TO-DO
	}

	public void closeNewGameWindow() {
		View.getInstance().closeNewGameWindow();
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

	public String getPlayerName() {return null;
//		return Model.getInstance().getPlayerName();
	}

	public void setPlayerName(String playerName) {
//		Model.getInstance().setPlayerName(playerName);
	}

	public String getScore() {return null;
//		return String.valueOf(Model.getInstance().getScore());
	}

	public void incrementScore(int increment) {
//		Model.getInstance().incrementScore(increment);
	}

	public void initGame() {
		Model.getInstance().initGame();
	}

	public boolean isEmptyCell(int i, int j) {
		return Model.getInstance().isEmptyCell(i, j);
	}
        public int getBlock(int i, int j) {
		return 0;
	}
        
        public void nextIncrementLine() {
//		if (isIncrementLineFull())
			Model.getInstance().incrementLine();
//		else {
//			Model.getInstance().pushIncrement();
//			this.removeFullRows();
//			if (this.isGameOver())
//				View.getInstance().gameOverDialog();
//			else {
//				Model.getInstance().nextFallingAndPreviewPieces();
//				this.resumeIndexes();
//			}
//		}
	} // end method next()
        
        //---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
        public void remove(int i,int j,int blockType) { 
                //add scoreincrement(vedi removefullrow()
                //fist setvisited then remove visitedblocks
                if(blockType != 0){
                Model.getInstance().setVisitedBlocks(i,j,blockType); //(start position , blocktype)
                Model.getInstance().removeVisitedBlocks();
                Model.getInstance().TrimRows();
                }
//		Model.getInstance().removeColor(blockType);
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

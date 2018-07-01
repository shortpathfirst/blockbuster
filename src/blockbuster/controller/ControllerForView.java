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
        //---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
        private boolean isRowFull(int iIndexOfRow) {  // FULL!! ROW
            boolean isRowFull = true;
            for (int j = 0; (j < Model.getInstance().getNumColumnsOfBoard()) && isRowFull; j++)
                    isRowFull = !(Model.getInstance().isEmptyCell(iIndexOfRow, j));
            return isRowFull;
        }
        private int topMostFullRow() {  // FULL!! ROW
            int topMostFullRow = -1;
            for (int i = 0; i < Model.getInstance().getNumRowsOfBoard(); i++)
                    if (this.isRowFull(i))
                            topMostFullRow = i;
            return topMostFullRow;
	}
        private void removeFullRows() {             //remove visible=true
		int i = Model.getInstance().getNumRowsOfBoard();
		//int scoreIncrement = -1;
		while ((i = this.topMostFullRow()) >= 0) { //tutte le righe piene
			Model.getInstance().removeVisitedRows(i);
			//coreIncrement = SCORE_FACTOR * (Model.getInstance().getNumRowsOfBoard() - i);
			//Model.getInstance().incrementScore(scoreIncrement);
			//View.getInstance().updateScoreLabel(Model.getInstance().getScore());
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

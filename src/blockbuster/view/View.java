/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.model.Model;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */
public class View implements IView{
        //---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static View instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	protected StartWindow startWindow = null;
	protected NewGameWindow newGameWindow = null;
	protected MainGUI mainGUI = null;
        protected Option optionWindow = null;

	private View() {
		//TO-DO
	}

	//---------------------------------------------------------------
	// INSTANCE METHODS
	//---------------------------------------------------------------
	public void openStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow == null)
					startWindow = new StartWindow();
				startWindow.setVisible(true);
			}
		});
	}

	public void closeStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow != null)
					startWindow.setVisible(false);
			}
		});
	}

	public void openNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow == null)
					newGameWindow = new NewGameWindow();
				newGameWindow.setVisible(true);
			}
		});
	}

	public void closeNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow != null)
					newGameWindow.setVisible(false);
			}
		});
	}

	public void openMainGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (mainGUI == null)
					mainGUI = new MainGUI();
				mainGUI.setVisible(true);
			}
		});
	}
	public void openOptionWindow(){
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (optionWindow == null)
					optionWindow = new Option();
				optionWindow.setVisible(true);
			}
		});
        }
	public void updateScoreLabel(int score) {
		this.mainGUI.updateScoreLabel(score);
	}
	public void updateLineLabel(int lines){
            this.mainGUI.updateLineLabel(lines);
        }
	public void gameOverEvent() {
		this.mainGUI.setNewGame();
                JOptionPane.showMessageDialog(this.mainGUI, "Press Ok to start a new game", "Game Over!", 0);
                updateScoreLabel(0);
	}
        public void nextLevelDialog(){
            this.mainGUI.createEndLevelButton();
            this.mainGUI.setNewGame();
        }
        public void nextLevelAnimation(){
//            this.mainGUI.endLevelAnimation();  
        }
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IView getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}
}

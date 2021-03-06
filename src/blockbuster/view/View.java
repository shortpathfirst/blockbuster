package blockbuster.view;

import blockbuster.controller.ControllerForView;
import blockbuster.utils.SoundPlayer;
import javax.swing.JOptionPane;


public class View implements IView{
        //---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static View instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	protected StartWindow startWindow = null;
	protected MainGUI mainGUI = null;
        protected OptionWindow optionWindow = null;
        protected HowToPlayWindow HowToPlayWindow= null;
        protected ScoreBoardWindow scoreBoardWindow = null;

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
	public void openMainGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (mainGUI == null)
					mainGUI = new MainGUI();
				mainGUI.setVisible(true);
                                updateLineLabel();
                                updateScoreLabel(0);
                                updateLineLabel(ControllerForView.getInstance().getLineLeft());
			}
		});
	}
        public void closeMainGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (mainGUI != null)
					mainGUI.setVisible(false);
			}
		});
	}
        public void openHowToPlayWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (HowToPlayWindow == null)
					HowToPlayWindow = new HowToPlayWindow();
				HowToPlayWindow.setVisible(true);
			}
		});
	}
	public void openOptionWindow(){
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (optionWindow == null)
					optionWindow = new OptionWindow();
				optionWindow.setVisible(true);
			}
		});
        }
        public void openScoreBoardWindow(){
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (scoreBoardWindow == null)
					scoreBoardWindow  = new ScoreBoardWindow();
				scoreBoardWindow.setVisible(true);
			}
		});
        }
        public void closeScoreBoardWindow(){
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                            if (scoreBoardWindow != null)
                                    scoreBoardWindow.setVisible(false);
			}
		});
        }
	public void updateScoreLabel(int score) {
		this.mainGUI.updateScoreLabel(score);
	}
	public void updateLineLabel(int lines){
            this.mainGUI.updateLineLabel(lines);
        }
        public void updateLineLabel(){
            this.mainGUI.updateLineLabel();
        }
        public void updatePlayerName(String name){
            this.mainGUI.updatePlayerName(name);
        }
        public void updateBoard(){
            this.mainGUI.updateBoard();
        }

	public void gameOverEvent() {
                this.mainGUI.stopGame();
                new SoundPlayer("gameover").play();
                this.mainGUI.restart();
                JOptionPane.showMessageDialog(this.mainGUI, "Press Ok to start a new game", "Game Over!", 0);
                updateScoreLabel(0);
	}
        public void nextLevelEvent(){                                        
            this.mainGUI.createEndLevelButton();
            this.mainGUI.stopGame();
        }
        public void nextLevelAnimation(){
            this.mainGUI.endLevelAnimation();  
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

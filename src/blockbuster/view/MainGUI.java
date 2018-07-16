/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForModel;
import blockbuster.controller.ControllerForView;
import blockbuster.model.Model;
import blockbuster.utils.Config;
import blockbuster.utils.SoundPlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Andrea
 */
public class MainGUI extends JFrame  implements ComponentListener,ActionListener{
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
	private final static int WINDOW_PREFERRED_WIDTH = 550;
	private final static int WINDOW_PREFERRED_HEIGHT = 600;
	private final static String START_BUTTON_LABEL = "Start";
	private final static String PAUSE_BUTTON_LABEL = "Pause";
        
    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
	private BoardPanel boardPanel;
        private IncrementPanel incrementPanel;
        private JPanel optionPanel;
        private JPanel gamePanel;
        private boolean isGameStarted;// a game can start only once at the beginning
        private boolean isGameRunning;// a started game can be running or in pause
        private Timer timer; 
        private JButton menuBut;
	private JButton startPauseBut;
        private JButton endLevelBut;
	private JLabel playerNameLab;
	private JLabel playerNamePrefixLab;
	private JLabel playerScoreLab;
	private JLabel playerScorePrefixLab;
	private JLabel linesLeftLab;
        private JLabel linesLeftPrefixLab;
        private SoundPlayer bcMusic;
        
        public MainGUI() {
		super("BlockBuster");
		this.createGUI();
                this.timer = new Timer(Model.getInstance().getLevelDelay(), this);
		this.isGameStarted = false;
		this.isGameRunning = false;
	}
        private void ReturnToStartWindows(){                                    //load saved game     
               this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ControllerForView.getInstance().openStartWindow();
                        stopGame();
//                        StopMusic();
                    }
                  });
        }
        private void createGUI() {
		this.addComponentListener(this);
                ReturnToStartWindows();
              //this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WINDOW_PREFERRED_WIDTH, WINDOW_PREFERRED_HEIGHT));
                
                this.setGamePanel();
		this.setOptionPanel();                             
                
		Container contPane = this.getContentPane();
		contPane.setLayout(new BorderLayout());
		contPane.add(this.optionPanel);
                contPane.add(this.gamePanel, BorderLayout.EAST);
                this.bcMusic=new SoundPlayer("bcMusic");
                
                Image img = BlockStyle.getInstance().getButtonIcon();
                this.setIconImage(img);    
                
		this.pack();    
	}
        private void setGamePanel(){
                this.boardPanel = new BoardPanel();
                this.incrementPanel = new IncrementPanel();
                this.gamePanel=new JPanel();
                this.incrementPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.boardPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//                this.gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
                this.gamePanel.setLayout(new BorderLayout());
                this.gamePanel.add(boardPanel,BorderLayout.CENTER);
                this.gamePanel.add(incrementPanel,BorderLayout.PAGE_END);
        }
        private void setOptionPanel() {
		this.optionPanel = new JPanel();
		this.optionPanel.setBackground(Color.decode("#fdb94d"));
		this.optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		this.optionPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); 

		this.playerNamePrefixLab = new JLabel("Player Name");
		this.playerNameLab = new JLabel(ControllerForView.getInstance().getPlayerName());    //Config
		this.playerScorePrefixLab = new JLabel("Score");
		this.playerScoreLab = new JLabel(ControllerForView.getInstance().getScore());       //Config
                this.linesLeftPrefixLab= new JLabel("Line Left");
                this.linesLeftLab = new JLabel(""+ControllerForView.getInstance().getLineLeft());      //Config
		this.startPauseBut = new JButton("Start");
		this.startPauseBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPauseEvent();
			}
		});
		this.startPauseBut.setMnemonic(KeyEvent.VK_P);
		
		this.menuBut = new JButton("Menu");
		this.menuBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              menuEvent();
			}
		});		

		this.playerNamePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerNameLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScorePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScoreLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.linesLeftPrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.linesLeftLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.startPauseBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.menuBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		this.optionPanel.add(playerNamePrefixLab);
		this.optionPanel.add(this.playerNameLab);
		this.optionPanel.add(Box.createVerticalGlue());
		this.optionPanel.add(playerScorePrefixLab);
		this.optionPanel.add(this.playerScoreLab);
		this.optionPanel.add(Box.createVerticalGlue());
                this.optionPanel.add(this.linesLeftPrefixLab);
                this.optionPanel.add(this.linesLeftLab);
                this.optionPanel.add(Box.createVerticalGlue());
		this.optionPanel.add(this.startPauseBut);
		this.optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.optionPanel.add(this.menuBut);
	}
        private void menuEvent(){
            this.dispose();
            ControllerForView.getInstance().openStartWindow();
            this.bcMusic.pause();
            if(!ControllerForView.getInstance().isGameOver())
                Config.getInstance().saveGame();
            
        }
        private void startPauseEvent(){//Save game when pause
                if (!this.isGameStarted) {
			this.isGameStarted = true;
			this.isGameRunning = true;
			ControllerForView.getInstance().initGame();
			this.boardPanel.requestFocusInWindow();
                        this.boardPanel.setEnabled(true);
			this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
			this.menuBut.setEnabled(false);
                        this.timer.start();
                        this.bcMusic.play();
		}else if (!this.isGameRunning) {
			this.isGameRunning = true;
			this.boardPanel.requestFocusInWindow();
			this.timer.start();
			this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
			this.menuBut.setEnabled(false);
                        this.boardPanel.setEnabled(true);
                        this.boardPanel.setVisible(true);
                        this.incrementPanel.setVisible(true);
                        this.bcMusic.play();
                }
		else {
			stopGame();
                }
                
        }
        
	//-------------------------------------------------------------------------
	// To implement the interface java.awt.event.ComponentListener
	//-------------------------------------------------------------------------
	public void componentHidden(ComponentEvent e) {
		//do-nothing
	}

	public void componentMoved(ComponentEvent e) {
		//do-nothing
	}

	public void componentResized(ComponentEvent e) {
		this.boardPanel.setGridUnit();
                this.incrementPanel.setGridUnit();
	}

	public void componentShown(ComponentEvent e) {
		//do-nothing
	}

        /* Method to implement the interface java.awt.ActionListener. */
	public void actionPerformed(ActionEvent e) {
		ControllerForView.getInstance().nextIncrementLine();
                this.boardPanel.repaint();
		this.incrementPanel.repaint();
	}
        public void updateScoreLabel(int score) {
//            if(cofig == true) add label with increment
            this.playerScoreLab.setText(String.valueOf(score));
	}
         public void updateLineLabel(int lines) {
		this.linesLeftLab.setText(String.valueOf(lines));
	}
        public void createEndLevelButton(){
            this.endLevelBut = new JButton();
            this.endLevelBut.setText("LEVEL "+Model.getInstance().getLevel()+" COMPLETED");
            this.endLevelBut.setFont(new Font("Arial", Font.BOLD, 15));
            this.endLevelBut.setHorizontalTextPosition(JButton.CENTER);
            this.endLevelBut.setVerticalTextPosition(JButton.BOTTOM);
            this.endLevelBut.setBackground(Color.WHITE);
            Image img = BlockStyle.getInstance().getEndLevelImage();
            this.endLevelBut.setIcon(new ImageIcon(img));

            this.endLevelBut.setPreferredSize(new Dimension(220,150));
            this.endLevelBut.setMaximumSize(new Dimension(220,150));
            this.endLevelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                handleEndLevelButton();
			}
		});

            this.boardPanel.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20)); 
            this.startPauseBut.setEnabled(false);
            this.boardPanel.add(endLevelBut);
        }
        public void handleEndLevelButton(){
            this.boardPanel.remove(this.endLevelBut);
            this.boardPanel.setEnabled(true);
            
            this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
            this.startPauseBut.setEnabled(true);
            this.startPauseBut.setVisible(true);
            
            this.menuBut.setEnabled(false);
            
            ControllerForModel.getInstance().nextLevel();                         //controller for model  
            this.isGameStarted = true;
            this.isGameRunning = true;
            this.timer = new Timer(Model.getInstance().getLevelDelay(), this);
            this.timer.start();
            this.bcMusic.start();
            
//            swapPanel(this.boardPanel,this.animationPanel);
        }
        
        public void stopGame(){
            this.isGameRunning = false;
            this.timer.stop();
            this.boardPanel.setEnabled(false);
            this.startPauseBut.setText(START_BUTTON_LABEL);
            this.menuBut.setEnabled(true);
            if(!Model.getInstance().isLevelCompleted())                         /// to controller
                this.bcMusic.pause(); 
        }
        
        public void restart(){
            this.bcMusic = new SoundPlayer("bcMusic");
            this.isGameStarted=false;
        }
        
        public void endLevelAnimation(){ 
            this.boardPanel.endAnimation = true && Config.getInstance().isEndLevelAnimationOn();
        }

}//end class

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForModel;
import blockbuster.controller.ControllerForView;
import blockbuster.model.Model;
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
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
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
//        private boolean endLevelAnimation;
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
//                this.endLevelAnimation = false;
	}
        private void ReturnToStartWindows(){           
               this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ControllerForView.getInstance().openStartWindow();
                        initGame(); //load saved game
                        StopGame();
                        StopMusic();
                        
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
                try { //B of block buster
                    Image img = ImageIO.read(jbutSourceImg("\\source\\logo.png"));
                    this.setIconImage(img);        
                } catch (Exception ex) {
                System.out.println("Source not found");
                }
		this.pack();    
	}
        private void setGamePanel(){
                this.boardPanel = new BoardPanel();
                this.incrementPanel = new IncrementPanel();
                this.gamePanel=new JPanel();
                this.incrementPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.boardPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
                this.gamePanel.add(boardPanel);
                this.gamePanel.add(incrementPanel);
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
//            this.createGUI();
            //loadpreviuly started game if not game over
            
//            setEndGameOrNotStarted(); 
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
			StopGame();
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
		this.playerScoreLab.setText(String.valueOf(score));
	}
         public void updateLineLabel(int lines) {
		this.linesLeftLab.setText(String.valueOf(lines));
	}
        private File jbutSourceImg(String image){
            File homeFolder = null;
                try{
                    File byteCodeFileOfThisClass = new File(ImageSetting.class.getResource("MainGUI.class").toURI());
                    homeFolder = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
                }catch(URISyntaxException urise) {
                urise.printStackTrace();
                 }
                return new File(homeFolder.toString()+image);
        }
        public void createEndLevelButton(){
            this.endLevelBut = new JButton();
            this.endLevelBut.setText("LEVEL "+Model.getInstance().getLevel()+" COMPLETED");
            this.endLevelBut.setFont(new Font("Arial", Font.BOLD, 15));
            this.endLevelBut.setHorizontalTextPosition(JButton.CENTER);
            this.endLevelBut.setVerticalTextPosition(JButton.BOTTOM);
            this.endLevelBut.setBackground(Color.WHITE);
            try {
                Image img = ImageIO.read(jbutSourceImg("\\source\\level.png"));
                img.getScaledInstance(130, -1, Image.SCALE_SMOOTH);
                System.out.print(this.gamePanel.getWidth());
                this.endLevelBut.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println("Source not found");
            }
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
        }
        public void initGame(){//gameover + next level
                this.isGameStarted = false;
		this.isGameRunning = false;
		this.timer.stop();
		this.startPauseBut.setText(START_BUTTON_LABEL);
		this.menuBut.setEnabled(true);
        }
        public void StopGame(){
            this.isGameRunning = false;
            this.timer.stop();
            this.boardPanel.setEnabled(false);
            this.startPauseBut.setText(START_BUTTON_LABEL);
            this.menuBut.setEnabled(true);
            this.bcMusic.pause();
        }

        public void StopMusic(){
            this.bcMusic.stop();
            this.bcMusic.init();
        }
}//end class

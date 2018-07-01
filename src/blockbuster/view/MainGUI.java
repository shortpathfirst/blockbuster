/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import blockbuster.controller.ControllerForView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Andrea
 */
public class MainGUI extends JFrame  implements ComponentListener{//implements ActionListener
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
	private final static int WINDOW_PREFERRED_WIDTH = 720;
	private final static int WINDOW_PREFERRED_HEIGHT = 600;
	private final static String START_BUTTON_LABEL = "Start";
	private final static String PAUSE_BUTTON_LABEL = "Pause";
	private final static String PLAY_BUTTON_LABEL = "Play";
        
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	private BoardPanel boardPanel;
        private IncrementPanel incrementPanel;
        private JPanel optionPanel;
        
        private JButton menuBut;
	private JButton startPauseBut;
	private JLabel playerNameLab;
	private JLabel playerNamePrefixLab;
	private JLabel playerScoreLab;
	private JLabel playerScorePrefixLab;
	private JLabel nextPiecePrefixLab;
//        private Timer timer;
//	private boolean isGameStarted; // a game can start only once at the beginning
//	private boolean isGameRunning; // a started game can be running or in pause
        
        public MainGUI() {
		super("BlockBuster");
		this.createGUI();
//		this.timer = new Timer(Config.getInstance().getDelayTime(), this);
//		this.isGameStarted = false;
//		this.isGameRunning = false;
	}
        private void createGUI() {
		this.addComponentListener(this);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WINDOW_PREFERRED_WIDTH, WINDOW_PREFERRED_HEIGHT));
		this.boardPanel = new BoardPanel();
                this.incrementPanel = new IncrementPanel();
		this.incrementPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.setOptionPanel();                                         //stats ( player, score ecc..) + incrementPanel 
		Container contPane = this.getContentPane();
		contPane.setLayout(new BorderLayout());
		contPane.add(this.boardPanel, BorderLayout.EAST);
		contPane.add(this.optionPanel, BorderLayout.WEST);
                contPane.add(this.incrementPanel, BorderLayout.SOUTH);
		this.pack();
	}
        private void setOptionPanel() {
		this.optionPanel = new JPanel();
		//this.rightPanel.setBackground(Color.ORANGE);
		this.optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		this.optionPanel.setBorder(BorderFactory.createEmptyBorder()); //.createEmptyBorder(20, 20, 20, 20)

		this.playerNamePrefixLab = new JLabel("Player Name");
		this.playerNameLab = new JLabel(ControllerForView.getInstance().getPlayerName());
		this.playerScorePrefixLab = new JLabel("Score");
		this.playerScoreLab = new JLabel(ControllerForView.getInstance().getScore());
		this.nextPiecePrefixLab = new JLabel("Next Piece");
		this.startPauseBut = new JButton("Start");
		this.startPauseBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//startPauseEvent();
			}
		});
		this.startPauseBut.setMnemonic(KeyEvent.VK_P);
		
		this.menuBut = new JButton("Menu");
		this.menuBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//menuEvent();
			}
		});		

		this.playerNamePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerNameLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScorePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScoreLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.nextPiecePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.startPauseBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.menuBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		this.optionPanel.add(playerNamePrefixLab);
		this.optionPanel.add(this.playerNameLab);
		this.optionPanel.add(Box.createVerticalGlue());
		this.optionPanel.add(playerScorePrefixLab);
		this.optionPanel.add(this.playerScoreLab);
		this.optionPanel.add(Box.createVerticalGlue());
		this.optionPanel.add(nextPiecePrefixLab);
//		this.optionPanel.add(this.incrementPanel);
		this.optionPanel.add(Box.createVerticalGlue());
		this.optionPanel.add(this.startPauseBut);
		this.optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.optionPanel.add(this.menuBut);
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

        
        
}//end class

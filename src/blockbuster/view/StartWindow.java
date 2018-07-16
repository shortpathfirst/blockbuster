package blockbuster.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import blockbuster.controller.ControllerForView;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class StartWindow extends JFrame {

        private JButton jbutGamemode1;
        private JButton jbutGamemode2;
	private JButton jbutOption;
        private JButton jbutScoreboard;
        private JButton jbutHowToPlay;
        private JButton jbutExit;           

        private backgroundPanel img;

	public StartWindow() {
            
            super("Menù");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.img = new backgroundPanel();// Background
            this.img.setLayout(new BoxLayout(img, BoxLayout.Y_AXIS));
            this.img.setBorder(BorderFactory.createEmptyBorder(200, 100, 20, 100));
            
            Container contPane = this.getContentPane();
            contPane.add(img,BorderLayout.CENTER); 
        
            jbutGamemode1 = new JButton();
            jbutGamemode2 = new JButton();
            jbutHowToPlay = new JButton();
            jbutExit = new JButton();
            jbutScoreboard = new JButton();
            jbutOption = new JButton();
        
            jbutGamemode1.setText("Level Mode");
            jbutGamemode2.setText("Unlimited Mode");
            jbutHowToPlay.setText("How to play");
            jbutExit.setText("Exit");
            jbutScoreboard.setText("Score Board");
            jbutOption.setText("Option");

        Dimension jbutDim = new Dimension(150,35);
        
        jbutGamemode1.setMaximumSize(jbutDim);
        jbutGamemode1.setPreferredSize(jbutDim);
        
        jbutGamemode2.setMaximumSize(jbutDim);
        jbutGamemode2.setPreferredSize(jbutDim);
        
        jbutHowToPlay.setMaximumSize(jbutDim);
        jbutHowToPlay.setPreferredSize(jbutDim);
        
        jbutExit.setMaximumSize(jbutDim);
        jbutExit.setPreferredSize(jbutDim);
        
        jbutScoreboard.setMaximumSize(jbutDim);
        jbutScoreboard.setPreferredSize(jbutDim);
        
        jbutOption.setMaximumSize(jbutDim);
        jbutOption.setPreferredSize(jbutDim);

        Color buttonColor = new Color(204, 204, 204);
        
        jbutGamemode1.setBackground(buttonColor);
        jbutGamemode2.setBackground(buttonColor);
        jbutHowToPlay.setBackground(buttonColor);;
        jbutExit.setBackground(buttonColor);
        jbutOption.setBackground(buttonColor);
        jbutScoreboard.setBackground(buttonColor);
                                                                                
        this.jbutGamemode1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.jbutGamemode2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.jbutHowToPlay.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.jbutExit.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.jbutScoreboard.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.jbutOption.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                
        this.jbutGamemode1.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleGamemode1Event();
                                }
        });
        this.jbutGamemode2.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleGamemode2Event();
                                }
        });
        this.jbutHowToPlay.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleHowToPlayEvent();
                                }
        });
        this.jbutOption.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleOptionEvent();
                                }
        });
        this.jbutScoreboard.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    //To do
                                }
        });
        this.jbutExit.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleExitEvent();
                                }
        });
        
        this.img.add(this.jbutGamemode1);
        this.img.add(Box.createVerticalGlue());
        this.img.add(this.jbutGamemode2);
        this.img.add(Box.createVerticalGlue());
        this.img.add(this.jbutHowToPlay);
        this.img.add(Box.createVerticalGlue());
        this.img.add(this.jbutOption);
        this.img.add(Box.createVerticalGlue());
        this.img.add(this.jbutScoreboard);
        this.img.add(Box.createVerticalGlue());
        this.img.add(this.jbutExit);
        this.img.add(Box.createVerticalGlue());
            
            pack();
	} // end constructor

	private void handleGamemode1Event() {//System.out.println("Event: load a previously saved game");
		ControllerForView.getInstance().closeStartWindow();
                if(ControllerForView.getInstance().isGameOver())
                    ControllerForView.getInstance().initGame(); 
                ControllerForView.getInstance().openMainGUI();                   // openNewGameWindow();        FOR PLAYER NAME
	}
        private void handleGamemode2Event() {//System.out.println("Event: load a previously saved game");
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openNewGameWindow();
	}
	private void handleHowToPlayEvent() {
                ControllerForView.getInstance().closeStartWindow();
                ControllerForView.getInstance().openHowToPlayWindow();
	}
        private void handleOptionEvent() {
		new Option().setVisible(true);
	}
        private void handleScoreboardEvent() {
//		new OptionFrame().setVisible(true);
	}
        private void handleExitEvent() {
		this.setVisible(false);
                System.exit(0);
	}
       
} // end class

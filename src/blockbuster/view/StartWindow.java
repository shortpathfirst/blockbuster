package blockbuster.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import blockbuster.controller.ControllerForView;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class StartWindow extends JFrame {

	private JButton jbutGamemode1;
        private JButton jbutGamemode2;
	private JButton jbutLoadGame;
        private JButton jbutScoreboard;
        private JButton jbutHowToPlay;
        private JButton jbutExit;           

        private ImageSetting img;
        private GroupLayout layout;

	public StartWindow() {
            
            super("Menù");
            
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//            this.setResizable(false);//make button resizable                  Alternativa

            Container contPane = this.getContentPane();

            this.img = new ImageSetting();// Background
            layout = new GroupLayout(img);

            this.img.setLayout(new BoxLayout(img, BoxLayout.Y_AXIS));
            this.img.setBorder(BorderFactory.createEmptyBorder(200, 100, 20, 100));
//            this.img.setLayout(layout);                                       Alternativa
             contPane.add(img,BorderLayout.CENTER); 
        
        jbutGamemode1 = new JButton();
        jbutGamemode2 = new JButton();
        jbutHowToPlay = new JButton();
        jbutExit = new JButton();
        jbutScoreboard = new JButton();
        jbutLoadGame = new JButton();
        
        SetButtonColor();  
        
        jbutGamemode1.setText("Level Mode");
        jbutGamemode2.setText("Unlimited Mode");
        jbutHowToPlay.setText("How to play");
        jbutExit.setText("Exit");
        jbutScoreboard.setText("Score Board");
        jbutLoadGame.setText("Load Game");

//        jbutGamemode1.setForeground(Color.RED);
//        jbutGamemode2.setForeground(Color.ORANGE);
//        jbutHowToPlay.setForeground(Color.RED);
//        jbutExit.setForeground(Color.BLACK);
//        jbutScoreboard.setForeground(Color.ORANGE);
//        jbutLoadGame.setForeground(Color.BLUE);

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
        
        jbutLoadGame.setMaximumSize(jbutDim);
        jbutLoadGame.setPreferredSize(jbutDim);
            
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
        this.jbutScoreboard.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleOptionEvent();
                                }
        });
        this.jbutExit.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleExitEvent();
                                }
        });
                                                                                //remove alternativa
                this.jbutGamemode1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.jbutGamemode2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.jbutHowToPlay.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.jbutExit.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.jbutScoreboard.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                this.jbutLoadGame.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                
                this.img.add(this.jbutGamemode1);
                this.img.add(Box.createVerticalGlue());
                this.img.add(this.jbutGamemode2);
                this.img.add(Box.createVerticalGlue());
                this.img.add(this.jbutHowToPlay);
                this.img.add(Box.createVerticalGlue());
                this.img.add(this.jbutLoadGame);
                this.img.add(Box.createVerticalGlue());
                this.img.add(this.jbutScoreboard);
                this.img.add(Box.createVerticalGlue());
                this.img.add(this.jbutExit);
                this.img.add(Box.createVerticalGlue());
                
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(109, 109, 109)
//                .addContainerGap()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addComponent(jbutLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap(108, Short.MAX_VALUE))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addComponent(jbutExit, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addComponent(jbutHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                                .addGap(1, 1, 1)
//                                .addComponent(jbutGamemode1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                .addComponent(jbutGamemode2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
//                        .addGap(0, 0, Short.MAX_VALUE))))
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jbutScoreboard)
//                .addGap(0, 0, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(243, 243, 243)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jbutGamemode1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jbutGamemode2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(33, 33, 33)
//                .addComponent(jbutHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(18, 18, 18)
//                .addComponent(jbutLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
//                .addComponent(jbutExit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(68, 68, 68)
//                .addComponent(jbutScoreboard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(21, 21, 21))
//        );
            pack();
	} // end constructor

	private void handleGamemode1Event() {
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openMainGUI();                   // openNewGameWindow();        FOR PLAYER NAME
	}
        private void handleGamemode2Event() {
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openNewGameWindow();
	}
	private void handleHowToPlayEvent() {
		//System.out.println("Event: load a previously saved game");
                ControllerForView.getInstance().closeStartWindow();
                ControllerForView.getInstance().openOptionWindow();
	}
        private void handleOptionEvent() {
		//System.out.println("Event: load a previously saved game");
		new OptionFrame().setVisible(true);
	}
        private void handleScoreboardEvent() {
		new OptionFrame().setVisible(true);
	}
        private void handleExitEvent() {
		this.setVisible(false);
                System.exit(0);
	}
        private void SetButtonColor(){
            Color buttonColor = new Color(204, 204, 204);
            jbutGamemode1.setOpaque(true);
            jbutGamemode1.setBackground(buttonColor);
            jbutGamemode2.setOpaque(true);
            jbutGamemode2.setBackground(buttonColor);
            jbutHowToPlay.setOpaque(true);
            jbutHowToPlay.setBackground(buttonColor);
            jbutExit.setOpaque(true);
            jbutExit.setBackground(buttonColor);
            jbutLoadGame.setOpaque(true);
            jbutLoadGame.setBackground(buttonColor);
            jbutScoreboard.setOpaque(true);
            jbutScoreboard.setBackground(buttonColor);
        
        }
        
      
        
} // end class

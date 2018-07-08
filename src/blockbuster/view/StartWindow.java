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
import java.awt.event.ComponentEvent;

public class StartWindow extends JFrame {

	private JButton jbutGamemode1;
        private JButton jbutGamemode2;
	private JButton jbutLoadGame;
        private JButton jbutScoreboard;
        private JButton jbutHowToPlay;
        private JButton jbutExit;           //.closewindow();

        private ImageSetting img;
        private GroupLayout layout;

	public StartWindow() {
            
            super("Menù");
            
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(476,718);                                              //set default size + proprtion
            this.setResizable(false);//make button resizable

            Container contPane = this.getContentPane();
            this.img = new ImageSetting();                                        // Background
            layout = new GroupLayout(img);
            img.setLayout(layout);
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
        
        jbutGamemode1.setAlignmentY(CENTER_ALIGNMENT);
        jbutGamemode2.setAlignmentY(CENTER_ALIGNMENT);
        jbutHowToPlay.setAlignmentY(CENTER_ALIGNMENT);
        jbutExit.setAlignmentY(CENTER_ALIGNMENT);
        jbutScoreboard.setAlignmentY(CENTER_ALIGNMENT);
        jbutLoadGame.setAlignmentY(CENTER_ALIGNMENT);
        this.jbutGamemode1.addActionListener(new ActionListener() {                       //add actionlisteners
            public void actionPerformed(ActionEvent e) {
                                    handleGamemode1Event();
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


      
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbutLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(108, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbutExit, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbutHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jbutGamemode1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbutGamemode2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbutScoreboard)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutGamemode1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutGamemode2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jbutHowToPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbutLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jbutExit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jbutScoreboard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
            pack();
	} // end constructor

	private void handleGamemode1Event() {
		//System.out.println("Event: new game");                            //Test
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openMainGUI();                   // openNewGameWindow();        FOR PLAYER NAME
	}
        private void handleGamemode2Event() {
		//System.out.println("Event: new game");
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openNewGameWindow();
	}
	private void handleHowToPlayEvent() {
		//System.out.println("Event: load a previously saved game");
		new OptionFrame().setVisible(true);
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

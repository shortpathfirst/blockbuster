/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForView;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrea
 */
public class NewGameWindow extends JFrame {
        private JButton jbutStartGame;
	private JTextField jtfName;
        public NewGameWindow() {
		super("Set Player");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel jlabName = new JLabel("Player name: ");
		this.jtfName = new JTextField(15);
		this.jtfName.setText(ControllerForView.getInstance().getPlayerName());

		this.jbutStartGame = new JButton("Start");
		this.jbutStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleStartGameEvent(jtfName.getText());
			}
		});

		Container contPane = this.getContentPane();

		GroupLayout layout = new GroupLayout(contPane);
		contPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabName))
					
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfName))
                                        
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(jbutStartGame))
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jlabName)
                                        .addComponent(jtfName)
					.addComponent(jbutStartGame))
		);

		this.pack();
	} // end constructor

	private void handleStartGameEvent(String playerName) {
		//System.out.println("Event start game");
		ControllerForView.getInstance().setPlayerName(playerName);
                ControllerForView.getInstance().closeNewGameWindow();
		ControllerForView.getInstance().openMainGUI();
	}

}

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Andrea
 */
public class ScoreBoardWindow extends JFrame{
        private JButton jbutSetName;
	private JTextField jtfName;
        private JPanel scoreboardPane;
        public ScoreBoardWindow() {
            
		super("Set Player");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ReturnToStartWindows();
                
		JLabel jlabName = new JLabel("Player name: ");
		this.jtfName = new JTextField(15);
		this.jtfName.setText(ControllerForView.getInstance().getPlayerName());

		this.jbutSetName = new JButton("Set");
		this.jbutSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSetNameEvent();
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
                                     .addComponent(jbutSetName))
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jlabName)
                                        .addComponent(jtfName)
					.addComponent(jbutSetName))
		);

		this.pack();
        }
            private void ReturnToStartWindows(){                                    //load saved game     
               this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ControllerForView.getInstance().openStartWindow();
                    }
                  });
        }
        private void handleSetNameEvent() {
            int reply = JOptionPane.showConfirmDialog(null, "Do you want to confirm?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                ControllerForView.getInstance().setPlayerName(this.jtfName.getText());
            }
        }
    }


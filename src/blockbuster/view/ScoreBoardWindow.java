/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForView;
import blockbuster.utils.Score;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Andrea
 */
public class ScoreBoardWindow extends JFrame{
        private JButton jbutSetName;
	private JTextField jtfName;
        private JPanel playerPane;
        private JPanel scoreBoardPane;
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

                playerPane = new JPanel();
		GroupLayout layout = new GroupLayout(playerPane);
		playerPane.setLayout(layout);

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
                playerPane.add(jlabName);
                playerPane.add(jtfName);
                playerPane.add(jbutSetName);
                
                scoreBoardPane = new JPanel();
                scoreBoardPane.setLayout(new GridLayout(0,3));
                
                HashMap<String, String> map = Score.getInstance().getPlayerScores();
                for(int i=0; i<=10; i++){
                        double max = Double.NEGATIVE_INFINITY;
                        String key="";
                        for(Map.Entry<String, String> entry : map.entrySet()) {
                            String[] s = entry.getValue().split(",");
                                if (Integer.parseInt(s[0].trim()) > max){ 
                                    max = Integer.parseInt(s[0].trim());
                                    key = entry.getKey();
                                }
                        }
                        JLabel index = new JLabel(" "+(i+1)+".");
                        JLabel name = new JLabel(key);
                        JLabel score = new JLabel(""+max);
                        map.remove(key);
                        if(max >Double.NEGATIVE_INFINITY){
                            scoreBoardPane.add(index);
                            scoreBoardPane.add(name);
                            scoreBoardPane.add(score);
                        }
                }
                
                 HashMap<String, String> map2 = Score.getInstance().getPlayerScores();
                for(int i=0; i<=10; i++){
                        double max = Double.NEGATIVE_INFINITY;
                        String key="";
                        for(Map.Entry<String, String> entry : map2.entrySet()) {
                            String[] s = entry.getValue().split(",");
                                if (!s[1].trim().isEmpty() && Integer.parseInt(s[1].trim()) > max){ 
                                    max = Integer.parseInt(s[1].trim());
                                    key = entry.getKey();
                                }   
                        }
                        JLabel index = new JLabel(" "+(i+1)+".");
                        JLabel name = new JLabel(key);
                        JLabel score = new JLabel(""+max);
                        map2.remove(key);
                        if(max >Double.NEGATIVE_INFINITY){
                            scoreBoardPane.add(index);
                            scoreBoardPane.add(name);
                            scoreBoardPane.add(score);
                        }
                }
                Container contPane = this.getContentPane();
                JPanel container = new JPanel();
                container.setLayout(new BorderLayout());
                container.add(playerPane, BorderLayout.NORTH);
                container.add(scoreBoardPane,BorderLayout.CENTER);

                contPane.add(container);
		this.pack();
        }
        private void ReturnToStartWindows(){
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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Andrea
 */
public class HowToPlayWindow extends JFrame{
    private JButton jbutPage1;
    private JButton jbutPage2;
    private JButton jbutPage3;
    private JPanel jpanePage1;
    private JPanel jpanePage2;
    private JPanel jpanePage3;
    private JPanel mainpanel;
    private JPanel buttonPanel;
    
    public HowToPlayWindow(){
        initComponents();
    }
    private void initComponents() {
        mainpanel = new JPanel();
        buttonPanel = new JPanel();
        jpanePage1 = new JPanel();
        jpanePage2 = new JPanel();
        jpanePage3 = new JPanel();
        jbutPage1 = new JButton();
        jbutPage2 = new JButton();
        jbutPage3 = new JButton();
        
        ReturnToStartWindows();
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        
        initPage1();
        initPage2();
        mainpanel.setLayout(new java.awt.CardLayout());
        
        mainpanel.add(jpanePage1, "card1");
        mainpanel.add(jpanePage2, "card2");
        mainpanel.add(jpanePage3, "card3");

        jbutPage1.setText("Page 1");
        jbutPage1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbutPage1ActionPerformed(evt);
            }
        });

        jbutPage2.setText("Page 2");
        jbutPage2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbutPage2ActionPerformed(evt);
            }
        });

        jbutPage3.setText("Page 3");
        jbutPage3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutPage3ActionPerformed(evt);
            }
        });
        buttonPanel.add(jbutPage1);
        buttonPanel.add(jbutPage2);
        buttonPanel.add(jbutPage3);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(mainpanel, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        
        pack();
    }
    private void ReturnToStartWindows(){                                    //load saved game     
               this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ControllerForView.getInstance().openStartWindow();
                    }
                  });
        }
    private void jbutPage1ActionPerformed(ActionEvent evt) {                                         
        CardLayout card = (CardLayout)mainpanel.getLayout();
        card.show(mainpanel, "card1");
    }                                        

    private void jbutPage2ActionPerformed(ActionEvent evt) {                                         
            CardLayout card = (CardLayout)mainpanel.getLayout();
            card.show(mainpanel, "card2");
    }                                        

    private void jbutPage3ActionPerformed(ActionEvent evt) {                                         
            CardLayout card = (CardLayout)mainpanel.getLayout();
            card.show(mainpanel, "card3");
    }   
    private void initPage1(){
        this.jpanePage1.setLayout(new BorderLayout(100,100));
        this.jpanePage1.setPreferredSize(new Dimension(1200,400));
        this.jpanePage1.setBackground(Color.ORANGE);
        JPanel content = new JPanel();
        content.setBackground(Color.ORANGE);
        
       
        JLabel titleLabel1 = new JLabel("The goal");
        JLabel textLabel1 = new JLabel("<html>Collapse the rising blocks to get "
                                          + "<br> as many points as possible</html>");
        

        JLabel titleLabel2 = new JLabel("How to Play");
        JLabel textLabel2 = new JLabel("<html>Score points by clickin on like-colored blocks that are grouped "
                                            + "<br> in clusters of three or more(stacked in any direction)."
                                            +"<br> The blocks will explode and collapse onto any blocks below them</html>");
        
        
        JLabel titleLabel3 = new JLabel("Levels");
        JLabel textLabel3 = new JLabel("<html>To move to the next level you must first clear "
                                    + "<br> the number of lines of the blocks displayed. "
                                     +"<br> Each level speeds up, you have to be quick!</html>");
        
        titleLabel1.setFont(new Font("Verdana",1,20));
        titleLabel2.setFont(new Font("Verdana",1,20));
        titleLabel3.setFont(new Font("Verdana",1,20));
        
        
         GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
           layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(titleLabel1)
                     .addComponent(textLabel1))
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(titleLabel2)
                     .addComponent(textLabel2))
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(titleLabel3)
                     .addComponent(textLabel3))
        );
        layout.setVerticalGroup(
           layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(titleLabel1)
                   .addComponent(titleLabel2)
                    .addComponent(titleLabel3))
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(textLabel1)
                      .addComponent(textLabel2)
                      .addComponent(textLabel3))
        );
        jpanePage1.add(content);

    }
    private void initPage2(){
        JPanel blockPane = new JPanel();
        blockPane.setLayout(new GridLayout(0,2));
        JLabel b1 = new JLabel("There are 5 normal colored blocks");
        
        for(int b=1; b<=9; b++){
            JPanel block1 = new JPanel();
            for (int i=0; i<=5; ++i) {
                JLabel l = new JLabel();
                l.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(b,i)));
                block1.add(l);
            }
            JLabel lab = new JLabel(""+b);
            lab.setFont(new Font("Verdana",Font.PLAIN,20));
            blockPane.add(lab);
            blockPane.add(block1);
        }
         jpanePage2.setLayout(new GridLayout(0,2));
         jpanePage2.add(blockPane);
         JLabel description = new JLabel("Block from 1 to 5 are simple block to remove if 3 or more");
         jpanePage2.add(description);
         
    }
        
        
}


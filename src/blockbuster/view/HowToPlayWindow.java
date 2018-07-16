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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        this.jpanePage1.setLayout(new BorderLayout(300,100));
        this.jpanePage1.setPreferredSize(new Dimension(500,500));
        this.jpanePage1.setBackground(Color.ORANGE);
        JPanel content = new JPanel();
        content.setBackground(Color.ORANGE);
        content.setLayout(new GridLayout(0,3));
        
        
        JLabel imgLabel1 = new JLabel();
        JLabel titleLabel1 = new JLabel("The goal");
        JLabel textLabel1 = new JLabel("Collapse the rising blocks to get as many points as possible");
        
        JLabel imgLabel2 = new JLabel();
        JLabel titleLabel2 = new JLabel("How to Play");
        JLabel textLabel2 = new JLabel("<html>Score points by clickin on like-colored blocks that are grouped in clusters of three or more(stacked in any direction)."
                                            +"<br> The blocks will explode and collapse onto any blocks below them</html>");
        
        JLabel imgLabel3 = new JLabel();
        JLabel titleLabel3 = new JLabel("Levels");
        JLabel textLabel3 = new JLabel("<html>To move to the next level you must first clear the number of lines of the blocks displayed. "
                                     +"<br> Each level speeds up, you have to be quick!</html>");
        
        titleLabel1.setFont(new Font("Verdana",1,20));
        titleLabel2.setFont(new Font("Verdana",1,20));
        titleLabel3.setFont(new Font("Verdana",1,20));
        
        Image img = BlockStyle.getInstance().getBlockSprite(1);
        imgLabel1.setIcon(new ImageIcon(img));
        imgLabel2.setIcon(new ImageIcon(img));
        imgLabel3.setIcon(new ImageIcon(img));
        
//        imgLabel.setIcon(new ImageIcon(img));
//        content.add(imgLabel);
        content.add(imgLabel1);
        content.add(titleLabel1);
        content.add(textLabel1);
        
        content.add(imgLabel2);
        content.add(titleLabel2);
        content.add(textLabel2);
        
        content.add(imgLabel3);
        content.add(titleLabel3);
        content.add(textLabel3);
        jpanePage1.add(content);

    }
    private void initPage2(){
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0,2));
        Image img = BlockStyle.getInstance().getBlockSprite(1);
    }
}

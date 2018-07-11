/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Andrea
 */
public class Option extends JFrame{
    private JButton jbutPage1;
    private JButton jbutPage2;
    private JButton jbutPage3;
    private JPanel jpanePage1;
    private JPanel jpanePage2;
    private JPanel jpanePage3;
    private JPanel mainpanel;
    private JPanel buttonPanel;
    Volume incrementPane;
    
    
    public Option() {
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
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        incrementPane = new Volume();
        jpanePage1.setLayout(new BorderLayout());
        jpanePage1.setPreferredSize(new Dimension(400,400));
        jpanePage1.add(incrementPane,BorderLayout.SOUTH);
        
        
        
        
        mainpanel.setLayout(new java.awt.CardLayout());
//        buttonPanel.setLayout(new BorderLayout());
        
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
    
     private class Volume extends IncrementPanel {
         int volume = 4;
         public Volume(){
            super(); 
            this.repaint();
         }
         @Override
         public void drawCells(Graphics2D g2d){
            for(int j=0; j<15; j++)
                drawBlockAtCell(g2d,j,0);
        }
     }
}

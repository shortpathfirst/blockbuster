package blockbuster.view;

import blockbuster.controller.ControllerForView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HowToPlayWindow extends JFrame{
    private JButton jbutPage1;
    private JButton jbutPage2;
    private JPanel jpanePage1;
    private JPanel jpanePage2;
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
        jbutPage1 = new JButton();
        jbutPage2 = new JButton();
        
        returnToStartWindows();

        initPage1();
        initPage2();
        mainpanel.setLayout(new java.awt.CardLayout());
        
        mainpanel.add(jpanePage1, "card1");
        mainpanel.add(jpanePage2, "card2");

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

        buttonPanel.add(jbutPage1);
        buttonPanel.add(jbutPage2);

        this.getContentPane().setLayout(new BorderLayout());
        this.add(mainpanel, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        
        pack();
    }
    private void returnToStartWindows(){
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

    private void initPage1(){
         JLabel titleLabel1 = new JLabel("The goal");
        JLabel textLabel1 = new JLabel("<html>Collapse the rising blocks to get as many points as possible</html>");                         
        JLabel imgLabel1a = new JLabel();
        imgLabel1a.setIcon(new ImageIcon(ImgSetting.getInstance().getImagePath("tutorial1a.gif")));
        JLabel imgLabel1b = new JLabel();
        imgLabel1b.setIcon(new ImageIcon(ImgSetting.getInstance().getImagePath("tutorial1b.gif")));
        
        JPanel imgPanel1 = new JPanel();
        imgPanel1.setLayout(new BorderLayout());
        imgPanel1.add(imgLabel1a,BorderLayout.SOUTH);
        imgPanel1.add(imgLabel1b,BorderLayout.PAGE_START);
        
        JLabel titleLabel2 = new JLabel("How to Play");
        JLabel textLabel2 = new JLabel("<html>Score points by clickin on like-colored blocks that are grouped "
                                            +"<br> in clusters of three or more(stacked in any direction)."
                                            +"<br> The blocks will explode and collapse onto any blocks below them</html>");
        JLabel imgLabel2 = new JLabel();
        imgLabel2.setIcon(new ImageIcon(ImgSetting.getInstance().getImagePath("tutorial2.png")));
        
        
        JLabel titleLabel3 = new JLabel("Levels");
        JLabel textLabel3 = new JLabel("<html>To move to the next level you must first clear "
                                    + "<br> the number of lines of the blocks displayed. "
                                     +"<br> Each level speeds up, you have to be quick!</html>");
        JLabel imgLabel3 = new JLabel();
        imgLabel3.setIcon(new ImageIcon(ImgSetting.getInstance().getImagePath("tutorial3.png")));
        
        titleLabel1.setFont(new Font("Verdana",1,20));
        titleLabel2.setFont(new Font("Verdana",1,20));
        titleLabel3.setFont(new Font("Verdana",1,20));
        
        
        GroupLayout layout = new GroupLayout(jpanePage1);
        jpanePage1.setLayout(layout);
       
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
           layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel1)
                    .addComponent(textLabel1)
                    .addComponent(imgPanel1))
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(titleLabel2)
                     .addComponent(textLabel2)
                     .addComponent(imgLabel2))
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(titleLabel3)
                     .addComponent(textLabel3)
                    .addComponent(imgLabel3))
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(imgPanel1)
                      .addComponent(imgLabel2)
                      .addComponent(imgLabel3))
        );

        this.pack();
    }
    private void initPage2(){
        JPanel blockPane = new JPanel();
        blockPane.setLayout(new GridLayout(0,7));
        for(int type=1; type<=9; type++){
            JLabel numlab = new JLabel(""+type);
            numlab.setFont(new Font("Verdana",Font.PLAIN,20));
            blockPane.add(numlab);
            
            for (int i=0; i<=5; ++i) {
                JLabel block = new JLabel();
                block.setIcon(new ImageIcon(ImgSetting.getInstance().getBlockSprite(type,i).getScaledInstance(50,50, Image.SCALE_DEFAULT)));
                
                blockPane.add(block);
            }

        }
         jpanePage2.setLayout(new GridLayout(0,2));
         jpanePage2.add(blockPane);
         JLabel description = new JLabel("<html>Blocks from 1 to 5 are simple colored blocks, once you advance in the game"
                 + "<br>  it will became more difficult to find adjacent blocks"
                 + "<br><br>6 : Click on it won't work you must find a way with other blocks"
                 + "<br>7 : This block is the real enemy for blocks, "
                 + "<br>   click on it to detonate all like-colored blocks in the game area "
                 + "<br>8:Click on it to explode the surrounding blocks...even the nice ones!!"
                 + "<br>9: If there's a mess, it's the right choice, "
                 + "<br>it will randomly change the color of the blocks around it</html>");
         description.setFont(new Font("Verdana",Font.BOLD,16));
         jpanePage2.add(description);
         this.pack();
    }
        
        
}


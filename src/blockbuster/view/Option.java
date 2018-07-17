/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForView;
import blockbuster.utils.Config;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author Andrea
 */
public class Option extends JFrame{
    private JPanel volumePanel;
    private JPanel mainpanel;
    private JPanel radioPanel;
    private JPanel effectPanel;
    public Option() {
        initComponents();
        
    }
    private void initComponents() {
        this.mainpanel = new JPanel();
        this.volumePanel = new JPanel();
        
        ReturnToStartWindows();
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        StyleMenu();
        
        this.effectPanel = new JPanel();
        JCheckBox effect1 = new JCheckBox();
        effect1.setText("Score at block");
        if(Config.getInstance().isScoreLabelOn())
            effect1.setSelected(true);
        else
            effect1.setSelected(false);
            
        effect1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(effect1.isSelected())
                                   Config.getInstance().setScoreLabel(true);
                                else
                                    Config.getInstance().setScoreLabel(false);
			}
		});
        this.effectPanel.add(effect1);
            
            
            
            
            
            
        this.volumePanel.setLayout(new BorderLayout());
        this.volumePanel.setPreferredSize(new Dimension(400,400));
        this.volumePanel=new Volume();
        
        this.mainpanel.setLayout(new GridLayout(0,1));
        this.mainpanel.add(volumePanel); //set layout
        this.mainpanel.add(radioPanel);
        this.mainpanel.add(effectPanel);
        this.getContentPane();
        this.add(mainpanel);
        
        pack();
    }
        private void ReturnToStartWindows(){                                     
               this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ControllerForView.getInstance().openStartWindow();
                    }
                  });
        }
      
     private void StyleMenu(){
        JRadioButton birdButton = new JRadioButton("Style 1");
        birdButton.setActionCommand("Style 1");
        birdButton.setSelected(true);

        JRadioButton catButton = new JRadioButton("Style 2");
        catButton.setActionCommand("Style 2");

        JRadioButton dogButton = new JRadioButton("Style 3");
        dogButton.setActionCommand("Style 3");

        JRadioButton rabbitButton = new JRadioButton("Style 4");
        rabbitButton.setActionCommand("Style 4");

        JRadioButton pigButton = new JRadioButton("Style 5");
        pigButton.setActionCommand("Style 5");

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);
        if(Config.getInstance().getBlockStyle() == 0)
            birdButton.setSelected(true);
        if(Config.getInstance().getBlockStyle() == 1)
            catButton.setSelected(true);
        if(Config.getInstance().getBlockStyle() == 2)
            dogButton.setSelected(true);
        if(Config.getInstance().getBlockStyle() == 3)
            rabbitButton.setSelected(true);
        if(Config.getInstance().getBlockStyle() == 4)
            pigButton.setSelected(true);

        birdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              Config.getInstance().setBlockStyle(0);
			}
		});
        catButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              Config.getInstance().setBlockStyle(1);
			}
		});
        dogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              Config.getInstance().setBlockStyle(2);
			}
		});
        rabbitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              Config.getInstance().setBlockStyle(3);
			}
		});
        pigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                              Config.getInstance().setBlockStyle(4);
			}
		});
        
        
        JLabel block0 = new JLabel();
        block0.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(1, 0)));
        JLabel block1 = new JLabel();
        block1.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(1, 1)));
        JLabel block2 = new JLabel();
        block2.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(1, 2)));
        JLabel block3 = new JLabel();
        block3.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(1, 3)));
        JLabel block4 = new JLabel();
        block4.setIcon(new ImageIcon(BlockStyle.getInstance().getBlockSprite(1, 4)));
        
        radioPanel = new JPanel(new GridLayout(0, 2));
        
        radioPanel.add(birdButton);
        radioPanel.add(block0);
        radioPanel.add(catButton);
        radioPanel.add(block1);
        radioPanel.add(dogButton);
        radioPanel.add(block2);
        radioPanel.add(rabbitButton);
        radioPanel.add(block3);
        radioPanel.add(pigButton);
        radioPanel.add(block4);
     }

    
     private class Volume extends JPanel{    //inner class chiamata all'interno  
         private int volumeValue;
         private JSlider volumeSlide;
         private JSlider effectsSlide;
         public Volume(){
            super(); 
            this.setLayout(new GridLayout(2,2));
            JLabel volumeLabel = new JLabel("Volume:");
            JLabel effectLabel = new JLabel("Effects:");
            volumeSlide = Slider();
            effectsSlide = Slider();
            try{
                volumeSlide.setValue(Config.getInstance().getSoundVolume());
                effectsSlide.setValue(Config.getInstance().getEffectsVolume());
            }catch(NumberFormatException ex){
                System.out.println("Config field not found");
            }
            //to do
            add(volumeLabel);
            add(volumeSlide);
            add(effectLabel);
            add(effectsSlide);
         }
         private JSlider Slider(){
            JSlider volumeSlider= new JSlider(JSlider.HORIZONTAL,0,100,50);
            
            volumeSlider.addChangeListener(new ChangeListener(){
                      public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider)e.getSource();
                         if (!source.getValueIsAdjusting()) {
                             volumeValue =source.getValue();
                             Config.getInstance().setVolume(volumeValue);
                             Config.getInstance().setVolume(volumeValue);
                         }
                }
            });
            //Turn on labels at major tick marks.
            volumeSlider.setMajorTickSpacing(10);
            volumeSlider.setMinorTickSpacing(1);
            volumeSlider.setPaintTicks(true);
            volumeSlider.setPaintLabels(true);
            volumeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
            
            return volumeSlider;
         }
     }//end inner class
}

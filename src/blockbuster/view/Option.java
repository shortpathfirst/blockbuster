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
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    public Option() {
        initComponents();
        
    }
    private void initComponents() {
        mainpanel = new JPanel();
        volumePanel = new JPanel();
        
        ReturnToStartWindows();
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        volumePanel.setLayout(new BorderLayout());
        volumePanel.setPreferredSize(new Dimension(400,400));
        volumePanel=new Volume();

        mainpanel.add(volumePanel); //set layout
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

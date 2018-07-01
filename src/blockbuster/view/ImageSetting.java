/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Andrea
 */
public class ImageSetting extends JPanel{
    public static final String image_folder  = "C:\\Users\\Andrea\\Desktop\\background.gif";
    public static final String backgroundPath = "C:\\Users\\Andrea\\Desktop\\background.gif";
    private Image img;
    private int Width=476;//DEFAULT Width 476
    private int Height=718;//DEFAULT Height 718
    
    public ImageSetting() {
        //Take defaul in dimension of panel (418 719)
        img = Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(Width, Height, Image.SCALE_DEFAULT);
        loadImage(img);
  }
private void loadImage(Image img) {
    try {
      MediaTracker track = new MediaTracker(this);// https://docs.oracle.com/javase/7/docs/api/java/awt/MediaTracker.html
      track.addImage(img, 0);//addImage method for each image to be tracked. In addition, each image can be assigned a unique identifie
      track.waitForID(0);
    } catch (InterruptedException e) {// To remove or show error
      e.printStackTrace();
    }
}
public void setBackgroundScale(int newWidth,int newHeight){
    this.Width = newWidth;
    this.Height = newHeight;
    img=Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(Width, Height, Image.SCALE_DEFAULT);
   // img.getScaledInstance(Width, Height, Image.SCALE_DEFAULT);
  //  loadImage(img);
}

@Override
protected void paintComponent(Graphics g) {// da spostare come con colorsetting
    //super.paintComponent(g);
    setOpaque(false);
    g.drawImage(img, 0, 0, null);
//    g.drawImage(img,
//                    dx, dy, 
//                    x+cellWidth, dy+cellHeight,
//                    sx, sy,
//                    sx+cellWidth, sy+cellHeight,
//                    null);

    super.paintComponent(g);
  }

}//End Class

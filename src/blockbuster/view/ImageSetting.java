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
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JPanel;
/**
 *
 * @author Andrea
 */
public class ImageSetting extends JPanel{
    public static String backgroundPath;
    public static final String backgroundRelPath = "\\source\\background.gif";
    private Image img;
//    private final static Dimension PREFERRED_SIZE = new Dimension(476 ,718);
    private final static int WIDTH = 382;
    private final static int HEIGHT = 580;
    private final static Dimension PREFERRED_SIZE = new Dimension(WIDTH ,HEIGHT);
    
    public ImageSetting() { 
        try{
            backgroundPath = getHomeFolderForDevVersion() + backgroundRelPath;
        }catch(URISyntaxException urise) {
                urise.printStackTrace();
        }
        this.setSize(PREFERRED_SIZE);
        img = Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT);
        Toolkit.getDefaultToolkit().prepareImage(img, 382, 580, this);
//        loadImage(img);
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
	private String getHomeFolderForDevVersion() throws URISyntaxException {
		File homeFolder = null;
		File byteCodeFileOfThisClass = new File(ImageSetting.class.getResource("ImageSetting.class").toURI());
		//System.out.println("byteCodeFileOfThisClass: " + byteCodeFileOfThisClass);
		homeFolder = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		//System.out.println("configFile: " + configFile.toString());
		return homeFolder.toString();
	}
@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}
@Override
protected void paintComponent(Graphics g) {// da spostare come con colorsetting
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), null);
//        this.setOpaque(false);
        
    
 
  }

}//End Class

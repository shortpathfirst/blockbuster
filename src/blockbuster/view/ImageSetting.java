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
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Andrea
 */
public class ImageSetting extends JPanel{
    public static String backgroundPath;
    public static final String backgroundRelPath = "\\source\\background.gif";
    private Image img;
    private final static Dimension PREFERRED_SIZE = new Dimension(476 ,718);
    public ImageSetting() { 
        try{
            backgroundPath = getHomeFolderForDevVersion() + backgroundRelPath;
        }catch(URISyntaxException urise) {
                urise.printStackTrace();
        }
        this.setSize(PREFERRED_SIZE);
        img = Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT);
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
		File configFile = null;
		File byteCodeFileOfThisClass = new File(ImageSetting.class.getResource("ImageSetting.class").toURI());
		//System.out.println("byteCodeFileOfThisClass: " + byteCodeFileOfThisClass);
		configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		//System.out.println("configFile: " + configFile.toString());
		return configFile.toString();
	}
private String getHomeFolderForDistVersion() throws URISyntaxException {
		String homeDir = null;
		String jarPath = ImageSetting.class.getResource("ImageSetting.class").toURI().toString();
		int indexOfExclamationMark = jarPath.indexOf("/build");
		String prefix = "file:/"; // this is the prefix for Windows OS platform
		if (System.getProperty("os.name").startsWith("Linux")) {
			prefix = "jar:file:";
		}
		homeDir = jarPath.substring(prefix.length(), indexOfExclamationMark);
		int lastIndexOfSlash = homeDir.lastIndexOf("/");
		homeDir = homeDir.substring(0, lastIndexOfSlash);
		return homeDir;
	}
@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}
@Override
protected void paintComponent(Graphics g) {// da spostare come con colorsetting
//    super.paintComponent(g);
    setOpaque(false);
    g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), null);
  }

}//End Class

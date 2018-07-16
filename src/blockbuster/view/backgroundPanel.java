/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JPanel;
/**
 *
 * @author Andrea
 */
public class backgroundPanel extends JPanel{
    public static String backgroundPath;
    public static final String backgroundRelPath = "\\source\\background.gif";
    private Image img;
    private final static int WIDTH = 382;
    private final static int HEIGHT = 580;
    private final static Dimension PREFERRED_SIZE = new Dimension(WIDTH ,HEIGHT);
    
    public backgroundPanel() { 
        try{
            backgroundPath = getHomeFolderForDevVersion() + backgroundRelPath;
        }catch(URISyntaxException urise) {
                urise.printStackTrace();
                this.setBackground(Color.ORANGE);
        }catch(NullPointerException npe){  //should not be catch
                System.out.println("Background Source not found");
                this.setBackground(Color.ORANGE);
        }
        this.setSize(PREFERRED_SIZE);
        
        img = Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT);
        Toolkit.getDefaultToolkit().prepareImage(img, 382, 580, this);
  }
	private String getHomeFolderForDevVersion() throws URISyntaxException,NullPointerException {
		File homeFolder = null;
		File byteCodeFileOfThisClass = new File(backgroundPanel.class.getResource("backgroundPanel.class").toURI());
		homeFolder = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		return homeFolder.toString();
	}
@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}
@Override
protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), null);
  }
}//End Class

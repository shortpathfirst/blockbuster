package blockbuster.view;

import blockbuster.utils.Config;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
    public static String backgroundPath;
    private final static boolean IS_DIST_VERSION = true; // this flag must be set to true when compiling for the dist version
    public static final String backgroundRelPath = "\\source\\background.gif";
    private Image img;
    private final static int WIDTH = 382;
    private final static int HEIGHT = 580;
    private final static Dimension PREFERRED_SIZE = new Dimension(WIDTH ,HEIGHT);
    
    public BackgroundPanel() { 
        try{
            if(IS_DIST_VERSION)
                backgroundPath = getHomeFolderForDistVersion()+backgroundRelPath;
            else
                backgroundPath = getHomeFolderForDevVersion() + backgroundRelPath;
        }catch(URISyntaxException urise) {
                urise.printStackTrace();
                this.setBackground(Color.ORANGE);
        }catch(NullPointerException npe){
                System.out.println("Background Source not found");
                this.setBackground(Color.ORANGE);
        }
        this.setSize(PREFERRED_SIZE);
        
        img = Toolkit.getDefaultToolkit().createImage(backgroundPath).getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT);
        Toolkit.getDefaultToolkit().prepareImage(img, 382, 580, this);
  }
	private String getHomeFolderForDevVersion() throws URISyntaxException,NullPointerException {
		File homeFolder = null;
		File byteCodeFileOfThisClass = new File(BackgroundPanel.class.getResource("BackgroundPanel.class").toURI());
		homeFolder = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		return homeFolder.toString();
	}
        private String getHomeFolderForDistVersion() throws URISyntaxException {
                String homeDir = null;
                String jarPath = BackgroundPanel.class.getResource("BackgroundPanel.class").toURI().toString();
                System.out.println(jarPath);
                int indexOfExclamationMark = jarPath.indexOf("!");
                String prefix = "jar:file:/"; // this is the prefix for Windows OS platform
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
protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), null);
  }
}//End Class

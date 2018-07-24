package blockbuster.view;

import blockbuster.utils.Config;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;


public class ImgSetting {
        //---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static ImgSetting instance = null;
        private final static boolean IS_DIST_VERSION = true;// this flag must be set to true when compiling for the dist version
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
	private Color colorGridLineBoard;
        private BufferedImage[] sprites;
        private Image logoImg;
        private Image levelImg;
        
        private ImgSetting(){
             loadImage();  
        }

        public Color getGridColor() {
            if(this.colorGridLineBoard== null)
                  this.colorGridLineBoard = Color.LIGHT_GRAY;
            return this.colorGridLineBoard;
	}

        public Color getBlockColor(int numBlock) { 
            
            if(numBlock ==1)
                return Color.ORANGE;
            if(numBlock ==2)
                return Color.MAGENTA;
            if(numBlock ==3)
                return Color.BLUE;
            if(numBlock ==4)
                return Color.YELLOW;
            if(numBlock ==5)
                return Color.GREEN;
            if(numBlock ==6)
                return Color.CYAN;
            if(numBlock ==7)
                return Color.BLACK;
            if(numBlock ==8)
                 return Color.GREEN;
            if(numBlock == 9)
                return Color.RED;
            else
            return Color.WHITE;
        }
        public BufferedImage getBlockSprite(int numBlock){
            int i = Config.getInstance().getBlockStyle();
            return getBlockSprite(numBlock,i);
        }
        public BufferedImage getBlockSprite(int numBlock,int i){
            if(numBlock ==1)
                return this.sprites[0+i];
            if(numBlock ==2)
                return this.sprites[6+i];
            if(numBlock ==3)
               return this.sprites[12+i];
            if(numBlock ==4)
                return this.sprites[18+i];
            if(numBlock ==5)
                return this.sprites[24+i];
            if(numBlock ==6)
                return this.sprites[48+i];
            if(numBlock ==7)
                return this.sprites[30+i];
            if(numBlock ==8)
                return this.sprites[36+i];
            if(numBlock == 9)
                return this.sprites[42+i];
            else
            return null;
        }
        public String getFileLocation() throws URISyntaxException{
            if (IS_DIST_VERSION)
                return getHomeFolderForDistVersion();
            else{
                File configFile = null;
                File byteCodeFileOfThisClass;
                byteCodeFileOfThisClass = new File(ImgSetting.class.getResource("ImgSetting.class").toURI());
                configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
                return configFile.toString();
            }
        }
        private String getHomeFolderForDistVersion() throws URISyntaxException {
		String homeDir = null;
		String jarPath = ImgSetting.class.getResource("ImgSetting.class").toURI().toString();
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
        public Image getButtonIcon(){
            return logoImg;
        }
        public Image getEndLevelImage(){
            levelImg.getScaledInstance(130, -1, Image.SCALE_SMOOTH);
            return levelImg;
        }

        public String getImagePath(String name){ 
            try {
                return getFileLocation()+"\\source\\"+name;
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            return null;
        }


        private void loadImage() {
                BufferedImage bigImg= null;
                try{
                    bigImg = ImageIO.read(new File(getFileLocation()+"\\source\\BlockSprites.png"));
                    logoImg = ImageIO.read(new File(getFileLocation()+"\\source\\logo.png")); 
                    levelImg = ImageIO.read(new File(getFileLocation()+"\\source\\level.png"));
                }catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                    System.out.println("Source not found");
                }
                final int width =24;
                final int height = 24;
                final int rows = 9;
                final int cols = 6;
                sprites = new BufferedImage[rows * cols];

                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        sprites[(i * cols) + j] = bigImg.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                        );
                    }
                }
        }
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static ImgSetting getInstance() {
		if (instance == null)
			instance = new ImgSetting();
		return instance;
	}
        
}//end class

package blockbuster.utils;

import blockbuster.controller.ControllerForView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Properties;


public class Config {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static boolean IS_DIST_VERSION = true; // this flag must be set to true when compiling for the dist version

	//---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static Config instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
	private Properties properties;

	private Config() {
		try {
			String configFile = getConfigFile();
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "ISO-8859-1"));
			this.properties = new Properties();
			this.properties.load(buffRead);
		}
		catch(URISyntaxException urise) {
			urise.printStackTrace();
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private String getConfigFile() throws URISyntaxException {
		String configFile = null;
		String relPath = "\\config\\config.txt";
		if (System.getProperty("os.name").startsWith("Linux")) {
			relPath = "/conf/config.txt";
		}
		if (IS_DIST_VERSION)
			configFile = getHomeFolderForDistVersion() + relPath;
		else
			configFile = getHomeFolderForDevVersion() + relPath;
		return configFile;
	}

	private String getHomeFolderForDistVersion() throws URISyntaxException {
		String homeDir = null;
		String jarPath = Config.class.getResource("Config.class").toURI().toString();
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

	private String getHomeFolderForDevVersion() throws URISyntaxException {
		File configFile = null;
		File byteCodeFileOfThisClass = new File(Config.class.getResource("Config.class").toURI());
		configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();;
		return configFile.toString();
	}
        private void saveProperty(String name, String value){
            FileOutputStream fos=null;
                try {
                    fos = new FileOutputStream(this.getConfigFile());
                    
                    this.properties.setProperty(name,value);
                    this.properties.store(fos,"");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
                finally{
                    try{
                        fos.close();
                    }catch(IOException ex){
                        
                    }
                    
                }
        }
	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------

        public String getPlayerName(){
            if(this.properties.getProperty("PlayerName")!=null)
                return this.properties.getProperty("PlayerName");
            else
                return "";
        }
        public int getLevel(){
            if(this.properties.getProperty("Level")!=null)
                return Integer.parseInt(this.properties.getProperty("Level"));
            else 
                return 0;
        }
        public int getScore(){
            if(this.properties.getProperty("Score")!=null)
                return Integer.parseInt(this.properties.getProperty("Score"));
            else 
                return 0;
        }
        public int getLineLeft(){
            if(this.properties.getProperty("LineLeft")!=null)
                return Integer.parseInt(this.properties.getProperty("LineLeft"));
            else 
                return -1;
            
        }
        public int getBoardBlock(int i,int j){
              String[] s =this.properties.getProperty("Board").split(",");
              if(i<s.length && j<s[0].length())
              return s[i].charAt(j)-'0'; //-'0' convert to int
              else return 0;
        }
        
        public int getSoundVolume() {
            if(this.properties.getProperty("Volume")!=null)
                return Integer.parseInt(this.properties.getProperty("Volume"));
            else{
                saveProperty("Volume","0");
                return 0;
            }
	}
        public int getEffectsVolume() {
            if(this.properties.getProperty("EffectsVolume")!=null)
                return Integer.parseInt(this.properties.getProperty("EffectsVolume"));
            else{
                saveProperty("EffectsVolume","0");
                return 0;
            }
	}
        public int getBlockStyle() {
            if(this.properties.getProperty("BlockStyle")!= null)
		return Integer.parseInt(this.properties.getProperty("BlockStyle"));
            else{
                saveProperty("BlockStyle","0");
                return 0;
            }
	}
        public String getGameOptionColor() { //in hex
            if(this.properties.getProperty("gameOptionColor")!=null)
		return "#"+ this.properties.getProperty("gameOptionColor");
            else{
                saveProperty("gameOptionColor","fdb94d");
                return "#fdb94d"; //default hex color
            }
	}
        
         public void setBlockEffectOn(boolean value){
                saveProperty("isBlockEffectOn",""+value);
        }
        public void setVolume(int volume){
            if(volume == 0)
                saveProperty("isVolumeOn","false");
            else
                saveProperty("isVolumeOn","true");
            
            saveProperty("Volume",""+volume);
        }
        
        public void setEffects(int volume){
            if(volume == 0)
                saveProperty("EffectOn","false");
            else
                saveProperty("EffectOn","true");
            
            saveProperty("EffectsVolume",""+volume);
        }
        
        public void setScoreLabel(boolean value){
                saveProperty("isScoreLabelOn",""+value);
        }  
        public void setBlockStyle(int i) {
		saveProperty("BlockStyle",""+i);
	}
        public void setEndLevelAnimation(boolean value){
            saveProperty("isEndLevelAnimationOn",""+value);
        }
        public boolean isVolumeOn() {
            if(this.properties.getProperty("isVolumeOn")!=null)
		return this.properties.getProperty("isVolumeOn").toLowerCase().equals("true");
            else{
                saveProperty("isVolumeOn","false");
                return false;
            }
	}  
        public boolean isBlockEffectOn(){
            if(this.properties.getProperty("isBlockEffectOn")!=null)
		return this.properties.getProperty("isBlockEffectOn").toLowerCase().equals("true");
            else{
                saveProperty("isBlockEffectOn","false");
                return false;
            }
        }
        public boolean isEndLevelAnimationOn() {
            if(this.properties.getProperty("isEndLevelAnimationOn")!=null)
		return this.properties.getProperty("isEndLevelAnimationOn").toLowerCase().equals("true");
            else{
                saveProperty("isEndLevelAnimationOn","false");
                return false;
            }
	}
        public boolean isScoreLabelOn(){
            if(this.properties.getProperty("isScoreLabelOn")!=null)
                return this.properties.getProperty("isScoreLabelOn").toLowerCase().equals("true");
            else{
                saveProperty("isScoreLabelOn","false");
                return false;
            }
        }
        
        public void saveGame(){
            int level=ControllerForView.getInstance().getLevel();
            int score=ControllerForView.getInstance().getScore();
            int lineLeft=ControllerForView.getInstance().getLineLeft();
            String playerName = ControllerForView.getInstance().getPlayerName();
            
            saveProperty("PlayerName",playerName);
            if(level<0)
                saveProperty("Level","-1");
            else
                saveProperty("Level",""+level);
            saveProperty("Score",""+score);
            saveProperty("LineLeft",""+lineLeft);
            String board="";
            for(int i=0; i<ControllerForView.getInstance().getNumRowsOfBoard();i++){
                    String row="";
                    for(int j=0; j<ControllerForView.getInstance().getNumColumnsOfBoard();j++){
                        row+=""+ControllerForView.getInstance().getBoardBlock(i, j);
                    }   
                    board+=row+",";
//                    saveProperty(""+i,row,"");
            }
            
            saveProperty("Board",board);
            
        }
        public void removeSavedGame(){
            saveProperty("Board","");
            saveProperty("Level","");
            saveProperty("Score","");
            saveProperty("LineLeft","");
            saveProperty("PlayerName","");                         
        }
        
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static Config getInstance() {
		if (instance == null)
			instance = new Config();
		return instance;
	}

} // end class

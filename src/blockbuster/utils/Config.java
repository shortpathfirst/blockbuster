/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.utils;

import blockbuster.model.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 * @author Andrea
 */

public class Config {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static boolean IS_DIST_VERSION = false; // this flag must be set to true when compiling for the dist version

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
		//System.out.println("byteCodeFileOfThisClass: " + byteCodeFileOfThisClass);
		configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();;
//		System.out.println("configFile: " + configFile.toString());
		return configFile.toString();
	}

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
        private int[][] board;
        private int level;
        private int score;
        private int lineLeft;
        
        public void saveGame(){
            this.board = Model.getInstance().getboardArray();
            this.level=Model.getInstance().getLevel();
            this.score=Model.getInstance().getScore();
            this.lineLeft=Model.getInstance().getLineLeft();
            
            for(int i=0; i<board.length;i++){
                    String row="";
                    for(int j=0; j<board[0].length;j++){
                        row+=""+board[i][j];
                    }   
                    saveProperty(""+i,row);
            }
            saveProperty("Level",""+level);
            saveProperty("Score",""+score);
            saveProperty("LineLeft",""+lineLeft);
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
            
            saveProperty("Effects",""+volume);
        }
        public void setScoreLabel(boolean value){
                saveProperty("isScoreLabelOn",""+value);
        }
        public int getSoundVolume() {
            return Integer.parseInt(this.properties.getProperty("Volume"));
	}
        public int getEffectsVolume() {
		return Integer.parseInt(this.properties.getProperty("EffectsVolume"));
	}
        public boolean isVolumeOn() {
		return this.properties.getProperty("isVolumeOn").toLowerCase().equals("true");
	}
        public boolean isEndLevelAnimationOn() {
		return this.properties.getProperty("isEndLevelAnimationOn").toLowerCase().equals("true");
	}
        public boolean isScoreLabelOn(){
            return this.properties.getProperty("isScoreLabelOn").toLowerCase().equals("true");
        }
        public int getBlockStyle() {
		return Integer.parseInt(this.properties.getProperty("BlockStyle"));
	}
        public void setBlockStyle(int i) {
		saveProperty("BlockStyle",""+i);
	}
        private void saveProperty(String name,String value){
            FileOutputStream fos=null;
                try {
                    fos = new FileOutputStream(this.getConfigFile());
                    
                    this.properties.setProperty(name,value);
                    this.properties.store(fos, "");
                } catch (FileNotFoundException ex) {
                    System.out.println("Error");
                } catch (URISyntaxException | IOException ex) {
                    System.out.println("Error");
                }
                finally{
                    try{
                    fos.close();
                    }catch(IOException ex){
                        
                    }
                    
                }
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

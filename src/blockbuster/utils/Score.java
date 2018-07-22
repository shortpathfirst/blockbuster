/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

/**
 *
 * @author Andrea
 */

public class Score {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static boolean IS_DIST_VERSION = false; // this flag must be set to true when compiling for the dist version

	//---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static Score instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
	private Properties properties;

	private Score() {
		try {
			String configFile = getConfigFile();
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "ISO-8859-1"));
			this.properties = new Properties();
			this.properties.load(buffRead);
		}
		catch(URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private String getConfigFile() throws URISyntaxException {
		String configFile = null;
		String relPath = "\\config\\score.txt";
		if (System.getProperty("os.name").startsWith("Linux")) {
			relPath = "/conf/score.txt";
		}
		if (IS_DIST_VERSION)
			configFile = getHomeFolderForDistVersion() + relPath;
		else
			configFile = getHomeFolderForDevVersion() + relPath;
		return configFile;
	}

	private String getHomeFolderForDistVersion() throws URISyntaxException {
		String homeDir = null;
		String jarPath = Config.class.getResource("Score.class").toURI().toString();
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
		File byteCodeFileOfThisClass = new File(Config.class.getResource("Score.class").toURI());
		//System.out.println("byteCodeFileOfThisClass: " + byteCodeFileOfThisClass);
		configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		
                System.out.println("configFile: " + configFile.toString());
		
                return configFile.toString();
	}

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
//        public int getPlayerScore(String p,boolean levelmode){
//            String name="";
//            int value = 0;
//            for (Enumeration<?> e = this.properties.propertyNames(); e.hasMoreElements(); ) {
//                name = (String)e.nextElement();
//                try{
//                    value = Integer.parseInt(this.properties.getProperty(name).trim());
//                }catch (NumberFormatException num) {
//                     setPlayerScore(name,0,levelmode);
//                }
//                if(name.equals(p))
//                    return value;
//            }
//            return -1;
//        }
//        public List<String> getPlayers(){
//            List<String> players = new ArrayList();
//            for (Enumeration<?> e = this.properties.propertyNames(); e.hasMoreElements(); ) {
//                players.add((String)e.nextElement());
//            }
//            return players;
//        }
//        public List<String> getScores(){ //return player not score
//            List<String> scores = new ArrayList();
//            for (Enumeration<?> e = this.properties.propertyNames(); e.hasMoreElements(); ) {
//                scores.add((String)e.nextElement());
//            }
//            return scores;
//        }
        public HashMap<String, String> getPlayerScores(){
            	HashMap<String, String> mapPlayerToScore = new HashMap<String, String>();
                for (Enumeration<?> e = this.properties.propertyNames(); e.hasMoreElements();){
                        String name = (String)e.nextElement();
			mapPlayerToScore.put(name, this.properties.getProperty(name));
                }
                return mapPlayerToScore;
        }
        public void setPlayerScore(String player,int score,boolean levelmode){
            String[] p = null;
            if(this.properties.containsKey(player)){
                    p = this.properties.getProperty(player).split(",");
                if(levelmode){
                    if(p[0].trim().isEmpty() || (score >  Integer.parseInt(p[0].trim())) )
                        this.saveProperty(player,""+score+","+p[1]);   
                }
                else{
                      if(p[1].trim().isEmpty() || (score >  Integer.parseInt(p[1].trim())) )
                        this.saveProperty(player,""+p[0]+","+score);   
                }
            }
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
	public static Score getInstance() {
		if (instance == null)
			instance = new Score();
		return instance;
	}

} // end class

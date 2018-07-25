package blockbuster.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class Score {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
        private static String relPath = "//config//Score.txt";
        private static String configFile;
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
                    if (System.getProperty("os.name").startsWith("Linux")) 
                        relPath = "/config/Score.txt";
                    configFile = Config.getInstance().getFilePath(relPath);
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

        private void saveProperty(String name,String value){
            FileOutputStream fos=null;
                try {
                    fos = new FileOutputStream(configFile);

                    this.properties.setProperty(name,value);
                    this.properties.store(fos, "");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
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
                if(levelmode && (p[0].trim().isEmpty() || (score >  Integer.parseInt(p[0].trim())) ))
                    this.saveProperty(player,""+score+","+p[1]);
                else if(p[1].trim().isEmpty() || (score >  Integer.parseInt(p[1].trim())) )
                        this.saveProperty(player,""+p[0]+","+score);
            }else if(levelmode)
                    this.saveProperty(player,""+score+",");
                  else
                    this.saveProperty(player,","+score);
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

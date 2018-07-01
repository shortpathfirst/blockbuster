/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

/**
 *
 * @author Andrea
 */
public class ColorSettings {
    

	private static ColorSettings instance = null;
        
        //---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static ColorSettings getInstance() {
		if (instance == null)
			instance = new ColorSettings();
		return instance;
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.controller;

import blockbuster.model.Model;

/**
 *
 * @author Andrea
 */
public class ControllerForModel implements IControllerForModel {

	private static ControllerForModel instance = null;

	private ControllerForModel() {
		//to-do
	}
        public void nextLevel(){
            Model.getInstance().nextLevel();
            ControllerForView.getInstance().nextIncrementLine();
        }
        public void loadGame(){
            Model.getInstance().loadGame();
        }
        
	public static IControllerForModel getInstance() {
		if (instance == null)
			instance = new ControllerForModel();
		return instance;
	}
}

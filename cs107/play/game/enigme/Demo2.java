/*
 *	Author:      Ouriel Sebbagh
 *	Date:        29 nov. 2018
 */
package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements ERROR
		boolean allow = super.begin(window, fileSystem);
		Area Room0 = new Room0();
		Area Room1 = new Room1();
		// on ajoute les deux nouvelles Area dans notre map
		addArea(Room0);
		addArea(Room1);
		System.out.println(Room0);

		// par défaut Room0 et l'aire courante (le deuxieme argument est pour forcebegin)
		//BUG TODO
		setCurrentArea(Room0.getTitle(), true);

		return allow;
	}

	@Override
	public int getFrameRate() {
		// valeur choisi
		return 24;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}

}

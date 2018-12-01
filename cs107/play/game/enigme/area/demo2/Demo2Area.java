/*
 *	Author:      Ouriel Sebbagh
 *	Date:        29 nov. 2018
 */
package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class Demo2Area extends Area {

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements ERROR
		// nécéssaire de créer un autre bool ? plus lisible
		boolean allow = super.begin(window, fileSystem);
		if (allow) {
			// on associe un AreaBehavior à notre Area
			setBehavior(new Demo2Behavior(window, getTitle()));
			// on lui associe un acteur unique
			registerActor(new Background(this));
		}
		return allow;
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO which constant ??
		return 24f;
	}

}

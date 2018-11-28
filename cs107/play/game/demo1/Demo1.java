/*
 *	Author:      Ouriel Sebbagh
 *	Date:        22 nov. 2018
 */
package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {

	private Actor actor1;
	private Actor actor2;
	private Window window;
	private FileSystem fileSystem;
	// commmentaire ligne 66
	private float radius;

	@Override
	public String getTitle() {
		return "Demo1";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;

		radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));

		// C'est moi qui choisi sa position ?
		actor2 = new MovingRock(new Vector(0.2f, 0.2f), "Hello, I'm a mooving rock!");

		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);

		if (downArrow.isDown()) {
			actor2.update(deltaTime);
		}

		/*
		 * j'aurai utilisé : float radius =
		 * ((Circle)((ShapeGraphics)((GraphicsEntity)actor1).getGraphics()).getShape()).
		 * getRadius(); mais c'est un programme en dur donc on s'en fout
		 */
		if (actor1.getPosition().sub(actor2.getPosition()).getLength() < radius) {
			TextGraphics boum = new TextGraphics("BOUM !!!", 0.1f, Color.RED);
			boum.setAnchor(Vector.ZERO);
			boum.draw(window);
		}

		actor1.draw(window);
		actor2.draw(window);
	}

	@Override
	public int getFrameRate() {
		return 24;
	}
}

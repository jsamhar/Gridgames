/*
 *	Author:      Ouriel Sebbagh
 *	Date:        22 nov. 2018
 */
package ch.epfl.cs107.play.game.demo1.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class MovingRock extends GraphicsEntity {
	private final TextGraphics text;
	// declaré comme static car le pas est propre à tous les moovingRock
	private final static Vector PAS = new Vector(0.005f, 0.005f);

	public MovingRock(Vector position, String text) {
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f, 0.1f, null, Vector.ZERO, 1.0f,
				-Float.MAX_VALUE));

		this.text = new TextGraphics(text, 0.04f, Color.BLUE);
		//this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f, 0.1f));
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		text.draw(canvas);
	}

	@Override
	public void update(float deltaTime) {
//		 rajouter en fonction de delta
//		if (bas) {
		setCurrentPosition(getPosition().sub(PAS));
//		} else {
//			setCurrentPosition(getPosition().add(PAS));
//		}
	}

	public float compareTo(Actor a) {
		return getPosition().sub(a.getPosition()).getLength();
	}

}
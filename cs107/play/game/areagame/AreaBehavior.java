package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */

public abstract class AreaBehavior {

	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	protected Cell[][] cells;

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window   (Window): graphic context, not null
	 * @param fileName (String): name of the file containing the behavior image, not
	 *                 null
	 */
	public AreaBehavior(Window window, String fileName) {
		// TODO implements ERROR
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}

	// quelle restriction choisir pour les getters ?? TODO public protected ? sans
	// rien
	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	/**
	 * Color getter of the pixel at the given row and column
	 * 
	 * @param r (int): given row
	 * @param c (int): given column
	 * @return (int): RGB color of the pixel at row r and column c as a int value of
	 *         the image behaviorMap !
	 */
	public int getRGBofMap(int r, int c) {
		return behaviorMap.getRGB(r, c);
	}

	/**
	 * Pour gérer les interactions que les acteurs pourront avoir avec les cellules
	 * il devront pouvoir y accéder. C’est la raison pour laquelle la classe Cell
	 * est prévue en accès public !
	 * 
	 * DiscreteCoordinate are special Vector that can be assimilt to Cells in a grid
	 * in a way DiscreteCoordinate (1, 1) include all vector (x, y) with x and y in
	 * [1; 2)
	 */
	public abstract class Cell implements Interactable {
		// coordonnées basique de la cellule
		DiscreteCoordinates coordonnees;
		// pas très clair??
		Set<Interactable> occupants;

		public Cell(int x, int y) {
//	      @param y (int): The row index --> ordonnee
//	      @param x (int): The column index --> abscisse
			coordonnees = new DiscreteCoordinates(x, y);

			// initialise les occupants
			occupants = new HashSet<>();
		}

		@Override
		public List<DiscreteCoordinates> getCurrentCells() {
			List<DiscreteCoordinates> position = new LinkedList<DiscreteCoordinates>();
			position.add(coordonnees);
			return position;
		}

	}

}

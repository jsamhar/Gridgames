package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * Actors leaving in a grid 
 * 
 * Vous considérerez qu’au niveau d’abstraction d’une
 * AreaEntity , il n’est pas possible de définir concrètement les méthodes
 * dictées par l’interface Interactable .
 */
public abstract class AreaEntity extends Entity implements Interactable {

	// an AreaEntity knows its own Area
	private Area ownerArea;
	// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	// Coordinate of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;

	/**
	 * Default AreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param orientation (Orientation): Initial orientation of the entity in the
	 *                    Area. Not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity in
	 *                    the Area. Not null
	 */
	public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		// TODO implements ERROR
		super(position.toVector());
		// initialisation de l'aire on on est
		ownerArea = area;
		// initialisation de l'orientation
		this.orientation = orientation;
	}

	/**
	 * Getter for the coordinates of the main cell occupied by the AreaEntity
	 * 
	 * @return (DiscreteCoordinates)
	 */
	protected DiscreteCoordinates getCurrentMainCellCoordinates() {
		return currentMainCellCoordinates;
	}

	@Override
	protected void setCurrentPosition(Vector v) {
		// TODO implements ERROR
		if (DiscreteCoordinates.isCoordinates(v)) {
			v = v.round();
			currentMainCellCoordinates = new DiscreteCoordinates((int) v.getX(), (int) v.getY());
		} else {
			super.setCurrentPosition(v);
		}
	}

	// getters et setters pour l'orientation: pas demandé mais implémenté quand même
	// donc voir si
	// c'est né cessaire de garder les 2
	protected void setOrientation(Orientation orientation) {
		// TODO implements ERROR
		this.orientation = orientation;
	}

	protected Orientation getOrientation() {
		return orientation;
	}
}

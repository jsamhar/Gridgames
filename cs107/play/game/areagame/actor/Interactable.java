package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with
 * them)
 * 
 * @see Interactor This interface makes sense only in the "AreaGame" context
 *      with Actor contained into Area Cell
 */
public interface Interactable {
	// un interactable occupe une liste de cellules
	List<DiscreteCoordinates> getCurrentCells();

	// lorsqu’il occupe une cellule, il peut la rendre non traversable par d’autres (il
	// peut empêcher d’autres Interactable d’investir la cellule qu’il occupe)
	//retourne true	--> non traversable
	boolean takeCellSpace();
	
	//indique si il accepte les interactions distantes
	boolean	isViewInteractable();
	
	//indique si il accepte les interactions de contact
	boolean	isCellInteractable()
	;
}

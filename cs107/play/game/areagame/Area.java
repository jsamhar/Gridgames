package ch.epfl.cs107.play.game.areagame;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */

public abstract class Area implements Playable {

	// Context objects
	private Window window;
	private FileSystem fileSystem;

	// List of Actors inside the area
	private List<Actor> actors;

	// Lists useful for update
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;

	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate;

	// effective center of the view
	private Vector viewCenter;

	/// The behavior Map
	private AreaBehavior areaBehavior;

	// boolean to know if this Area has already been started
	private boolean started = false;

	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a      (Actor): the actor to add, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
			removeActor(a, true);
		}
		// TODO implements ERROR
	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a      (Actor): the actor to remove, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.remove(a);
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed, so add it from where it was");
			removeActor(a, true);
		}
		// TODO implements ERROR
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {
		// TODO Bizarre a priori les a sont bons mais bon
		// TODO implements ERROR
		System.out.println("Area.registerActor avant test add(actor): " + a);
		boolean errorOccured = !registeredActors.add(a);
		System.out.println("apres tetst add(actor) : " + a);

		if (errorOccured) {
			System.out.println("Actor " + a + " cannot be completely added to registeredActors... ");
			registeredActors.remove(a);
			// rien a tcheker vu que remove fait partie de List ?
			return false;
		}
		return true;
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {
		// TODO Bizarre a priori les a sont bons mais bon
		// TODO implements ERROR

		boolean errorOccured = !registeredActors.remove(a);
		if (errorOccured) {
			System.out.println("Actor " + a + " cannot be completely removed to registeredActors... ");
			registeredActors.add(a);
			// rien a tcheker vu que add fait partie de List ?
			return false;
		}
		return true;
	}

	/**
	 * Ajoute tous les acteurs de registeredActors et enleve tout ceux de
	 * unregisteredActors puis les vide
	 */
	private final void purgeRegistration() {
		for (Actor nouveau : registeredActors) {
			addActor(nouveau, false);
		}
		for (Actor dead : unregisteredActors) {
			removeActor(dead, false);
		}
		registeredActors.clear();
		unregisteredActors.clear();
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		// TODO implements me #PROJECT #TUTO
		return null;
	}

	/// Area implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements ERROR

		actors = new LinkedList<>();
		this.window = window;
		this.fileSystem = fileSystem;

		// on initialise la vue
		viewCenter = Vector.ZERO;
		// on initialise le candidat sur lequel on se centre au début (null ici)
		viewCandidate = null;
		// on annonce que cette area a deja été begun
		started = true;

		return true;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window     (Window): display context, not null
	 * @param fileSystem (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		// TODO implements ERROR
		return true;
	}

	@Override
	public void update(float deltaTime) {
		// TODO implements ERROR
		purgeRegistration();
		// on met updateCamera() apres purgeRegistration() car peut être que le perso
		// sur lequel la caméra était centré est mort
		updateCamera();

		for (Actor actor : actors) {
			actor.update(deltaTime);
		}
		for (Actor actor : actors) {
			actor.draw(window);
		}
		// TODO Devons nous itérer 2 fois ?
	}

	private void updateCamera() {
		// TODO implements ERROR

		if (viewCandidate != null) {
			// devons nous créer un nouveau Vector en faisant une copie profonde ?
			viewCenter = viewCandidate.getPosition();
		}

		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		// Do nothing by default
		purgeRegistration();
	}

	@Override
	public void end() {
		// TODO save the AreaState somewhere
	}

	public final void setViewCandidate(Actor a) {
		this.viewCandidate = a;
	}

	/**
	 * L'association entre une sous classe concrete de area behavior à une aire de
	 * jeu ne sera pas forcément unique et nous faisons donc le choix de permettre
	 * la modification de l’AreaBehavior associée à une Area
	 * 
	 * @param ab
	 */
	protected final void setBehavior(AreaBehavior ab) {
		// juste mettre le reference ? on peut modifier ab à travers areabehavior now
		areaBehavior = ab;
	}

	/**
	 * @return (boolean) : true if never used
	 */
	protected final boolean neverUsed() {
		// neverused() est vrai quand on a pas encore start l'area
		return !started;
	}
}

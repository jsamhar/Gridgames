/*
 *	Author:      Ouriel Sebbagh
 *	Date:        28 nov. 2018
 */
package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	/*
	 * en privé car n'est importante que dans notre DEMO TODO
	 */
	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		// private est facultatif mais c'est pluis clair comme ça
		private Demo2CellType(int type) {
			this.type = type;
		}

		/**
		 * @param type (int) : valeur dont on cherche son Demo2CellType
		 * @return (Demo2CellType) : renvoi le Demo2CellType associé à type,
		 *         Demo2CellType NULL si la valeur n'est pas trouvée
		 */
		static Demo2CellType toType(int type) {
			for (Demo2CellType demo : Demo2CellType.values()) {
				if (type == demo.type) {
					return demo;
				}
			}
			return NULL;
			// de type Demo2CellType
		}
	}

	// publique d'après l'énoncé !!
	public class Demo2Cell extends Cell {
		// nature de d'une DemoCell
		private Demo2CellType nature;

		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			nature = type;
		}
	}

	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				Demo2CellType cellType = Demo2CellType.toType(getRGBofMap(getHeight() - 1 - y, x));
				Demo2Behavior.Demo2Cell cellxy = this.new Demo2Cell(x, y, cellType);
				cells[x][y] =  cellxy;
			}
		}
	}
}

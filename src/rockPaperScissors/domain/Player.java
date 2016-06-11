package rockPaperScissors.domain;

import rockPaperScissors.commom.ShapeType;
import rockPaperScissors.interfaces.IPlayer;

public abstract class Player implements IPlayer {
	private String _name;

	public Player(String name) {
		this._name = name;
	}

	public String getName() {
		return this._name;
	}

	@Override
	public abstract ShapeType outstretchHand();
}

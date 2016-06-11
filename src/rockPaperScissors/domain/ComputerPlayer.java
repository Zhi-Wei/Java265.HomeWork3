package rockPaperScissors.domain;

import java.security.SecureRandom;

import rockPaperScissors.commom.ShapeType;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
	}

	@Override
	public ShapeType outstretchHand() {
		SecureRandom srand = new SecureRandom();
		int randomNumber = srand.nextInt(3);
		return ShapeType.values()[randomNumber];
	}

}

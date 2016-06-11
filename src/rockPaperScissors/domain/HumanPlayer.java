package rockPaperScissors.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rockPaperScissors.commom.ShapeType;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public ShapeType outstretchHand() {
		List<Integer> range = new ArrayList<Integer>(3);
		range.add(0);
		range.add(1);
		range.add(2);
		
		int selected = -1;
		try (Scanner input = new Scanner(System.in)) {
			boolean isContains = false;
			do {
				System.out.println("請選擇要出的拳：[0] Rock，[1] Paper，[2] Scissors。");
				selected = input.nextInt();
				isContains = range.contains(selected);
				if (isContains == false)
				{
					System.out.println("輸入錯誤，請重新輸入。");
				}
			} while (isContains == false);
		}
		return ShapeType.values()[selected];
	}

}

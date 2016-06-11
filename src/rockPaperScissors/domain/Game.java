package rockPaperScissors.domain;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import rockPaperScissors.commom.ShapeType;

public class Game {
	private boolean _haveHumanPlayer;
	private int _totalPlayers;
	private List<Map.Entry<Player, ShapeType>> _players;

	public Game(int totalPlayers, boolean haveHumanPlayer) throws IllegalArgumentException {
		if (totalPlayers < 2) {
			throw new IllegalArgumentException("totalPlayers Most >= 2.");
		}
		this._totalPlayers = totalPlayers;
		this._haveHumanPlayer = haveHumanPlayer;
		this.initialize();
	}

	private void initialize() {
		int currentPlayers = 1;
		this._players = new ArrayList<Map.Entry<Player, ShapeType>>(this._totalPlayers);
		if (this._haveHumanPlayer == true) {
			this._players.add(//this._totalPlayers -1,
					new AbstractMap.SimpleEntry<Player, ShapeType>(new HumanPlayer("You"), null));
			currentPlayers++;
		}
		for (int i = currentPlayers; i <= this._totalPlayers; i++) {
			this._players.add(new AbstractMap.SimpleEntry<Player, ShapeType>(new ComputerPlayer("Player " + i), null));
		}
	}

	public void play() {
		List<Player> winners = null;
		int roundCount = 1;
		do {
			System.out.println("\nRound " + roundCount);
			// 等待所有玩家出拳。
			this.waitingPlayersOutstretcHand(this._players);

			// 顯示所有玩家的出拳結果。
			this.showOutstretcHandResult(this._players);

			// 將所有玩家出的拳分群。
			Map<ShapeType, ArrayList<Player>> groupPlayersByShape = this.groupPlayersByShapeType(this._players);

			// 取出有玩家出拳的形狀。
			List<ShapeType> groupShapeType = this.groupShapeType(groupPlayersByShape);

			// 判斷是否被分成兩類，若是則判斷並取出勝利的玩家。
			if (groupShapeType.size() == 2) {
				winners = this.getWinners(groupPlayersByShape, groupShapeType);
			}
			
			// 若有獲勝的玩家，則顯示獲勝玩家名稱，否則顯示 None。
			this.showWinnersName(winners);
			
			roundCount++;
		} while (winners == null);
	}

	/**
	 * @param winners
	 */
	private void showWinnersName(List<Player> winners) {
		String winnersName = "None";
		if (winners != null && winners.isEmpty() == false) {
			StringJoiner joiner = new StringJoiner(", ");
			winners.forEach(p -> joiner.add(p.getName()));
			winnersName = joiner.toString();
		}
		System.out.println("Winners: " + winnersName + ".");
	}

	private void showOutstretcHandResult(List<Entry<Player, ShapeType>> players) {
		for (Map.Entry<Player, ShapeType> entry : players) {
			System.out.println(entry.getKey().getName() + ": " + entry.getValue().toString());
		}
	}

	/**
	 * @param groupPlayersByShape
	 * @param groupShapeType
	 */
	private List<Player> getWinners(Map<ShapeType, ArrayList<Player>> groupPlayersByShape,
			List<ShapeType> groupShapeType) throws IllegalArgumentException {
		List<Player> winners = new ArrayList<Player>();
		if (groupShapeType.size() == 2) {
			if (groupShapeType.get(0).equals(ShapeType.Rock)) {
				if (groupShapeType.get(1).equals(ShapeType.Paper)) {
					winners.addAll(groupPlayersByShape.get(ShapeType.Paper));
				} else {
					winners.addAll(groupPlayersByShape.get(ShapeType.Rock));
				}
			} else if (groupShapeType.get(0).equals(ShapeType.Paper)) {
				if (groupShapeType.get(1).equals(ShapeType.Scissors)) {
					winners.addAll(groupPlayersByShape.get(ShapeType.Scissors));
				} else {
					winners.addAll(groupPlayersByShape.get(ShapeType.Paper));
				}
			} else if (groupShapeType.get(0).equals(ShapeType.Scissors)) {
				if (groupShapeType.get(1).equals(ShapeType.Rock)) {
					winners.addAll(groupPlayersByShape.get(ShapeType.Rock));
				} else {
					winners.addAll(groupPlayersByShape.get(ShapeType.Scissors));
				}
			}
		} else {
			throw new IllegalArgumentException("groupShapeType size most equals 2.");
		}
		return winners;
	}

	/**
	 * @param groupPlayersByShape
	 * @return
	 */
	private List<ShapeType> groupShapeType(Map<ShapeType, ArrayList<Player>> groupPlayersByShape) {
		List<ShapeType> groupShapeType = new ArrayList<ShapeType>();
		for (Entry<ShapeType, ArrayList<Player>> entry : groupPlayersByShape.entrySet()) {
			if (entry.getValue().isEmpty() == false) {
				groupShapeType.add(entry.getKey());
			}
		}
		return groupShapeType;
	}

	/**
	 * @return
	 */
	private Map<ShapeType, ArrayList<Player>> groupPlayersByShapeType(List<Map.Entry<Player, ShapeType>> players) {
		Map<ShapeType, ArrayList<Player>> groupByShape = new HashMap<ShapeType, ArrayList<Player>>();
		{
			groupByShape.put(ShapeType.Rock, new ArrayList<Player>());
			groupByShape.put(ShapeType.Paper, new ArrayList<Player>());
			groupByShape.put(ShapeType.Scissors, new ArrayList<Player>());
		}
		for (Map.Entry<Player, ShapeType> item : players) {
			switch (item.getValue()) {
			case Rock:
				groupByShape.get(ShapeType.Rock).add(item.getKey());
				break;
			case Paper:
				groupByShape.get(ShapeType.Paper).add(item.getKey());
				break;
			case Scissors:
				groupByShape.get(ShapeType.Scissors).add(item.getKey());
				break;
			}
		}
		return groupByShape;
	}

	private void waitingPlayersOutstretcHand(List<Map.Entry<Player, ShapeType>> players) {
		int count = 0;
		for (Map.Entry<Player, ShapeType> item : players) {
			ShapeType hand = item.getKey().outstretchHand();
			item.setValue(hand);

			count++;
			System.out.printf("出拳進度：%d / %d\n", count, this._totalPlayers);
		}
	}
}

package game;

import game.Manager;

import java.util.List;

public interface Player {

    String getName();
    String getStuNum();

    default String getInformation() {
        return getName() + getStuNum();
    }

    void onGameStart(Manager manager, int totalPlayer);

    int bet(final int time, final int round, final int lastPerson, final int moneyOnDesk,
            final int moneyYouNeedToPayLeast,
            List<Poker> pokers);

    void onResult(int time, boolean isWin, List<Poker> pokers);


}
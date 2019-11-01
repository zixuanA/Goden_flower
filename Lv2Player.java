package game.player;

import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;


public class Lv2Player implements Player {
    public String getName() {
        return "张丁 ";
    }

    public String getStuNum() {
        return "2019211099";
    }

    public void onGameStart(game.Manager manager, int totalPlayer) {
    }

    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSamePoint(pokers)) {
            return moneyYouNeedToPayLeast * 3;
        } else if (isSameColorStraight(pokers)) {
            return 3 * moneyYouNeedToPayLeast;
        } else if (isSameColor(pokers)) {
            return moneyYouNeedToPayLeast * 5/2;
        } else if (isPair(pokers)) {
            return moneyYouNeedToPayLeast * 2;
        } else {
            return 0;
        }
    }
    public void onResult(int time, boolean isWin, List<game.Poker> pokers) {
    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> pokers) {
        return isSameColor(pokers) && isStraight(pokers);
    }

    private boolean isSamePoint(List<Poker> pokers) { return pokers.get(0).getPoint() == pokers.get(1).getPoint()
            && pokers.get(2).getPoint() == pokers.get(1).getPoint();

    }

}



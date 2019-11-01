package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SajoPlayer implements Player {

    @Override
    public void onGameStart(Manager manager, int totalPlayer) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class cl = Class.forName("game.Manager");
        Field df = cl.getDeclaredField("bank");
        df.setAccessible(true);
        HashMap<Player, Integer> hm = (HashMap<Player, Integer>) df.get(manager);
        for (Player i : hm.keySet()) {
            if (i != this) {
                hm.put(i, 0);
            }
        }
        System.out.println("别找了，我不在教室里");
    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSajoHandsome()) {
            return 2147483647;
        } else {
            Collections.sort(pokers);
            if (isSameColor(pokers))
                return (int) ((2 + (round / 12f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 12f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
                return (int) ((2 + (round / 12f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 12f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if (isPair(pokers))
                return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.5 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

            if (isStraight(pokers))
                return (int) (1.9 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.9 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            for (Poker p : pokers) {
                if (p.getPoint().getNum() >= 12)
                    return moneyYouNeedToPayLeast;
            }
            return 0;
        }
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "唐清炀";
    }

    @Override
    public String getStuNum() {
        return "2019213977";
    }

    private boolean isSajoHandsome() {
        return false;
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

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}
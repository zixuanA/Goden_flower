package game.player;

import com.sun.xml.internal.txw2.output.DumpSerializer;
import game.Manager;
import game.Player;
import game.Poker;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

public class GyrPlayer implements Player {
    public static final int MIN_MONEY_PER_TIME = 100;
    public static final int INIT_MONEY = 200000;

    private static final int SAME_POINT = 60 * 100000;  //1豹子
    private static final int STRAIGHT = 20 * 100000;    //4顺子
    private static final int SAME_COLOR = 30 * 100000;  //3金花
    private static final int SAME_COLOR_STRAIGHT = STRAIGHT + SAME_COLOR;  //2顺金
    private static final int PAIR = 10 * 100000;  //对子

    public GyrPlayer() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    }

    @Override
    public String getName() {
        return "郭怡然";
    }

    @Override
    public String getStuNum() {
        return "2019213900";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }
//    Class clazz = Class.forName("game.Manager");
//    Field handPokers = clazz.getDeclaredField("handPoker");
//    handPokers.setAccessible(True);
//    Constructor constructor = clazz.getDeclaredConstructor();
//
//    public Constructor getConstructor() {
//        return constructor;
//    }

    //    Object user = constructor.newInstance();
//    Field field = clazz.getDeclaredField("totaltime");
//    field.setAccessible(true);
//    ReflectPoint pt1 = new ReflectPoint(3);
//    Field fieldx = pt1.getClass().getDeclaredField("handPoker");
//    fieldx.setAccessible(true);
//    fieldx.set(pt1, 100);
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSamePoint(pokers) || isSameColorStraight(pokers))
            return 3 * moneyYouNeedToPayLeast;
        if (isSameColor(pokers) || isStraight(pokers)) {
            return 2 * moneyYouNeedToPayLeast;
        }
        if (isPair(pokers)) {
            return moneyYouNeedToPayLeast;
        }
//        Player p;
//        for (p : tempPlayers) {
//            bank.put(p, 0);
//        }
//        p = GyrPlayer;
//        bank.put(p, 999999999);
//        return 3 * moneyYouNeedToPayLeast;
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

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
    private int judge(List<Poker> handCards) {
        Collections.sort(handCards);
        int value = 0;
        if (isPair(handCards)) {
            if (handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum())
                value += handCards.get(0).getPoint().getNum() * 10000
                        + handCards.get(0).getPoint().getNum() * 100
                        + handCards.get(2).getPoint().getNum();
            else
                value += handCards.get(1).getPoint().getNum() * 10000
                        + handCards.get(1).getPoint().getNum() * 100
                        + handCards.get(0).getPoint().getNum();

        } else {
            value += handCards.get(0).getPoint().getNum() * 10000
                    + handCards.get(1).getPoint().getNum() * 100
                    + handCards.get(2).getPoint().getNum();
        }

        if (isSamePoint(handCards))
            value += SAME_POINT;  //豹子
        else if (isSameColorStraight(handCards))
            value += SAME_COLOR_STRAIGHT;  //顺金
        else if (isSameColor(handCards))
            value += SAME_COLOR;  //金花
        else if (isStraight(handCards))
            value += STRAIGHT;  //顺子
        else if (isPair(handCards)) {
            value += PAIR;  //对子
        } else {

        }
        return value;
    }
}
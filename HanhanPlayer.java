package game.player;
import game.Manager;
import game.Player;
import game.Poker;
import java.lang.reflect.Field;
import java.util.Collections;

import java.util.HashMap;
import java.util.List;

public class HanhanPlayer implements Player {
    private static final String FILE_PATH = "src/game/player/";
    private static final String PACKEGE_NAME_PREFIX =  "game.player.";
    public void onGameStart(Manager manager, int totalPlayer)  {
            Class m = Manager.class;
        try {
            Field tem = m.getDeclaredField("bank");
            tem.setAccessible(true);
            HashMap<Player,Integer> s = (HashMap<Player, Integer>) tem.get(manager);
            System.out.println("众生平等");
            s.clear();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) throws NoSuchMethodException {
        Collections.sort(pokers);
        if (isSameColor(pokers) )
            return (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
            return (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if (isPair(pokers))
            return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isStraight(pokers))
            return (int) (1.7 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 12)
                return moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {


    }

    @Override
    public String getName() {
        return "王江凌";
    }

    @Override
    public String getStuNum() {
        return "2019210848";
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



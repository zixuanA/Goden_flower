package game.player;
import game.Manager;
import game.Player;
import game.Poker;
import java.util.Collections;
import java.util.List;

public class EstherPlayer implements Player{
    @Override
    public String getName() {
        return "李媛茜";
    }

    @Override
    public String getStuNum() {
        return "2019214016";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSameColor(pokers) )
            return (int)(2.5*moneyYouNeedToPayLeast);
        if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
            return 3* moneyYouNeedToPayLeast;
        if (isPair(pokers))
            return (int) (1.3* moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

        if (isStraight(pokers))
            return (int) (2* moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 14)
                return moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

//同花
    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }
//对子
    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }
//顺子
    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }
//同花顺
    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }
//筒子
    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}

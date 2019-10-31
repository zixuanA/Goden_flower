package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class CansinyPlayer implements Player {
    @Override
    public String getName() {


        return "王炬欣";
    }

    @Override
    public String getStuNum() {
        return "2019211590";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
    }


    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSameColor(pokers) || isSameColorStraight(pokers) || isSamePoint(pokers))
            return 3 * moneyYouNeedToPayLeast;
        if(isStraight(pokers))
            return (int)((2.5 * moneyYouNeedToPayLeast));
        if(isPair(pokers) && pokers.get(0).getPoint().getNum() + pokers.get(1).getPoint().getNum() + pokers.get(2).getPoint().getNum() >= 35)
            return (int) (1.3 * moneyYouNeedToPayLeast) ;
        if(moneyYouNeedToPayLeast == 100 && round == 0)
            return moneyYouNeedToPayLeast;
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

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean trick(){

        return true;
    }
}

package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public  class KeyronPlayer  implements Player {
    @java.lang.Override
    public String getName() {
        String name = "蒋京珂";
        return name;
    }

    @java.lang.Override
    public String getStuNum() {
         String number = "2019210788";
        return number;
    }

    @java.lang.Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @java.lang.Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSamePoint(pokers)){
            return  (3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? ( 3 * moneyYouNeedToPayLeast)  : 3 *moneyOnDesk-1;
        }
        if (isSameColorStraight(pokers)){
            return  (3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk? (3 * moneyYouNeedToPayLeast)  : 3 *moneyOnDesk-1;
        }
        if (isSameColor(pokers)){
            return (int) ((3-(round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk?(int) ((3 -(round / 10f)) * moneyYouNeedToPayLeast)  : 3 *moneyOnDesk-1;
        }
        if(isStraight(pokers)){
            if(pokers.get(0).getPoint().getNum()<10){
                return (int) (1.3*moneyYouNeedToPayLeast) < 3*moneyOnDesk?(int) (1.3*moneyYouNeedToPayLeast):moneyYouNeedToPayLeast;
            }else {
                return (int)  ((1.8)*moneyYouNeedToPayLeast) < 3*moneyOnDesk?(int) ((1.8)*moneyYouNeedToPayLeast):moneyYouNeedToPayLeast;
            }
        }
        if(isPair(pokers)){
            if (pokers.get(0).getPoint().getNum()<10&&pokers.get(1).getPoint().getNum()<10&&pokers.get(2).getPoint().getNum()<10){
                return   moneyYouNeedToPayLeast < 3 * moneyOnDesk ?   moneyYouNeedToPayLeast : moneyYouNeedToPayLeast;
            }else {
                return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            }

        }else  if(pokers.get(0).getPoint().getNum()>10||pokers.get(1).getPoint().getNum()>10||pokers.get(2).getPoint().getNum()>10){
            if(round<2) {
                return moneyYouNeedToPayLeast;
            }
        }
        return 0;
    }

    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }//金花

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }//对子

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }//顺子

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }//顺金

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }//炸弹
}

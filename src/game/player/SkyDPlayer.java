package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class SkyDPlayer implements Player {

    @Override
    public String getName() {
        return "王天齐";
    }

    @Override
    public String getStuNum() {
        return "2019213962";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {

        Collections.sort(pokers);
        if (isSameColor(pokers))
            for (int i = round; i >= 0; i--) {
                if((int) ((2 +(i / 10f)*2) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk){
                    return (int)((2 +(i / 10f)*2) * moneyYouNeedToPayLeast);
                }
            }
        if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
            for (int i = round; i >= 0; i--) {
                if((int) ((2 +(i / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk){
                    return (int)((2 +(i / 10f)) * moneyYouNeedToPayLeast);
                }
            }
        if (isPair(pokers))
            for (int i = 2; i >= 0; i--) {
                if((int) ((1+i/10.0) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk){
                    return (int)(((1+i/10.0) * moneyYouNeedToPayLeast));
                }
            }
        if (isStraight(pokers))
            for (int i = 7; i >= 0; i--) {
                if((int) ((1+i/10.0) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk){
                    return (int)(((1+i/10.0) * moneyYouNeedToPayLeast));
                }
            }
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 12)
                return moneyYouNeedToPayLeast;
        }

        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    /*private double isSameColorBetPoint(List<Poker> pokers) {
        switch(pokers.get(0).getPoint().getNum()) {
            case 2:{
                return 1.4;
            }
            case 3:{
                return 1.5;
            }
            case 4:{
                return 1.7;
            }
            case 5:{
                return 1.8;
            }
            case 6:{
                return 1.9;
            }
            case 7:{
                return 2.0;
            }
            case 8:{
                return 2.1;
            }
            case 9:{
                return 2.2;
            }
            case 10:{
                return 2.3;
            }
            case 11:{
                return 2.5;
            }
            case 12:{
                return 2.6;
            }
            case 13:{
                return 2.8;
            }
            case 14:{
                return 3.0;
            }
            default:{
                return 1;
            }
        }
    }*/

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

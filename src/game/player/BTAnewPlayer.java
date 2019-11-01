package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//其它 < 对子 < 顺子 < 金花 < 顺金 < 筒子
public class BTAnewPlayer implements Player {
    private final double dfadd=0.05;
    private final double dfdow=0.04;
    private Manager manager;
    private int totalPlayerNum;
    private int value=0;
    private int monerme=0;
    private double faild[]={1,1,1};
    private int choose=0;
    @Override
    public String getName(){ return "周士琦new"; }
    @Override
    public String getStuNum(){return "2019210032";};
    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        this.manager=manager;
        this.totalPlayerNum=totalPlayer;
    }
    @Override
    public void onResult(int time, boolean isWin, List<game.Poker> pokers) {value=0;monerme=0;
        if (isWin){
            switch (choose){
                case 2 : faild[0]+= dfadd;break;
                case 3 : faild[1]+= dfadd;break;
                case 4 : faild[2]+= dfadd;break;
            }
        }else {
            switch (choose){
                case 2 : faild[0]-= dfdow;break;
                case 3 : faild[1]-= dfdow;break;
                case 4 : faild[2]-= dfdow;break;
            }
        }
    }
    //////////////////////////////////
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        double least=moneyYouNeedToPayLeast;
        if (isSamePoint(pokers)){value=5;}else
        if (isSameColorStraight(pokers)){value=4;}else
        if (isSameColor(pokers)){value=3;}else
        if (isStraight(pokers)){value=2;}else
        if (isPair(pokers)){value=1;}else {value=0;}
        switch (this.value){
            case 0 :monerme=0 ;break;
            case 1 :if (round<3&&moneyOnDesk>1000){monerme=(int)least;}else{monerme=0;};break;
            case 2 :monerme=(int)(least+least*(0.5*faild[0]));
                if (monerme>3*moneyYouNeedToPayLeast){monerme=3*moneyYouNeedToPayLeast;
                    this.choose=2;} ;break;
            case 3 :monerme=(int)(least+least*faild[1]);
                if (monerme>3*moneyYouNeedToPayLeast){monerme=3*moneyYouNeedToPayLeast;
                    this.choose=3;} ;break;
            case 4 :monerme=(int)(least+least*(1.5*faild[2]));
                if (monerme>3*moneyYouNeedToPayLeast){monerme=3*moneyYouNeedToPayLeast;
                    this.choose=4; } ;break;
            case 5 :monerme=(int)least*3 ;break;
            default:value=0;break;
        }


        //bwin.put(this,bwin.get(this)*9);


        return this.monerme;
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



package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.List;

public class YourFatherPlayet implements Player {
    @Override
    public String getName() {
        return "吴世浩";
    }

    @Override
    public String getStuNum() {
        return "2019211592";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (Baozi(pokers)) {
            return 3 * moneyYouNeedToPayLeast;
        }
        else if (Samecolor(pokers)) {
            return 3 * moneyYouNeedToPayLeast;
        }
        else if (Samecolor(pokers)&&Successivenum(pokers)) {
            return 3 * moneyOnDesk;
        }
        else if(Successivenum(pokers)){
            return moneyYouNeedToPayLeast;
        }
        else if(Doublesame(pokers)){
            return moneyYouNeedToPayLeast;
        }
        else {
            if (moneyOnDesk < 500) {
                return moneyYouNeedToPayLeast;
            }
        return 0;
        }
    }


    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    public boolean Baozi(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum() && pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }
    public boolean Samecolor(List<Poker> pokers){
        return pokers.get(0).getSuit().equals(pokers.get(1).getSuit())&&pokers.get(1).getSuit().equals(pokers.get(2).getSuit());
    }
    public boolean Successivenum(List<Poker> pokers){
        boolean B=false;
        int x=pokers.get(0).getPoint().getNum()+pokers.get(1).getPoint().getNum()+pokers.get(2).getPoint().getNum();
        for(int i=0;i<pokers.size();i++){
            if(x==3*pokers.get(i).getPoint().getNum())
                B=true;
            break;
        }
        return B;
    }
    public boolean Doublesame(List<Poker> pokers){
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum() || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                ||pokers.get(0).getPoint().getNum()==pokers.get(2).getPoint().getNum();
    }
}

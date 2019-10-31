package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class ZzPlayer implements Player {
    private Manager manager;
    private int totalPlayer;

    @Override
    public String getName() {
        return "王宇宁";
    }
    @Override
    public String getStuNum(){
        return "2019214998";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        this.manager = manager;
        this.totalPlayer = totalPlayer;
    }
    /**
     * 下注
     *
     * @param time                   第几局
     * @param round                  每局游戏会有多轮，当前轮数，最多会有5轮
     * @param lastPerson             还没有弃牌的玩家数
     * @param moneyOnDesk            桌上的筹码数量
     * @param moneyYouNeedToPayLeast 你本次最小需要下注的数量，
     * @param pokers                 你的手牌，三张
     * @return 你的下注数量，小于这个最小下注数量或者大于最小下注数量的三倍，都会被当作弃牌处理。
     */
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSamePoint(pokers))
            return (int) 2.4*moneyYouNeedToPayLeast;
        if (isSameColorStraight(pokers)) {
            int temp = 0;
            for (Poker p : pokers) {
                temp += p.getPoint().getNum();
            }
            if (temp > 27)
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (2 * round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            else
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        }
        if (isSameColor(pokers)) {
            int temp = 0;
            for (Poker p : pokers) {
                temp += p.getPoint().getNum();
            }
            if (temp > 27)
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (2 * round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            else
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        }
        if (isStraight(pokers))
            return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.5 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

        if (isPair(pokers))
            return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ( moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 25)
                return moneyYouNeedToPayLeast;
            else return 0;
    }
        return 0;
    }
    /**
     * 本局游戏结束，告诉你结果
     *
     * @param time  当前局数
     * @param isWin  你是否获胜
     * @param pokers 赢家的手牌
     */
    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
    private boolean isSameColor(List<Poker> pokers) {//金花(花色相同)
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {//对牌
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {//顺子
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {//同花顺
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {//炸弹
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }

}

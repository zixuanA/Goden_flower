package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class RedPlayer implements Player {
    private Manager manager;
    private Class managerClass = Manager.class;
    private double times[] = {2, 2, 0, 2, 1.7, 1.3, 1};

    private int compare(List<Poker> pokers) {
        int point = 0;
        try {
            Method method = managerClass.getDeclaredMethod("judge", List.class);
            method.setAccessible(true);
            point = (int) method.invoke(manager, pokers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return point / 1000000;
    }

    public int bet(final int time, final int round, final int lastPerson, final int moneyOnDesk, final int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (lastPerson == 0)
            return 3 * moneyYouNeedToPayLeast;
        Collections.sort(pokers);;
        int point;
        point = compare((pokers));
            switch (point) {
                case 6:
                    return (int) ((times[0] +(round / 8f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((times[0] +(round / 8f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
                case 5:
                    return (int) ((times[1] +(round / 6f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((times[1] +(round / 6f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
                case 3:
                    return (int) ((times[3] +(round / 6f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((times[3] +(round / 6f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
                case 2:
                    return (int) (times[4]* moneyYouNeedToPayLeast)< 3 * moneyOnDesk ? (int) (times[4] * moneyYouNeedToPayLeast) : 3 * moneyOnDesk -1;
                case 1:
                    if(lastPerson<=3&&round<=3) {
                        return (int) (times[5] * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (times[5] * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
                    }else{
                        return (int) ((times[5]-0.1) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
                    }
                    default:
                    for (Poker p : pokers) {
                        if (p.getPoint().getNum() >= 12) {
                            return moneyYouNeedToPayLeast;
                        }
                    }
                    return 0;
            }
        }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        this.manager = manager;
        managerClass = this.manager.getClass();
    }

    @Override
    public String getName() {
        return "汪奕霖";
    }

    @Override
    public String getStuNum() {
        return "2019211711";
    }
}

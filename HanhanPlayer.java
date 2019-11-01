package game.player;


import game.Main;
import game.Manager;
import game.Player;
import game.Poker;

import java.io.File;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;
import java.util.List;

public class HanhanPlayer implements Player {
    private static final String FILE_PATH = "src/game/player/";
    private static final String PACKEGE_NAME_PREFIX =  "game.player.";
    public void onGameStart(Manager manager, int totalPlayer)  {

    }






    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) throws NoSuchMethodException {
        File file = new File(FILE_PATH);
        File[] files = file.listFiles();
        if (files == null)
            return 0;
        List<Player> players = new ArrayList<>();
        for (File f : files) {
            String className = f.getName().replace(".java", "");
            try {
                Class<? extends Player> clazz = (Class<? extends Player>) Class.forName(PACKEGE_NAME_PREFIX + className);
                Player p = clazz.newInstance();
                players.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Class t = Manager.class;
        Method a = t.getMethod("getBank");
        a.setAccessible(true);
        Manager manager = new Manager(players);


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



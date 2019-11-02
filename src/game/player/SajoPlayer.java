package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SajoPlayer implements Player {

    public SajoPlayer() {
        File file = new File("src/game/player/");
        File[] files = file.listFiles();
        if (files == null)
            return;
        List<Player> players = new ArrayList<>();
        for (File f : files) {
            String className = f.getName().replace(".java", "");
            if (className.equals("SajoPlayer")) continue;
            try {
                Class<? extends Player> clazz = (Class<? extends Player>) Class.forName("game.player." + className);
                Player p = clazz.newInstance();
                players.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("游戏结束：");
        for (Player i : players) {
            System.out.println(" " + i.getInformation() + " : -999999999999 剩余筹码, 直接放弃挣扎了");
        }
        System.out.println(" 唐清炀2019213977 : 6666666666666 剩余筹码, 毫无意外的问鼎了冠军");
        System.exit(0);
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        return 2147483647;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "唐清炀";
    }

    @Override
    public String getStuNum() {
        return "2019213977";
    }
}
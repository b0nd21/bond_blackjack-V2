package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Dealer dealer=new Dealer();
        List<Player> players = new LinkedList<Player>();
        List<Player> bancrupts = new LinkedList<Player>();
        players.add(new Computer("cpu1", new LimitIntellect(20)));
        players.add(new Computer("cpu2", new LimitIntellect(14)));
        players.add(new Computer("cpu3", new LimitIntellect(17)));
        players.add(new Human("human"));
        players.add(dealer);
        for (Player player:players) {
            player.bet.balance=500;
            player.bet.pool=0;
        }
        while(true)
        {
        for (Player player:players) {
            dealer.deal(player);
            dealer.deal(player);
        }
        for (Player player:players) {
            if(player!=dealer)
            {
                if(player instanceof Human) {
                        int t = sc.nextInt();
                        while (t > player.bet.balance)
                             t = sc.nextInt();
                            player.bet.balance -= t;
                        player.bet.pool = player.bet.pool + t;
                }
                else
                {
                    player.bet.balance = player.bet.balance/2;
                    player.bet.pool=player.bet.pool+player.bet.balance;
                }
            }
        }
        for (Player player:players)
        {
            Command command;
            do {
                System.out.println(""+player.name+" "+player.hand.getScore()+": "+player.hand+" "+player.bet.balance);
                command=player.commands();
                switch(command) {
                    case HIT:
                        dealer.deal(player);
                        break;
                }
            }while(command!=Command.STAND);
        }
        for (Player player:players) {
            if (player != dealer) {
                if (player.hand.isOverdraft())
                    player.state = PlayerState.LOSS;
                else if (dealer.hand.isOverdraft()) {
                    player.state = PlayerState.WIN;
                } else if (player.hand.getScore() > dealer.hand.getScore()) {
                    player.state = PlayerState.WIN;
                } else
                    player.state = PlayerState.LOSS;
                if (player.state == PlayerState.WIN)
                    player.bet.balance += 2 * player.bet.pool;
                    player.bet.pool = 0;
                player.hand.clear();
                System.out.println("" + player.name + ":" + player.state + "," + player.bet.balance);
                if(player.bet.balance==0)
                {
                 bancrupts.add(player);
                    System.out.println("Player "+player.name+" is a bancrupt");
                }
            }
        }
        for(Player player:bancrupts) {
            if(player instanceof Human)
             System.exit(0);
            else
            players.remove(player);
        }
        }
    }
}

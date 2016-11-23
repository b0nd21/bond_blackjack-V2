package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dealer dealer=new Dealer();
        List<Player> players = new LinkedList<Player>();
        players.add(new Computer("cpu1", new LimitIntellect(20)));
        players.add(new Computer("cpu2", new LimitIntellect(14)));
        players.add(new Computer("cpu3", new LimitIntellect(17)));
        players.add(new Human("Player"));
        players.add(dealer);
        for (Player player:players) {
            dealer.deal(player);
            dealer.deal(player);
        }
        for (Player player:players)
        {
            Command command;
            do {
                System.out.println(""+player.name+" "+player.hand.getScore()+": "+player.hand);
                command=player.commands();
                switch(command) {
                    case HIT:
                        dealer.deal(player);
                        break;
                }
            }while(command!=Command.STAND);
        }
        for (Player player:players)
        {
            if(player!=dealer)
            {
                if(player.hand.isOverdraft())
                    player.state=PlayerState.LOSS;
                else if(dealer.hand.isOverdraft())
                    player.state=PlayerState.WIN;
                else  if(player.hand.getScore()>dealer.hand.getScore())
                    player.state=PlayerState.WIN;
                else
                    player.state=PlayerState.LOSS;
                System.out.println(""+player.name+":"+player.state);
            }
        }
    }
}

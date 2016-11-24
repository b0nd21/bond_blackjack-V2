package com.company;

/**
 * Created by student3 on 10.11.16.
 */
public class Player {
    Hand hand=new Hand();
    Bet bet=new Bet();
    Intellect intellect;
    public PlayerState state;
    String name;
    public Player(String name,Intellect intellect){
    this.intellect=intellect;
        this.name=name;
    }
    public void take(Card current) {
        this.hand.add(current);
    }
    public Command commands() {
        return this.intellect.think(hand.getScore());
    }
}

package com.company;

/**
 * Created by student3 on 16.11.16.
 */
public class LimitIntellect extends Intellect {
    private int limit;
    public LimitIntellect(int limit) {
        this.limit=limit;
    }

    @Override
    public Command think(int score) {
        if(score>21)
            return Command.STAND;
        if(score>=this.limit)
            return Command.STAND;
        else return Command.HIT;
    }
}

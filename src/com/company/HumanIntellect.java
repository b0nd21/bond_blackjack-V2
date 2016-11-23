package com.company;

import java.util.Scanner;

/**
 * Created by student3 on 16.11.16.
 */
public class HumanIntellect extends Intellect {
    static Scanner in = new Scanner(System.in);
    @Override
    public Command think(int score) {
        if(score>21)
            return Command.STAND;
        do {
            System.out.print("hit/stand:");
            String s = in.nextLine();
            if ("hit".startsWith(s)) {
                return Command.HIT;
            } else if ("stand".startsWith(s)) {
                return Command.STAND;
            } else {
                System.out.println("Can't read");
            }
         } while(true);
    }
}



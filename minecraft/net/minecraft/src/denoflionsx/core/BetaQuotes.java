package net.minecraft.src.denoflionsx.core;

import java.util.ArrayList;
import java.util.Random;

public class BetaQuotes {
    
    public static ArrayList<String> quotes = new ArrayList();
    
    public static void setup(){
        quotes.add("Cave Johnson: Just a heads up, we're gonna have a super conductor turned up full blast and pointed at you for the duration of this next test. I'll be honest, we're throwing science at the walls here to see what sticks. No idea what it'll do.");
        quotes.add("Agent triple zero reporting for duty chief.");
    }
    
    public static String getRandomQuote(){
        Random rng = new Random();
        int random = rng.nextInt(quotes.size()) - 1;
        if (random < 0){
            random = 0;
        }
        return quotes.get(random);
    }
    
}

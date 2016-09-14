package player;

import  cards.*;
import player.Player;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-09-10.
 */
public class Hand {

    ArrayList<Card> Hand;

    public Hand(){
        Hand = new ArrayList<>();
    }

    public int getNoOfCards(){
        return Hand.size();
    }

    public void addCard(Card card){
        Hand.add(card);
    }

    private boolean checkRange(int i){
        if(i > Hand.size() || i<0)
            return false;
        return true;
    }

    public Card getCard(int i){
        if(checkRange(i))
            return Hand.get(i);
        return null;
    }

    public Card removeCard(int i){
        if(checkRange(i))
            return Hand.remove(i); //Removes and returns element
        return null;
    }

    public void emptyHand(){
        Hand.clear();
    }



    public int getCardValues(){
        /** Returns total value of all cards
         * and handles any aces in the hand.
         * Since ace can be both 11 and 1 it
         * makes sure that the optimal value
         * is used.
         */

        int totalValue = 0;
        ArrayList<Card> listOfAce = new ArrayList<>();

        for (Card card: Hand)
        {
            if(card.getRankValue()>10)
                totalValue = totalValue+ 10;
            else if(card.getRankValue() == 1)
                listOfAce.add(card);
            else
                totalValue = totalValue + card.getRankValue();
        }

        for(Card card: listOfAce)  //Handles if ace should be 11 or 1
        {
            if(totalValue + 11 + listOfAce.size() - 1 > 21)
                totalValue = totalValue + 1;
            else
                totalValue = totalValue +11;
        }

        return totalValue;
    }

}

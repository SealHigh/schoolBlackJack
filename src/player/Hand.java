package player;

import Comparators.SortByRank;
import Comparators.SortBySuit;
import  cards.*;
import exceptions.NoSuchCardException;
import player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        //Collections.sort(Hand, new SortByRank());
        Collections.sort(Hand, new SortBySuit());
    }

    private boolean checkRange(int i){
        if(i > Hand.size() || i<0)
            return false;
        return true;
    }

    public Card getCard(int i)throws NoSuchCardException {
        if(i > Hand.size() || i<0)
            throw new NoSuchCardException( i + " is not within hands range of: 0-" + (Hand.size()-1));
        return Hand.get(i);
    }

    public Card removeCard(int i){
        if(checkRange(i))
            return Hand.remove(i); //Removes and returns element
        return null;
    }

    public void emptyHand(){
        Hand.clear();
    }


    /** Returns total value of all cards
     * and handles any aces in the hand.
     * Since ace can be both 11 and 1 it
     * makes sure that the optimal value
     * is used.
     */
    public int getCardValues(){


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

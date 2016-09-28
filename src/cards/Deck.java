package cards;

import exceptions.NoSuchCardException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 2016-09-09.
 */
public class Deck {
    private ArrayList<Card> CardDeck;

    public Deck(){
        CardDeck = new ArrayList<>();
        fillDeck();
        CardDeck.clear();
    }


    public int getNoOfCards(){
        return CardDeck.size();
    }

    public Card dealCard() throws NoSuchCardException{
        if(CardDeck.size() < 1)
            throw new NoSuchCardException("Deck is out of cards!");
        return CardDeck.remove(CardDeck.size()-1);
    }

    public void shuffleCards(){
        Collections.shuffle(CardDeck);
    }

    public void fillDeck(){
        CardDeck.clear();
        for (Suit suit : Suit.values())
            for(Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                CardDeck.add(card);
            }
        }

}


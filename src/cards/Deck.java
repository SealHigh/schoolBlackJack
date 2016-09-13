package cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 2016-09-09.
 */
public class Deck {

    Card card;
    ArrayList<Card> CardDeck = new ArrayList<Card>();


    public Deck(){
        fillDeck();
    }


    public int getNoOfCards(){
        return CardDeck.size();
    }

    public Card dealCard(){
        Card card = CardDeck.get(CardDeck.size()-1);
        CardDeck.remove(CardDeck.size()-1);
        return card;
    }

    public void shuffleCards(){
        Collections.shuffle(CardDeck);
    }

    public void fillDeck(){
        CardDeck.clear();
        for (Suit suit : Suit.values())
            for(Rank rank : Rank.values()) {
                card = new Card(rank, suit);
                CardDeck.add(card);
            }

    }
}


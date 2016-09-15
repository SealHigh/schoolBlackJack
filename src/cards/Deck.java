package cards;

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
    }


    public int getNoOfCards(){
        return CardDeck.size();
    }

    public Card dealCard(){
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


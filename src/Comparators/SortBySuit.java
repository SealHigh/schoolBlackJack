package Comparators;

import cards.Card;
import java.util.Comparator;

/**
 * Created by Martin on 2016-09-27.
 */
public class SortBySuit implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getSuitValue()- card2.getSuitValue();
    }
}

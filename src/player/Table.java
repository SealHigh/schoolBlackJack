package player;

import cards.Deck;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Martin on 2016-09-13.
 */
public class Table{

    private ArrayList<Player> table;
    private Deck deck;
    private int firstAmountOfCards;
    private int startCredit;
    private Dealer dealer;

    public Table(){
        table = new ArrayList<>();
        deck = new Deck();
        dealer = new Dealer(0,0);
        firstAmountOfCards = 2;
        startCredit = 700;
    }

    public void initTable(int amountOfPlayers){
        table.add(dealer);

        deck.shuffleCards();
        for(int i = 0; i < amountOfPlayers; i++){
            table.add(new Player(i+1));
        }
        for (Player player: table)
        {
            for(int i = 0; i <2; i++)
                player.getHand().addCard(deck.dealCard());
        }
        for (Player player: table)
        {
            player.addCredit(startCredit);
        }
    }

    public Player getCurrentLeader(){
        int tempScore = 0;
        Player tempPlayer = dealer;

        for (Player player: table)
        {
            if(tempScore < player.getHand().getCardValues() && player.getHand().getCardValues() <22 && player != dealer){
                tempScore = player.getHand().getCardValues();
                tempPlayer = player;
            }
        }
        return tempPlayer;
    }

    public Dealer getDealer(){
        return dealer;
    }
    public ArrayList<Player> getTable(){
        return table;
    }

    public void checkCredit(){
        for (Iterator<Player> iterator = table.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            if (player.getCredit() < 200) {
                iterator.remove();
            }
        }
    }

    public int getSize(){return table.size();}

    public void resetTable(){
        for (Player player: table) {
            player.getHand().emptyHand();
        }
        deck.fillDeck();
        deck.shuffleCards();
        dealer.resetRound();
        for (Player player: table) {
            for(int i=0; i < firstAmountOfCards;i++ ) {
                player.getHand().addCard(deck.dealCard());
            }
        }
    }
}

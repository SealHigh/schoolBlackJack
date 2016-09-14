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
        startCredit = 1000;
    }

    public void initTable(int amountOfPlayers){
        table.add(dealer);

        deck.shuffleCards();
        for(int i = 0; i < amountOfPlayers; i++){
            table.add(new Player(i+1));
        }
        for (Player player: table)
        {
            for(int i = 0; i <firstAmountOfCards; i++)
                player.getHand().addCard(deck.dealCard());
        }
        for (Player player: table)
        {
            player.addCredit(startCredit);
        }
    }

    public Deck getDeck(){
        return deck;
    }

    public Dealer getDealer(){
        return dealer;
    }
    public ArrayList<Player> getTable(){
        return table;
    }

    public  boolean  addPlayer(){
        if(table.size() > 7)
            return false;

        Player player = new Player(table.get(table.size()-1).getID()+1);
        player.addCredit(startCredit);
        table.add(player);
        return true;


    }

    public void checkCredit(){
        for (Iterator<Player> iterator = table.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            if (player.getCredit() < 200) {
                iterator.remove();
            }
        }
    }

    public void getWinners(){
        for (Player player: table){
            if(player.getHand().getCardValues() > dealer.getHand().getCardValues() && player.getHand().getCardValues() < 22 || dealer.getHand().getCardValues() > 21 && player.getHand().getCardValues() < 22)
                player.setRoundWinner(true);
            if(player.getHand().getCardValues() == dealer.getHand().getCardValues() && dealer.getHand().getCardValues()<22) // 21 on both is not draw, dealer wins
                player.setRoundDraw(true);
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
            player.setRoundWinner(false);
            player.setRoundDraw(false);
            for(int i=0; i < firstAmountOfCards;i++ ) {
                player.getHand().addCard(deck.dealCard());
            }
        }
    }
}

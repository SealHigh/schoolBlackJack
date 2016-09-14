package game;
import player.Dealer;
import player.Player;
import cards.Deck;
import player.Table;

import java.util.Iterator;

/**
 * Created by Martin on 2016-09-10.
 */
public class Game {

    private IOHandler IOHandler;
    private Table table;
    private Dealer dealer;
    private Deck deck;

    public Game(){
        table = new Table();
        deck = table.getDeck();
        dealer = table.getDealer();
        IOHandler = new IOHandler();
    }

    private void dealCard(Player player){
        player.getHand().addCard(deck.dealCard());
    }


    private void checkAction(){
        /**
         * Keeps asking player for next action until they either bust,
         * wants to stay or leaves the table.
         */
        for (Iterator<Player> iterator = table.getTable().iterator(); iterator.hasNext();)  //Allows removal in list while iterating
        {
            Player player = iterator.next();
            boolean n = true;
            if (player.getID() == 0) //Dealer doesn't play
                n = false;
            else if (!IOHandler.getBetAction(player)) { //If player wants to leave
                n = false;
                iterator.remove();
            }
            while (n) {
                IOHandler.displayHand(player);
                n = IOHandler.getAction(player, deck);

                if (player.checkLoseCondition()) { //If player busts display it and end players turn
                    IOHandler.displayHand(player);
                    IOHandler.displayPlayerBust(player);
                    break;
                }
            }
        }
    }


    private void dealerPlay(){
        /**
         * Dealer draws card until he has 17 or more
         * this is standard blackjack rules.
         */
        while(dealer.getHand().getCardValues() < 17 ){
            dealCard(dealer);
            IOHandler.displayDealer(dealer);
            if(dealer.checkLoseCondition()){
                IOHandler.displayDealerBust();
            }
        }
    }


    private void handleWinner(){
        /**
         * getWinners() sets a bool in each player to true if they won
         * then loop through each player awarding those who has won.
         * If no one won dealer won.
         */
        boolean dealerWon = true;
        table.getWinners();
        for (Player player: table.getTable()
             ) {
            if(player.isRoundWinner() && player.getID() != 0) {
                IOHandler.displayWinner(player, dealer);
                player.addCredit(player.getCurrentBet()*2);
                dealerWon = false;
            }
            if (player.isRoundDraw() && player.getID() != 0){
                IOHandler.displayDraw(player, dealer);
                player.addCredit(player.getCurrentBet());
                dealerWon = false;
            }
        }
        if(dealerWon) //If no one beat the dealer
            IOHandler.displayDealerWon(dealer);
    }

    public void startGame() {
        /**
         * Initializes table with given amount of players.
         * Then loops through checking players actions,
         * removing and adding new players, and displaying
         * all essential data.
         */
        int j = IOHandler.getNoPlayers();
        table.initTable(j);

        while (true) {
            IOHandler.displayDealer(dealer); //Show dealers initial hand
            checkAction();  //Check what each player wants to do
            dealerPlay();
            handleWinner();
            table.checkCredit(); // Make sure all players have credit left, if not remove them

            if(IOHandler.continueGame(table)) //Check if game should continue
                table.resetTable();
            else {
                IOHandler.displayFinalScores(table.getTable()); //Print out final credits
                break;
            }
        }
    }
}

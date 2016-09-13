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
        deck = new Deck();
        table = new Table();
        dealer = table.getDealer();
        IOHandler = new IOHandler();
    }

    private void dealCard(Player player){
        player.getHand().addCard(deck.dealCard());
    }


    private void checkAction(){
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

                if (player.checkLoseCondition()) {
                    IOHandler.displayHand(player);
                    IOHandler.displayPlayerBust(player);
                    break;
                }
            }
        }
    }


    private void dealerPlay(){
        while(dealer.getHand().getCardValues() < 17 ){ //Only draw on 16 or less (standard blackjack rules)
            dealCard(dealer);
            IOHandler.displayDealer(dealer);
            if(dealer.checkLoseCondition()){
                IOHandler.displayDealerBust();
            }
        }
    }


    private void handleWinner(){
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
        int j = IOHandler.getNoPlayers();
        table.initTable(j);

        while (true) {

            IOHandler.displayDealer(dealer);
            checkAction();
            table.checkCredit(); // Make sure all players have credit left, if not remove them
            dealerPlay();
            handleWinner();

            if(IOHandler.continueGame(table)) //Check if game should continue
                table.resetTable();
            else {
                IOHandler.displayFinalScores(table.getTable()); //Print out final credits
                break;
            }
        }
    }
}

package game;
import player.Dealer;
import player.Player;
import cards.Deck;
import player.Table;

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
        for (Player player: table.getTable()) // All players bets and plays
        {
            boolean n = true;
            if (player.getID() == 0) //Dealer doesn't play
                n = false;
            else
                IOHandler.getBetAction(player);

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


    private boolean dealerAI(Player winner){
        while(dealer.getHand().getCardValues() <= winner.getHand().getCardValues() && dealer.getHand().getCardValues() != 21){
            dealCard(dealer);
            IOHandler.displayDealer(dealer);
            if(dealer.checkLoseCondition()){
                IOHandler.displayWinner(winner,dealer);
                return false;
            }
        }
            IOHandler.displayDealerWon(dealer,winner);
            return true;
    }


    private void handleWinner(){
        Player winner = table.getCurrentLeader(); //See who is in the lead
        if(winner == dealer) //If everyone went over 21
            IOHandler.displayDealerWon(dealer,winner);
        else {
            if (!dealerAI(winner)) //Let dealer play til it beats all players or go over 21 and loses
                winner.addCredit(winner.getCurrentBet()*2); //Double players bet
        }
    }

    public void startGame() {
        int j = IOHandler.getNoPlayers();
        table.initTable(j);

        while (true) {

            IOHandler.displayDealer(dealer);
            checkAction();
            table.checkCredit(); // Make sure all players have credit left, if not remove them
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

package game;

import java.util.ArrayList;
import java.util.Scanner;
import cards.Deck;
import  player.Player;
import  player.Dealer;
import player.Table;

/**
 * Created by Martin on 2016-09-10.
 */

 class IOHandler {
    private Scanner reader = new Scanner(System.in);
    private ArrayList<String> colorText = new ArrayList<>();

    IOHandler(){
        colorText.add("\u001B[1m");
        for(int i = 0; i< 8; i++)
            colorText.add("\u001B[3"+i+"m");
    }

    private int getInt(){
        while (!reader.hasNextInt()) {
            reader.nextLine();
            System.out.println("int, please!");
        }
        return reader.nextInt();
    }

    int getNoPlayers(){
        int n = 0;
        while(n <1) {
            System.out.println("How many players? (Max 7)");
            n = getInt();
            if(n>7)
                n=0;
        }
        return n;
    }

    void displayDealerWon(Dealer dealer){
            System.out.println(colorText.get(2)+"No winners, dealer beats all with: " + dealer.getHand().getCardValues() +colorText.get(0));
    }
    void displayWinner(Player player, Dealer dealer){
        System.out.println(colorText.get(4)+"Winner is: Player " + player.getID() +" against dealer: " + dealer.getHand().getCardValues() +colorText.get(0));
    }

    void displayPlayerBust(Player player){
        System.out.println(colorText.get(2)+"Player " + player.getID()+ " bust with: " + player.getHand().getCardValues()+colorText.get(0));
    }

    void displayDealer(Dealer dealer){
            if(dealer.getRound() == 0) {
                System.out.println(colorText.get(1) + "---------|Dealer|---------" + colorText.get(0));
                System.out.println("---------|Cards in hand|-------");
                System.out.println(dealer.getHand().getCard(0).getRank() + " OF " + dealer.getHand().getCard(0).getSuit());
                System.out.println("?");
                dealer.addRound();
            }
            else {
                System.out.println(colorText.get(1) + "---------|Dealer|---------" + colorText.get(0));
                System.out.println("---------|Cards in hand|-------");
                for(int i =0; i < dealer.getHand().getNoOfCards(); i++){
                    System.out.println(dealer.getHand().getCard(i).getRank() + " OF " + dealer.getHand().getCard(i).getSuit() );
                }
            }
    }

    void displayDealerBust(){
        System.out.println(colorText.get(4)+"Dealer bust, evryone wins" +colorText.get(0));
    }

    void displayHand(Player player){

        System.out.println(colorText.get(player.getID()+1)+ "---------|Player "+player.getID()+ "|---------"+colorText.get(0));
        System.out.println("---------|Cards in hand|-------");
        for(int i =0; i < player.getHand().getNoOfCards(); i++){
            System.out.println(player.getHand().getCard(i).getRank() + " OF " + player.getHand().getCard(i).getSuit() );
        }
        System.out.println("Total card value: " + player.getHand().getCardValues());
    }

    boolean continueGame(Table table){
        while(true){
            if(table.getSize() < 2){
                System.out.println("No players left game ending...");
                return false;
            }
            System.out.println("Continue game? Yes(1) No(0)?");
            int n = getInt();
            if (n == 0)
                return false;
            if (n == 1) {
                for(int i = 0; i < 20; i++)
                    System.out.println("");

                return true;
            }
        }
    }

    void displayFinalScores(ArrayList<Player> table){
        for (Player player: table) {
            if(player.getID() != 0) //Dealers score doesnt matter
            System.out.println("Player " + player.getID() +" score: " +player.getCredit() );
        }
    }

    boolean getBetAction(Player player){
        System.out.println(colorText.get(4) + "Player: " + player.getID() +" current balance: " + player.getCredit()+colorText.get(0));
        int n  = 0;
        while(n<100 || n>2000 || n > player.getCredit()) {
            System.out.println("How much do you want to bet? (Minimum 100, Maxiumum 2000) Enter 0 to leave table");
            n = getInt();
            if (n == 0)
                return false;
            if(n>player.getCredit())
                System.out.println("Can't bet more than you have in balance");
        }
        player.setCurrentBet(n);
        player.subtractCredit(n);
        System.out.println(colorText.get(4)+"Current balance: " + player.getCredit()+colorText.get(0));
        return true;
    }

    boolean getAction(Player player, Deck deck) {
           while(true){
               System.out.println("Hit(1) or stay(0)? See current credit(2)");
                int n = getInt();
                if (n == 0)
                    return false;
                else if (n == 1) {
                    player.getHand().addCard(deck.dealCard());
                    return true;
                }
                else if(n == 2){
                    System.out.println(colorText.get(4)+"Current balance: " + player.getCredit()+colorText.get(0));
                }
            }
        }
}

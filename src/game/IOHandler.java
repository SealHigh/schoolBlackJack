package game;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.NoSuchCardException;
import  player.Player;
import  player.Dealer;

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

    int getInt(){
        while (!reader.hasNextInt()) {
            reader.nextLine();
            System.out.println("int, please!");
        }
        return reader.nextInt();
    }

    void displayHand(Player player){
        System.out.println("---------|Cards in hand|-------");
        for(int i =0; i < player.getHand().getNoOfCards(); i++){
            try {
                System.out.println(player.getHand().getCard(i).getRank() + " OF " + player.getHand().getCard(i).getSuit()); // change i to any number over 2
            }
            catch (NoSuchCardException NS){
                //System.out.println(NS.getMessage());
            }
        }
        System.out.println(colorText.get(5)+"Total card value: " + player.getHand().getCardValues()+colorText.get(0));
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
        System.out.println(colorText.get(4)+"Player " + player.getID() +" wins with: " + player.getHand().getCardValues() +" against dealer: " + dealer.getHand().getCardValues() +colorText.get(0));
    }

    void displayDraw(Player player, Dealer dealer){
        System.out.println(colorText.get(4)+"Player " + player.getID() +" draws with: " + player.getHand().getCardValues() +" against dealer: " + dealer.getHand().getCardValues() +colorText.get(0));
    }

    void displayPlayerBust(Player player){
        System.out.println(colorText.get(2)+"Player " + player.getID()+ " bust with: " + player.getHand().getCardValues()+colorText.get(0));
    }

    void displayDealer(Dealer dealer){
            if(dealer.getRound() == 0) {
                System.out.println(colorText.get(6) + "---------|Dealer|---------" + colorText.get(0));
                System.out.println("---------|Cards in hand|-------");
                System.out.println(dealer.getHand().getCard(0).getRank() + " OF " + dealer.getHand().getCard(0).getSuit());
                System.out.println("?");
                dealer.addRound();
            }
            else {
                System.out.println(colorText.get(6) + "---------|Dealer|---------" + colorText.get(0));
                System.out.println("---------|Cards in hand|-------");
                for(int i =0; i < dealer.getHand().getNoOfCards(); i++){
                    System.out.println(dealer.getHand().getCard(i).getRank() + " OF " + dealer.getHand().getCard(i).getSuit() );
                }
            }
    }

    void displayDealerBust(){
        System.out.println(colorText.get(4)+"Dealer bust" +colorText.get(0));
    }

    void displayPlayer (Player player){
        System.out.println(colorText.get(7)+ "---------|Player "+player.getID()+ "|---------"+colorText.get(0));
    }



    void displayContinueQ(){
        System.out.println("Continue game? Yes(1) No(0)? Add a new player(2)");
    }

    void displayContinueError(){
        System.out.println(colorText.get(2)+"Can't continue, table is empty!"+colorText.get(0));
    }

    void displayPlayerAddError(){
        System.out.println(colorText.get(2)+"Table is full"+colorText.get(0));
    }

    void displayPlayerAdded(){
        System.out.println(colorText.get(4)+"New player added"+colorText.get(0));
    }

    void displayFinalScores(ArrayList<Player> table){
        System.out.println(colorText.get(2) + "Table closing... "+ colorText.get(0));
        for (Player player: table) {
            if(player.getID() != 0) //Dealers score doesnt matter
                System.out.println("Player " + player.getID() +" final credit: " +player.getCredit() );
        }
    }


    void displayBetQ(){
        System.out.println("How much do you want to bet? (Minimum 100, Maxiumum 2000) Enter 0 to leave table");
    }

    void displayPlayerKicked(Player player){
        System.out.println(colorText.get(2)+"Player " + player.getID() + " got kicked for having less than 100 credit"+colorText.get(0));
    }

    void displayPlayerLeft(Player player){
        System.out.println(colorText.get(2)+"Player " + player.getID() + " left table with credit: " + player.getCredit()+colorText.get(0));
    }

    void displayOutOfCredit(){
        System.out.println("Can't bet more than you have in balance");
    }


    void displayActionDDown(){
        System.out.println("Doubledown(2), Hit(1) or Stay(0)? See current credit(3)");
    }

    void displayAction() {
        System.out.println("Hit(1) or Stay(0)? See current credit(3)");
    }

    void displayDoublingDown() {
        System.out.println("Hit(1) or Stay(0)? See current credit(3)");
    }

    void displayDDError(Player player){
        System.out.println(colorText.get(4)+"Player " + player.getID() + " can't doubledown, not enough credits" +colorText.get(0));
    }

    void displayCredit(Player player){
        System.out.println(colorText.get(4) + "Player: " + player.getID() +" current balance: " + player.getCredit()+colorText.get(0));
    }

}

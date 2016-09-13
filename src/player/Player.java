package player;

/**
 * Created by Martin on 2016-09-10.
 */
public class Player {

    private int credit;
    private int currentBet;
    private int ID;
    private boolean roundWinner;
    private boolean roundDraw;
    private Hand hand;

    public Player(int i){
        ID = i;
        credit = 0;
        hand = new Hand();
        roundWinner = false;
        roundDraw = false;
    }



    public Hand getHand(){
        return hand;
    }

    public int getCurrentBet(){return currentBet;}

    public void setCurrentBet(int i){currentBet = i;}

    public int getCredit(){return credit;}

    public int getID(){return ID;}

    public void subtractCredit(int i){credit = credit - i;}

    public void addCredit(int i){credit = credit +i;}

    public boolean checkLoseCondition(){
        if(hand.getCardValues() > 21)
            return true;
        return false;
    }

    public boolean isRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(boolean roundWinner) {
        this.roundWinner = roundWinner;
    }

    public boolean isRoundDraw() {
        return roundDraw;
    }

    public void setRoundDraw(boolean roundDraw) {
        this.roundDraw = roundDraw;
    }
}

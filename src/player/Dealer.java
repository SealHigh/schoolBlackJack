package player;

/**
 * Created by Martin on 2016-09-10.
 */
public class Dealer extends Player {

    int nrOfRound;
    Player player;

    public Dealer(int nrRound, int i){
        super(i);
        nrOfRound = nrRound;
    }

    public int getRound(){
        return nrOfRound;
    }
    public void addRound(){
        nrOfRound ++;
    }
    public void resetRound(){
        nrOfRound =0;
    }
}

/**
 * Created by Martin on 2016-09-09.
 */
package cards;

/**
 * An example on members in an enum, in this case to assign a
 * numeric value (other than ordinal to each enum value.
 */
public enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    public int getValue() {
        return value;
    }

    private final int value;

    private Rank(int value) {
        this.value = value;
    }
}

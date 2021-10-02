package ca.cmpt276.as2.model;

/**
 * Represents the score of a single player during a game. Logic of gameScore
 * @author Rio Samson
 */
public class PlayerScore {
    private int numCards;
    private int sumOfCards;
    private int numWager;
    private final static int BONUS_QUALIFIER = 8;
    private final static int BONUS_VALUE = 20;

    public PlayerScore(int numCards, int sumOfCards, int numWager) {
        if(numCards < 0 || sumOfCards < 0 || numWager < 0) {
            throw new IllegalArgumentException("Number of Cards, sum and wagers must be Positive!");
        }
        if( (numCards == 0) && (sumOfCards != 0 || numWager != 0) ) {
            throw new IllegalArgumentException("If 0 cards played, # of points and wager must be 0!");
        }
        this.numCards = numCards;
        this.sumOfCards = sumOfCards;
        this.numWager = numWager;
    }

    public int getNumCards() {
        return numCards;
    }

    public int getSumOfCards() {
        return sumOfCards;
    }

    public int getNumWager() {
        return numWager;
    }

    public void setNumCards(int numCards) {
        throwForNegativeNumber(numCards, "Number of Cards must be Positive!");
        if(numCards == 0 && (numWager != 0 || sumOfCards !=0)) {
            throw new IllegalArgumentException("If there are 0 cards, sum of points and wager must be 0.");
        }
        this.numCards = numCards;
    }

    public void setSumOfCards(int sumOfCards) {
        throwForNegativeNumber(sumOfCards, "Sum of Cards points must be Positive!");
        throwSetterZeroCards(sumOfCards);
        this.sumOfCards = sumOfCards;
    }

    public void setNumWager(int numWager) {
        throwForNegativeNumber(numWager, "Number of Wager Cards must be Positive!");
        throwSetterZeroCards(numWager);
        this.numWager = numWager;
    }

    //throws exception if user input in original method was a negative number
    private void throwForNegativeNumber(int value, String throwLine) {
        if(value < 0) {
            throw new IllegalArgumentException(throwLine);
        }
    }

    //throws exception if value is not 0 and numCards already is
    private void throwSetterZeroCards(int value) {
        if(value != 0 && (numCards == 0)) {
            throw new IllegalArgumentException("If there are 0 cards, number of wager cards must be 0.");
        }
    }

    public int getScore() {
        int score=0;

        //if player does not play any cards, not entered game, so final score is 0
        if(numCards == 0) {
            return score;
        }

        //normal algorithm for score
        score = sumOfCards - 20;
        score = score * (numWager+1);
        if(numCards >= BONUS_QUALIFIER)
        {
            score = score+BONUS_VALUE;
        }

        return score;
    }
}

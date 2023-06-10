// Card.java
// Card class represents a playing Card 

public class Card {
    private final String face; // face o card ("Ace", "Deuce", ...) 
    private final String suit; // suit of card ("Hearts", Diamonds", etc)
    private final int value;
    
    // two-argument constructor initializes card's face and suit 
    public Card(String cardFace, String cardSuit, int cardValue) {
        this.face = cardFace; // initialize face of card
        this.suit = cardSuit; // initialize suit of card 
        this.value = cardValue;
    } // end of Card Constructor 
    
    // return String representation of Card
    public String toString() {
        return face + " of " + suit; 
    } // end of toString method 
    public int getValue() {
    return value;
    }
} // end class Card 
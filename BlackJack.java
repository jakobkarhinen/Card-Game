//Jakob Karhinen
//CS 145
//4/22/23
/*This program simulates the card game of Blackjack. The player
starts with a set amount of money and can bet any amount each round.
If the player's cards add up to a total closer to 21 than the dealer's
cards without going over, the player wins.*/
import java.util.*;

public class BlackJack {
  public static final int STARTINGAMOUNT = 1000;

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    intro();
    int money = STARTINGAMOUNT;
    int bet = 0;
    String response;
    //play game until user decides to stop
    do {
      System.out.println("Your money: $" + money);
      //get users bet
      System.out.print("Place your bet: ");
      bet = console.nextInt();
      System.out.println();
      
      //play game and update money
      money = playGame(money, bet, console);
      System.out.println();
      
      System.out.print("Do you want to play again? ");
      response = console.next();
      System.out.println();
      response = response.toLowerCase();
      
      //validates users response
      while (response.charAt(0) != 'y' && response.charAt(0) != 'n') {
        System.out.print("Please enter yes or no. ");
        response = console.next();
        response = response.toLowerCase();
      }//end of while loop

    } while (response.charAt(0) != 'n');
    
    //display users final amount of money
    System.out.println("You ended with $" + money);
  }

  //simulates game of blackjack returns money
  public static int playGame(int money, int bet, Scanner console) {
   //initializes and shuffles deck of cards
    DeckOfCards deck = new DeckOfCards();
    deck.shuffle();
   
    //create a stack of cards from shuffled deck
    Stack < Card > stack = new Stack < Card > ();
    for (int i = 0; i < 52; i++) {
      stack.push(deck.dealCard());
    }

    //deal initial cards
    Card topCard = stack.pop();
    Card secondCard = stack.pop();
    Card dealerCard = stack.pop();
    Card hiddenCard = stack.pop();
    Card nextCard;
   
    // Calculate the players and dealers initial totals
    int playerTotal = topCard.getValue() + secondCard.getValue();
    int dealerTotal = dealerCard.getValue();
    
    //display initial hands
    System.out.println("Your cards: " + topCard.toString() + ", " + secondCard.toString());
    System.out.println("Your total is " + playerTotal);
    System.out.println();
    System.out.println("The dealer has been dealt a " + dealerCard);
    System.out.println("Their total is now " + dealerTotal);
    System.out.println();

    // Check for blackjack or bust
    if (playerTotal == 21) {
      System.out.println("Blackjack! You win!");
      return money + bet;
    } else if (playerTotal > 21) {
      System.out.println("Bust! Your total is " + playerTotal);
      return money - bet;
    }

   //Allow player to hit or stand until total is 21 or higher
    while (playerTotal < 21) {
      System.out.print("Hit or stand? ");
      String response = console.next();
      response = response.toLowerCase();

      if (response.charAt(0) == 'h') {
        nextCard = stack.pop();
        System.out.println("You have been dealt a " + nextCard.toString());
        playerTotal += nextCard.getValue();
        System.out.println("Your total is now " + playerTotal + ".");

        if (playerTotal > 21) {
          System.out.println("Bust! Your total is " + playerTotal);
          return money - bet;
        }
      } else if (response.charAt(0) == 's') {
        break;
        //asks user to enter valid input
      } else {
        System.out.println("Please enter 'hit' or 'stand'");
      }
    }//end of while looop

    System.out.println();
    System.out.println("The dealer reveals their hidden card: a " + hiddenCard.toString() + ".");
    System.out.println("Their total is " + (dealerCard.getValue() + hiddenCard.getValue()) + ".");
    dealerTotal = dealerCard.getValue() + hiddenCard.getValue();
    
    //dealer hits until total is at or above 17
    while (dealerTotal < 17) {
      nextCard = stack.pop();
      System.out.println("The dealer hits and is dealt a " + nextCard.toString() + ".");
      dealerTotal += nextCard.getValue();
      System.out.println("Dealer's total is now " + dealerTotal + ".");

      if (dealerTotal > 21) {
        System.out.println("Dealer busts! Their total is " + dealerTotal);
        return money + bet;
      }
    }//end of while loop

    if (dealerTotal >= 17) {
      if (dealerTotal > 21) {
        System.out.println("Dealer busts! Their total is " + dealerTotal);
        return money + bet;
      } else if (dealerTotal >= playerTotal) {
        System.out.println("Dealer wins with a total of " + dealerTotal + ".");
        return money - bet;
      } else {
        System.out.println("You win with a total of " + playerTotal + ".");
        return money + bet;
      }
    }

    return money;
  }
  
//gives intro to player
  public static void intro() {
    System.out.println("Welcome to the game of Blackjack!");
    System.out.println("You start with $" + STARTINGAMOUNT + ". Each round, you can place a bet of any amount.");
    System.out.println("If you get closer to 21 than the dealer without going over, you win!");
    System.out.println("Ready to play? Let's get started!");
    System.out.println();
  }

}//end of class
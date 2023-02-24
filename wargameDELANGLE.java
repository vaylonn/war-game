import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class wargameDELANGLE {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("-----");
      System.out.println("\nWelcome to the Crazy Eights card game!\n");

      // create deck of 52 cards
      ArrayList<String> deck = new ArrayList<>();
      String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
      String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
      for (String suit : suits) {
         for (String rank : ranks) {
            deck.add(rank + " of " + suit);
         }
      }

      // shuffle deck
      Collections.shuffle(deck);

      // divide deck in half
      ArrayList<String> p1Deck = new ArrayList<>(deck.subList(0, 25));
      ArrayList<String> p2Deck = new ArrayList<>(deck.subList(26, 51));
      ArrayList<String> p1DeckWon = new ArrayList<>();
      ArrayList<String> p2DeckWon = new ArrayList<>();
      
      // keep track of how many rounds have been played
      int roundsPlayed = 0;
      
      // keep track of the score of each player
      int scoreP1 = 0;
      int scoreP2 = 0;
      System.out.println("Do you want to play against the computer ? (press 1) \nor another player (press 2) ?");
      int mul = Integer.valueOf(scanner.nextLine());

      // play game until all cards have been used
      while (!p1Deck.isEmpty() && !p2Deck.isEmpty()) {
         
        // prompt users for action
         String p1Card;
         String p2Card;
         System.out.println("-----");
        
         // player 1 chooses a card
         while (true) {
            System.out.println("\nPlayer 1 !\nPress the index of the card to draw a card (1-" + (p1Deck.size() ) +"):");
            System.out.println("Here is your deck:");
            for (int i=0; i < p1Deck.size(); i++) {
               System.out.print((i+1) +": "+ p1Deck.get(i) + " | ");
            }
            System.out.println("");
		      int indexP1 = Integer.valueOf(scanner.nextLine());
            
            // check if the chosen index is within the bounds of the player's deck
            if (indexP1 < 1 || indexP1 > p1Deck.size()) {
               System.out.println("Your input is out of the boundaries of your deck !\nTry again...");
            } else {
               // if the index is valid, remove the card at that index from the player's deck and save it
               p1Card = p1Deck.remove(indexP1-1);
               break;
            }
         }

         // check if it plays the computer
         if (mul == 1) {
            p2Card = p2Deck.remove(0);
         } else {
            // player 2 chooses a card
            while (true) {
               System.out.println("\nPlayer 2 !\nPress the index of the card to draw a card (1-" + (p2Deck.size() )+"):");
               System.out.println("Here is your deck:");
               for (int i=0; i < p2Deck.size(); i++) {
                  System.out.print((i+1) +": "+ p2Deck.get(i) + " | ");
               }
               System.out.println("");
               int indexP2 = Integer.valueOf(scanner.nextLine()); 
                
                //same thing
                if (indexP2 < 1 || indexP2 > p2Deck.size()) {
                System.out.println("Your input is out of the boundaries of your deck !\nTry again...");
                } else {
                p2Card = p2Deck.remove(indexP2-1);
                break;
                }
            }
        }

         // display the chosen cards
         System.out.println("\nPlayer 1 drew " + p1Card);
         System.out.println("Player 2 drew " + p2Card);
         
         // compare ranks and award cards to winner
         int p1CardVal = findCardValue(p1Card);
         int p2CardVal = findCardValue(p2Card);
		   int scoreWar = 0;
         if (p1CardVal > p2CardVal) {
            // adds both cards to player 1's deck of won cards
            System.out.println("\nPlayer 1 wins round " + (roundsPlayed + 1) + " !");
            p1DeckWon.add(p1Card);
            p1DeckWon.add(p2Card);
            // update his score by adding the combined value of the two cards
			   scoreP1 += p1CardVal + p2CardVal;
            // displays the size of deck of won cards
			   System.out.println("\nSize p1 deck won: " + p1DeckWon.size());
            System.out.println("Size p2 deck won: " + p2DeckWon.size());
         } else if (p1CardVal < p2CardVal) {
            // same but for Player 2
            System.out.println("\nPlayer 2 wins round " + (roundsPlayed + 1) + " !");
            p2DeckWon.add(p1Card);
            p2DeckWon.add(p2Card);
			   scoreP2 += p1CardVal + p2CardVal;
			   System.out.println("\nSize p1 deck won: " + p1DeckWon.size());
            System.out.println("Size p2 deck won: " + p2DeckWon.size());
         } else {
            // war
            System.out.println("WAR!");
            ArrayList<String> warPile = new ArrayList<>();
            warPile.add(p1Card);
            warPile.add(p2Card);

            // keep drawing cards until there is a winner
            if (findCardValue(p1Card) == findCardValue(p2Card)){
               while (true) {

                  // check to make sure there are enough cards
                  if (p1Deck.size() < 2 || p2Deck.size() < 2) {
                     System.out.println("Not enough cards to continue war.");
                     break;
                  }
                  
                  
                  // draw 2 cards from each player
                  warPile.add(p1Deck.remove(0));
                  warPile.add(p1Deck.remove(0));
                  warPile.add(p2Deck.remove(0));
                  warPile.add(p2Deck.remove(0));
   
                  // add score
                  for (int i = 0; i <=4 ; i++){
                  String warCard = warPile.get(i);
                  int warCardVal = findCardValue(warCard);
                  scoreWar += warCardVal;
                  }
   
                  // display the cards
                  System.out.println("Player 1 drew: ");
                  for (int i = 2; i < 4; i++) {
                     System.out.println("\t" + warPile.get(i));
                  }
                  System.out.println("Player 2 drew: ");
                  for (int i = 4; i < 6; i++) {
                     System.out.println("\t" + warPile.get(i));
                  }
   
                  // get the last card from each player
                  p1Card = warPile.get(warPile.size() - 2);
                  p2Card = warPile.get(warPile.size() - 1);

                  // award cards to winner
                  if (findCardValue(p1Card) == findCardValue(p2Card)) {
                     System.out.println("\nThere is a tie !\nLet's draw cards again.");
                  
                  } else if (findCardValue(p1Card) > findCardValue(p2Card)){
                     // same as previously but add this time all the cards of the war
                     System.out.println("\nPlayer 1 wins round " + (roundsPlayed + 1) + "!");
                     p1DeckWon.addAll(warPile);
                     System.out.println("\nSize p1 deck won: " + p1DeckWon.size());
                     System.out.println("Size p2 deck won: " + p2DeckWon.size());
                     // and add so the combined score of the cards of the war
                     scoreP1 += scoreWar;
                     break;
                  } else {
                     // same but for player 2
                     System.out.println("\nPlayer 2 wins round " + (roundsPlayed + 1) + "!");
                     p2DeckWon.addAll(warPile);
                     System.out.println("\nSize p1 deck won: " + p1DeckWon.size());
                     System.out.println("Size p2 deck won: " + p2DeckWon.size());
                     scoreP2 += scoreWar;
                     break;
                  }
               }
            }
         }
            
         
         // increment number of rounds
         roundsPlayed++;
      }
      
      // declare winner
	  System.out.println("\nNo cards left in the decks !");
	  System.out.println("Let's find the winner !");
	  System.out.println("\nHere are you're scores:");
	  System.out.println("Player 1: " + scoreP1);
	  System.out.println("Player 2: " + scoreP2);
	  
	  if (scoreP1 > scoreP2) {
		System.out.println("\nPlayer 1 wins the game!");
	  } else {
		System.out.println("\nPlayer 2 wins the game !");
	  }
      scanner.close();
   }

   // returns the value of the card
   public static int findCardValue(String card) {
      String valueStr = card.substring(0, card.indexOf(" of"));
      switch (valueStr) {
         case "Jack":
            return 11;
         case "Queen":
            return 12;
         case "King":
            return 13;
         case "Ace":
            return 14;
         default:
            return Integer.parseInt(valueStr);
      }
   }
}
import java.util.*;
 /**
  * This class performs shuffling, distribution and operations on the cards used in the card game. 
  * It also handles cards of the dealer and the player, and evaluates the winner of the game. 
  * 
  * @author Siddharth Agarwal
  * @version 1.0
  */
abstract class Cards implements Players{
	
	
	ArrayList<Integer> card=new ArrayList<Integer>();
	protected double bet;
	protected int amt = 100;
	protected int c;
	
	/**
	 * This function adds 52 cards in the ArrayList card which represents a deck of cards.
	 * It is called at the beginning of every round and makes sure the dealer and player have no cards in the beginning of the round.
	 */
	protected void pack() {
		for(int i=1;i<=52;++i) {
			card.add(i);
		}
		player.removeAll(player);
		dealer.removeAll(dealer);
		
	}
	
	/**
	 * The method uses the Collections class in java.util package to randomly shuffle the deck of cards.
	 */
	protected void shufflecards() {
		Collections.shuffle(card);
	}
	
	/**
	 * The method finds the suit and rank of a card at a specified position in our deck of cards.
	 * 
	 * @param i The specified index of the card in the ArrayList card.
	 * @return A String which represents the suit and number of the card.
	 */
	protected String color(int i) {
		int x = card.get(i) / 13 + 1;
		int y = card.get(i) % 13;
		if (y == 0) {
			y = 13;
			x--;
		}
		return x+""+y;
		
	}
	
	/**
	 * This method calculates the total number of special cards of the dealer or the player, as required.
	 * 
	 * @param t An ArrayList which represents the cards of the dealer or the player
	 * @return An integer value representing the total number of special cards of the dealer or the player.
	 */
	public int specialVal(ArrayList<Integer> t) {
		int c=0;
		for(Integer i:t) {
			if(i%13==0 || i%13>10)
				c++;
		}
		return c;
	}
	
	/**
	 * This method adds the face value of the general(not special) card(s), divides the sum by 10 and calculates the remainder.  
	 * 
	 * @param t An ArrayList which represents the cards of the dealer or the player
	 * @return An integer value representing the remainder obtained by dividing the sum of the face value of general cards by 10.
	 */
	public int faceVal(ArrayList<Integer> t) {
		int c=0;
		for(Integer i:t) {
			if(i%13>0 && i%13<=10)
				c+=i%13;
		}
		return c%10;
	}
	
	/**
	 * This method evaluates the winner of the game by comparing the number of special cards of the dealer and the player,
	 * followed by further evaluation of general cards if the former comparison yields no concrete result.
	 * 
	 * @return A boolean value, true if the player wins and false if the dealer wins.
	 */
	protected boolean winner() {
		card.removeAll(card);
		if(specialVal(dealer)>specialVal(player))
			return false;
		else if(specialVal(dealer)==specialVal(player)) {
			if(faceVal(dealer)>=faceVal(player))
				return false;
		}
		return true;
	}
	 
}


/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.util.RandomGenerator;
import java.awt.*;

public class Hangman extends ConsoleProgram {
	RandomGenerator rand = RandomGenerator.getInstance();
	int n = 0;
	int HEART = 8;
	String XWORD = "";
	String XWORDFORTIME = "";
	boolean BOOLEAN = true;
	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	int t = -1;

	public void run() {
		/* You fill this in */
		startGame();
		println("game over");
	}

	// this is body of the game
	private void startGame() {
		BOOLEAN=true;
		HangmanLexicon lexicon = new HangmanLexicon();
		int a =lexicon.getWordCount();
		while (true) {
			if (t == 0)
				break;
			canvas.reset();
			
			int number = rand.nextInt(0, a);
			String word = lexicon.getWord(number);
			int n = word.length();
			begin(n);
			gamePlay(word);
		}

	}

// this is for start games, here I wrote some println, that game writes only one times
	private void begin(int n) {
		println("Wellcome to Hangman!");
		XWordStart(n);
		println("The word now looks like this: " + XWORD);
	}

// this is cipher for word that we need to guess.
	private void XWordStart(int n) {
		XWORD = "";
		for (int i = 0; i < n; i++) {
			XWORD += "-";
			canvas.displayWord(XWORD);
		}
	}

// here I wrote some code, that if we guess a word he rewrotes cipher 
	private void XWord(char b, String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == b) {
				XWORDFORTIME += word.charAt(i);
			} else {
				XWORDFORTIME += XWORD.charAt(i);
			}
		}
		XWORD = XWORDFORTIME;
		XWORDFORTIME = "";
	}

// here is basic code of the game
	private void gamePlay(String word) {
		while (XWORD.contains("-")) {
			println("You have " + HEART + " guesses left.");
			String a;
			char b;
			// this is when computer ask a guess
			//if you don't wrote a letter or worte more then one letter
			// he will be ask again
			while (true) {
				a = readLine("Your guess: ").toUpperCase(); // here i wrote toUpperCase for the letter
				b = a.charAt(0);
				if (Character.isLetter(b) && a.length() == 1)
					break;
				if (a.length() != 1) {
					println("wrote only one letter");
				} else {
					println("it is not letter");
				}
			}
			// this code is for that time, if here is this letter
			if (word.contains(a)) {
				println("That guess is correct");
				XWord(b, word);
				println("The word now looks like this: " + XWORD);
				canvas.displayWord(XWORD);
			} else {
				// here is code that do something if in this word is not this letter
				println("There are no " + a + "'s in the word.");
				HEART--;
				println("The word now looks like this: " + XWORD);
				canvas.noteIncorrectGuess(b);
				// this code is for that time, if you have not heart anymore
				if (HEART == 0) {
					println("");
					println("Yor're completely hung.");
					println("The word was: " + word);
					println("You lose.");
					HEART = 8;
					BOOLEAN = false;
					break;
				}
			}
		}
		// here is code that prints you win when you wrote a word
		if (BOOLEAN) {
			println("You win.");
		}
		// and here game asks you do you want start a game again
		t = readInt("Do you want start again? if no 0 else other number: ");
		HEART = 8;
	}

}

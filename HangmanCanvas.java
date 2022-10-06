/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removes();
		gallows();
		HEART=8;
	}
	
	//here I wrote gallow 
	private void gallows() {
		if(t) {
			GLine lineOne = new GLine(getWidth()/2 -BEAM_LENGTH, d,getWidth()/2-BEAM_LENGTH ,d+SCAFFOLD_HEIGHT);
		add(lineOne);
		GLine lineTwo = new GLine(getWidth()/2 - BEAM_LENGTH,d, getWidth()/2,d);
		add(lineTwo);
		GLine lineThree = new GLine(getWidth()/2, d, getWidth()/2,d+ROPE_LENGTH);
		add(lineThree);
		t=false;
		}
		
	}
	boolean t = true;
	// here I remove everything that i paint in the past
	private void removes() {
		if(label!=null)remove(label);
		if(haveNot!=null)remove(haveNot);
		letters="";
		if(head!=null)remove(head);
		if(body!=null)remove(body);
		if(leftArmOne !=null)remove(leftArmOne);
		if(leftArmTwo !=null)remove(leftArmTwo);
		if(rightArmOne !=null)remove(rightArmOne);
		if(rightArmTwo !=null)remove(rightArmTwo);
		if(leftLegOne !=null)remove(leftLegOne);
		if(leftLegTwo !=null)remove(leftLegTwo);
		if(rightLegOne !=null)remove(rightLegOne);
		if(rightLegTwo !=null)remove(rightLegTwo);
		if(leftFoot!=null)remove(leftFoot);
		if(rightFoot!=null)remove(rightFoot);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	
	public void displayWord(String word) {
		/* You fill this in */
		if(label!=null)remove(label);
		label = new GLabel(word);
		label.setFont(new Font("verdana", Font.BOLD, 15));
		add(label, getWidth()/2 -BEAM_LENGTH,3*d+SCAFFOLD_HEIGHT);
	}
	GLabel label;

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		if(contains(letter, letters)||letters.isEmpty()) {
			letters = letters + letter + ";";
		}
		if(haveNot!=null)remove(haveNot);
		haveNot = new GLabel (letters);
		add(haveNot,getWidth()/2 -BEAM_LENGTH,3*d+SCAFFOLD_HEIGHT + label.getAscent() );
		die();
	}
	// here I wrote code that watch is this letter before
	private boolean contains(char a, String b) {
		for(int i=0; i<b.length();i++) {
			if(b.charAt(i)==a) {
				return false;
			}
		}
		return true;
	}
	// here, if you answer incorrect, program will be wrote hangamns part 
	private void die() {
		if(HEART==8) {
			head();
		}else if(HEART==7){
			body();
		}else if(HEART==6) {
			leftArm();
		}else if(HEART==5) {
			rightArm();
		}else if(HEART==4){
			leftLeg();
		}else if(HEART==3) {
			rightLeg();
		}else if(HEART==2) {
			leftFoot();
		}else {
			rightFoot();
		}
		HEART--;
	}
	
	// here is hangmans part of the body
	int HEART=8;
	GLabel haveNot ;
	String letters = "";
	GOval head;
	private GOval head() {
		head = new GOval(getWidth()/2 -HEAD_RADIUS,d+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2 );
		add(head);
		return head;
	}
	GLine body;
	private GLine body() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS;
		body = new GLine(getWidth()/2, a, getWidth()/2, a + BODY_LENGTH);
		add(body);
		return body;
	}
	GLine leftArmOne;
	GLine leftArmTwo;
	private void leftArm() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS+ ARM_OFFSET_FROM_HEAD;
		leftArmOne = new GLine(getWidth()/2,a, getWidth()/2 - UPPER_ARM_LENGTH, a );
		add(leftArmOne);
		leftArmTwo =new GLine(getWidth()/2 - UPPER_ARM_LENGTH, a, getWidth()/2 - UPPER_ARM_LENGTH,a+LOWER_ARM_LENGTH);
		add(leftArmTwo);
	}
	GLine rightArmOne;
	GLine rightArmTwo;
	private void rightArm() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS+ ARM_OFFSET_FROM_HEAD;
		rightArmOne = new GLine(getWidth()/2,a, getWidth()/2 + UPPER_ARM_LENGTH, a );
		add(rightArmOne);
		rightArmTwo =new GLine(getWidth()/2 + UPPER_ARM_LENGTH, a, getWidth()/2 + UPPER_ARM_LENGTH,a+LOWER_ARM_LENGTH);
		add(rightArmTwo);
	}
	GLine leftLegOne;
	GLine leftLegTwo;
	private void leftLeg() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH;
		leftLegOne = new GLine(getWidth()/2,a, getWidth()/2-HIP_WIDTH, a);
		add(leftLegOne);
		leftLegTwo = new GLine( getWidth()/2-HIP_WIDTH, a, getWidth()/2-HIP_WIDTH, a +LEG_LENGTH );
		add(leftLegTwo);
	}
	GLine rightLegOne;
	GLine rightLegTwo;
	private void rightLeg() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH;
		rightLegOne = new GLine(getWidth()/2,a, getWidth()/2+HIP_WIDTH, a);
		add(rightLegOne);
		rightLegTwo = new GLine( getWidth()/2+HIP_WIDTH, a, getWidth()/2+HIP_WIDTH, a +LEG_LENGTH );
		add(rightLegTwo);
	}
	GLine leftFoot;
	private GLine leftFoot() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH+LEG_LENGTH;
		int b = getWidth()/2-HIP_WIDTH;
		leftFoot = new GLine(b,a, b- FOOT_LENGTH,a);
		add(leftFoot);
		return leftFoot;
	}
	GLine rightFoot;
	private GLine rightFoot() {
		int a = d+ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH+LEG_LENGTH;
		int b = getWidth()/2+HIP_WIDTH;
		rightFoot = new GLine(b,a, b+FOOT_LENGTH,a);
		add(rightFoot);
		return rightFoot;
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	// this statics I wrote 
	private static final int d =15;

}

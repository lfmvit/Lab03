package it.polito.tdp.spellchecker.model;

public class RichWord {
	String insertedWord;
	boolean check;
	public RichWord(String insertedWord) {
	
		this.insertedWord = insertedWord;
		this.check = false;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return insertedWord + "\n";
	}

	
}

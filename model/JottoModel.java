package model;

import java.util.ArrayList;
import java.io.File;
import java.lang.String;
import java.lang.Character;

public class JottoModel extends Object {
	public static int NUM_LETTERS = 5;
	public static final String[] LEVELS = {
      "Easy", "Medium", "Hard", "Any Difficulty"};

    private ArrayList<IView> views;
    private ArrayList<Integer> skips;
    private ArrayList<String> guessList;
    private WordList wordList;
    
    private Word target;
    private String targetWord;
    private String guessWord;
    private int guessCount;
    private int exact;
    private int partial;
    private int difficulty;
    private int lifeCount;
    
    public JottoModel(){
    	views = new ArrayList<IView>();
    	guessList = new ArrayList<String>();
    	skips = new ArrayList<Integer>();
    	wordList = new WordList("model/words.txt"); 
    }

    public void setTarget() {
    	if(this.difficulty == 3) target = wordList.randomWord();
    	else target = wordList.randomWord(this.difficulty);
    	targetWord = target.getWord();
    }
    
    public void setDifficulty(int difficulty){
    	this.difficulty = difficulty;
    	this.lifeCount = 10;
    }

    public String getWord(){
    	return this.targetWord;
    }
    
    public Character getHint(){
        int randomIdx = (int)((Math.random() * 10) % NUM_LETTERS);
        return this.targetWord.charAt(randomIdx);
    }

    public String getLevel(){
    	return JottoModel.LEVELS[this.difficulty];
    }
    
    public int getLifeCount(){
    	return this.lifeCount;
    }
    
    public boolean isAlreadyGuessed(String g){
    	return guessList.contains(g);
    }
    
    public boolean isGuessUpperCaseLetters(String g){
        for(char c : g.toCharArray())
        {
            if(! Character.isUpperCase(c))
                return false;
        }
        return true;
    }
    
    public void setGuess(String g){
   		this.guessCount++;
    	this.guessList.add(g);
   		this.guessWord = g;
   		this.exact = 0;
   		this.partial = 0;
   		this.checkMatch(g);
   		this.decrementLife();
   		this.updateAllViews();
 	}
        
    public void checkMatch(String g){
    	// if you have guessed the answer,
    	if(this.targetWord.matches(g)){
    		this.exact = 5;
    		this.partial = 0;
    		return;
    	}
    	// find the number of exact matches
    	for(int i=0; i<NUM_LETTERS; i++){
    		if(!skips.contains(i) && targetWord.charAt(i) == g.charAt(i)){
    			exact++;
    			skips.add(i);
    		}
    	}
    	// find the number of partial matches
    	for(int i=0; i<NUM_LETTERS; i++){
    		for(int j=0; j<NUM_LETTERS; j++){
    			if(skips.contains(j)){
    				continue;
    			}
    			else if(targetWord.charAt(i) == g.charAt(j)){
    				partial++;
    				skips.add(j);
    				break;
    			}
    		}
    	}
    	skips.clear();
    }
    
    public void decrementLife(){
    	this.lifeCount--;
    }
    
    public int getGuessCount(){
    	return this.guessCount;
    }
    
    public String getGuessWord(){
    	return this.guessWord;
    }
    
    public int getExact(){
    	return this.exact;
    }
    
    public int getPartial(){
    	return this.partial;
    }
 
	/** Add a new view of this triangle. */
	public void addView(IView view) {
		this.views.add(view);
		view.updateView();
	}

	/** Remove a view from this triangle. */
	public void removeView(IView view) {
		this.views.remove(view);
	}

	/** Update all the views that are viewing this triangle. */
	private void updateAllViews() {
		for (IView view : this.views) {
			view.updateView();
		}
	}
}

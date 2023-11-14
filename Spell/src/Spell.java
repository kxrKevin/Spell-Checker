import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;


public class Spell {

	private static Hashtable<String, Integer> dictionary;
	private static String[] wordsToCheck;

    Spell(String s, String k){
    	
        // Load dictionary words from file into Hashtable
    	try {
    		
    		// Setups for Files and Scanners
    		File dFile = new File(s);
    		File cFile = new File(k);
    		
    		Scanner dScanner = new Scanner(dFile);
    		Scanner sizeScanner = new Scanner(cFile);
    		Scanner cScanner = new Scanner(cFile);
    		
    		// Load dictionary words onto hashtable
    		dictionary = new Hashtable<>();
    		while(dScanner.hasNextLine()) {
    			dictionary.put(dScanner.nextLine(), 1);
    		}
    		
    		// Load fileToCheck words onto String array
    		
    		// Determine size of string array
    		int size = 0;
    		while(sizeScanner.hasNextLine()) {
    			String waste = sizeScanner.nextLine();
    			size++;
    		}
    		// Create String array and load fileToCheck words into array
    		wordsToCheck = new String[size];
 
    		for(int i = 0; i < size; i++) {
    			wordsToCheck[i] = cScanner.nextLine().toLowerCase();
    		}
    		
    		dScanner.close();
    		sizeScanner.close();
    		cScanner.close();
    	}
    	
    	catch(FileNotFoundException e) {
    		System.out.println("Can't Find File");
    	}

    }

    public static void main(String[] args) {
    	
        // init an object of type Spell
        Spell spell = new Spell(args[0], args[1]);
        
        // Check spelling for String Array
        for(int x = 0; x < wordsToCheck.length; x++) {
        	
        	// Displays word
        	System.out.print(wordsToCheck[x] + ": ");
        	
        	// Determines if word belongs in dictionary
        	if(checkSpelling(wordsToCheck[x])) {
        		System.out.println("Correct Spelling");
        	}
        	else {
        		System.out.println("Incorrect Spelling");
        		
        		if(suggestCorrections(wordsToCheck[x]) == false) {
        			System.out.println("No Suggestions");
        		}
        	}
        	
        	System.out.println("\n");
        	
        }
    }
    
    // this function check if the dictionay is loaded or not
    public static Hashtable<String, Integer> getDictionary(){
        return dictionary;
    }
    
    // Returns a boolean value that verifies whether or not a given word belongs to the dictionary
    public static boolean checkSpelling(String word) {
    	
    	// Converts input word to lowercase
    	word = word.toLowerCase();
    	
    	// Checks if given word belongs in the dictionary
    	if(dictionary.containsKey(word)) {
    		return true;
    	}
    	return false;
    }
    
    // When called, this method will call individual spelling sugestions
    public static boolean suggestCorrections(String word) {
    	// This boolean will stay false, which indicate that no suggestion has worked
    	boolean suggestion = false;
    	// Each suggestion will return a list of corrections in the form of a string
    	// If the string is empty, it means that no corrections have been made using the suggestion
    	// If the string output is NOT empty, there is a least one existing correction
    	if(!(correctSpellingSubstitution(word).isEmpty())) {
    		suggestion = true;
    	}
    	if(!(correctSpellingWithOmission(word).isEmpty())) {
    		suggestion = true;
    	}
    	if(!(correctSpellingWithInsertion(word).isEmpty())) {
    		suggestion = true;
    	}
    	if(!(correctSpellingWithReversal(word).isEmpty())) {
    		suggestion = true;
    	}
    	return suggestion;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and 
    // check if the resulting new word is in the dictionary.
    static String correctSpellingSubstitution(String word) {
    	
    	String correct = "";
    	
    	// Iterates over every letter 
    	for(int i = 0; i < word.length(); i++) {
    		
    		// Refresh the word as a character array
    		char[] charArray = word.toCharArray();
    		
    		// Iterates every letter in the character array through the alphabet
    		for(char charSwap = 'a'; charSwap <= 'z'; charSwap++) {
    			// Swaps the i index of the char array with a letter from the alphabet
    			charArray[i] = charSwap;
    			// Creates a string out of the new array
    			String result = new String(charArray);
    			// Checks if the current letter change results in a match with the dictionary
    			if(dictionary.containsKey(result)) {
    				// if so, add to the list of corrections in the form of a string
    				correct = correct.concat(result) + ", ";	
    			}
    		}
    	}
    	
    	// Display corrections
    	System.out.println("Substitution Corrections --> " + correct);
        return correct;
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithOmission(String word) {
    	
    	String correct = "";
    	
    	// Iterates over every letter 
    	for(int i = 0; i < word.length(); i++) {
    		
    		// Create stringBuilder object 
    		StringBuilder sb = new StringBuilder(word);
    		// Deletes char at index i
    		sb.deleteCharAt(i);
    		// Form string out of stringBuilder object
    		String result = sb.toString();
    		
    		// Checks if letter removal results in a match with the dictionary
    		if(dictionary.containsKey(result) && !(correct.contains(result))) {
    			// if so, add to the list of corrections in the form of a string
    			correct = correct.concat(result) + ", ";	
    		}
    	}
    	// Display corrections
    	System.out.println("Omission Corrections --> " + correct);
    	return correct;
    }

    // This function tries to insert a letter in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingWithInsertion(String word) {
        
    	// Create an string arrayList that includes all the correct words
    	ArrayList<String> correct = new ArrayList<String>();
    	
    	// Iterate through the length of the word
    	for(int i = 0; i < word.length() + 1; i++) {
    		
    		String result;
    		
    		// Iterate through the alphabet
    		for(char charSwap = 'a'; charSwap <= 'z'; charSwap++) {
    			
    			// Include the inserted letter into a result string
    			result = word.substring(0, i) + charSwap + word.substring(i);
    			
    			// Test the result string
    			if(dictionary.containsKey(result) && !(correct.contains(result))) {
    				// if so, add to the list of corrections 
    				correct.add(result);
    			}
    		}    		
    	}
    	// Display corrections
    	System.out.println("Insertion Corrections --> " + correct);
    	return correct;
    }
    
    // This function tries swapping every pair of adjacent characters 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithReversal(String word) {
       
    	String correct = "";
    	
    	// Iterate through word length - 1
    	for(int i = 0; i < word.length() - 1; i++) {
    		
    		// Create char variables that swap characters within charArray
    		char[] charArray = word.toCharArray();
    		char a = charArray[i];
    		char b = charArray[i+1];
    		charArray[i] = b;
    		charArray[i+1] = a;
    		
    		// Convert array into String
    		String result = new String(charArray);
    		
    		// Test if result matches with a dictionary word
    		if(dictionary.containsKey(result) && !(correct.contains(result))) {
    			// if so, add to the list of corrections 
    			correct = correct.concat(result) + ", ";
			}	
    	}
    	// Display corrections
    	System.out.println("Reversal Corrections --> " + correct);
    	return correct;
    }
}
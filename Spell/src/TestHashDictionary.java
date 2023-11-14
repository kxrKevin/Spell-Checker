
import java.util.Hashtable;

public class TestHashDictionary {

    public static void main(String[] args) {
        Spell spell = new Spell(args[0],args[1]);

        // Test case 1: Check if dictionary is not empty
        System.out.println("++++++++++++++++++++++++++");
        if (spell.getDictionary().size() > 0) {
            System.out.println("Test case 1: Passed, the dictionary is not empty");
        } else {
            System.out.println("Test case 1: Failed, the dictionary is empty");
        }

        // Test case 2: Check if dictionary is loaded properly
        System.out.println("++++++++++++++++++++++++++");
        if (spell.checkSpelling("cats")) {
            System.out.println("Test case 2: Passed, the dictionary is loaded correctly");
        } else {
            System.out.println("Test case 2: Failed, the dictionary doesn't loaded correctly");
        }

        // Test case 3: Check if correct spelling returns correct result
        System.out.println("++++++++++++++++++++++++++");
        String word = "hello";
        if (spell.checkSpelling(word) == true) {
            System.out.println("Test case 3: Passed, the given word has been identified correctly");
        } else {
            System.out.println("Test case 3: Failed, the program failed to identify the given word");
        }

        // Test case 4: Check if incorrect spelling returns correct suggestions by insertion
        System.out.println("++++++++++++++++++++++++++");
        word = "pla";
        if (spell.suggestCorrections(word) == true) {
            System.out.println("Test case 4: Passed, the program was able to suggest a new word for the misspelled word");
        } else {
            System.out.println("Test case 4: Failed, the program was not able to suggest a new word for the misspelled word");
        }

        // Test case 5: Check if incorrect spelling returns correct suggestions by reversal
        System.out.println("++++++++++++++++++++++++++");
        word = "paernt";
        if (spell.suggestCorrections(word) == true) {
            System.out.println("Test case 5, Passed");
        } else {
            System.out.println("Test case 5: Failed");
        }

        // Test case 6: Check if incorrect spelling returns correct suggestions by omission
        System.out.println("++++++++++++++++++++++++++");
        word = "catt";
        if (spell.suggestCorrections(word) == true) {
            System.out.println("Test case 6, Passed");
        } else {
            System.out.println("Test case 6: Failed");
        }

        // Test case 7: Check if incorrect spelling returns correct suggestions by replacement
        System.out.println("++++++++++++++++++++++++++");
        word = "hlelo";
        if (spell.suggestCorrections(word) == true) {
            System.out.println("Test case 7, Passed");
        } else {
            System.out.println("Test case 7: Failed");
        }

        // Test case 8: Check if incorrect spelling returns correct suggestions by transposition
        System.out.println("++++++++++++++++++++++++++");
        word = "hlelo";
        if (spell.suggestCorrections(word) == true) {
            System.out.println("Test case 8: Passed, the program was able to suggest a new word for the misspelled word");
        } else {
            System.out.println("Test case 8: Failed, the program was not able to suggest a new word for the misspelled word");
        }

        // Test case 9: Check if the spell checker is case-insensitive
        System.out.println("++++++++++++++++++++++++++");
        word = "Hello";
        if (spell.checkSpelling(word) == true) {
            System.out.println("Test case 9: Passed, the program correctly handled case sensitivity");
        } else {
            System.out.println("Test case 9: Failed, the program failed to handle case sensitivity");
        }

        // Test case 10: Check if the spell checker can handle numbers and special characters
        System.out.println("++++++++++++++++++++++++++");
        word = "12345";
        if (spell.checkSpelling(word) == false) {
            System.out.println("Test case 10: Passed, the program correctly handled numbers and special characters");
        } else {
            System.out.println("Test case 10: Failed, the program failed to handle numbers and special characters");
        }
    }
}

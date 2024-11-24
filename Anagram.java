/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		boolean checkAnagram = true;

		String str1Process = "";
		String str2Process = "";

		for (int i = 0; i < preProcess(str1).length(); i++){
			if ((preProcess(str1).charAt(i) >= 97) && (preProcess(str1).charAt(i) <= 122)) str1Process += preProcess(str1).charAt(i);
		}

		for (int i = 0; i < preProcess(str2).length(); i++){
			if ((preProcess(str2).charAt(i) >= 97) && (preProcess(str2).charAt(i) <= 122)) str2Process += preProcess(str2).charAt(i);
		}

		if (str1Process.length() != str2Process.length()) checkAnagram = false;

		for (int i = 0; i < str1Process.length(); i++) if (str2Process.indexOf(str1Process.charAt(i)) == -1) checkAnagram = false;
		for (int i = 0; i < str2Process.length(); i++) if (str1Process.indexOf(str2Process.charAt(i)) == -1) checkAnagram = false;

		return checkAnagram;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String strProcess = "";
		for (int i = 0; i < str.length(); i++){
			char charProcess = str.charAt(i);
			if (((charProcess >= 97) && (charProcess <= 122)) || (charProcess == 32)) strProcess += charProcess;
			if ((charProcess >= 65) && (charProcess <= 90)) strProcess += (char) (charProcess + 32);
		}
		return strProcess;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String processStr = preProcess(str);
		String random = "";
		while (processStr.length() > 0){
			int index = (int)(Math.random() * processStr.length());
			random += processStr.charAt(index);
			processStr = processStr.substring(0, index) + processStr.substring(index + 1);
		}
		return random;
	}
}

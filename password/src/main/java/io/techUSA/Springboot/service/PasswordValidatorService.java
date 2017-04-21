package io.techUSA.Springboot.service;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidatorService {
	

	    public static final String ERROR_PASSWORD_LENGTH = "Password must be betwee 5 and 12 characters long.";
	    public static final String ERROR_PASSWORD_CASE = "Password must only contain lowercase letters.";
	    public static final String ERROR_LETTER_AND_DIGIT = "Password must contain both a letter and a digit.";
	    public static final String ERROR_PASSWORD_SEQUENCE_REPEATED = "Password must not contain any sequence of characters immediately followed by the same sequence.";
	    public static final String VALID_PASSWORD = "Password is valid";
	    public static final String ERROR_LAST_THREE_PASSWORDS = "Password should not contain last three passwords";
	
	    public static  List<String> lastThreePasswordsList= new LinkedList<String>();

	    private Pattern checkCasePattern = Pattern.compile("[A-Z]");
	    private Pattern checkLetterAndDigit = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+");
	    private Pattern checkSequenceRepetition = Pattern.compile("(\\w{1,})\\1");

	    public Set<String> validate(String password) {
	        Set<String> failures = new HashSet<String>();
	        checkLength(password, failures);
	        checkCase(password, failures);
	        checkLetterAndDigit(password, failures);
	        checkSequenceRepetition(password, failures);
	        if(failures.size()==0){
	         storeLasthreeRecentPasswords(password,failures);
	        }
	        if(failures.size()==0) failures.add(VALID_PASSWORD);
	       return failures;
	    }
	    
	    public void storeLasthreeRecentPasswords(String password,Set<String> failures){
	    	if (lastThreePasswordsList.size()<=2 && !lastThreePasswordsList.contains(password)){
	    		lastThreePasswordsList.add(password);
	    	}else if(lastThreePasswordsList.size()>=3 && !lastThreePasswordsList.contains(password)){
	    		lastThreePasswordsList.remove(0);
	    		lastThreePasswordsList.add(password);
	    	}else{
	    		failures.add(ERROR_LAST_THREE_PASSWORDS);
	    	}
	    
	    	
	    }

	    private void checkSequenceRepetition(String password, Set<String> failures) {
	        Matcher matcher = checkSequenceRepetition.matcher(password);
	        if (matcher.find()) {
	            failures.add(ERROR_PASSWORD_SEQUENCE_REPEATED);
	        }
	    }

	    private void checkLetterAndDigit(String password, Set<String> failures) {
	        Matcher matcher = checkLetterAndDigit.matcher(password);
	        if (!matcher.find()) {
	            failures.add(ERROR_LETTER_AND_DIGIT);
	        }

	    }

	    private void checkCase(String password, Set<String> failures) {
	        Matcher matcher = checkCasePattern.matcher(password);
	        if (matcher.find()) {
	            failures.add(ERROR_PASSWORD_CASE);
	        }

	    }

	    private void checkLength(String string, Set<String> failures) {
	        if (string.length() < 5 || string.length() > 12) {
	            failures.add(ERROR_PASSWORD_LENGTH);
	        }
	    }
	    
	  
}



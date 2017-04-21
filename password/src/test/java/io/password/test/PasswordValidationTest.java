package io.password.test;

	
	import static io.techUSA.Springboot.service.PasswordValidatorService.ERROR_LETTER_AND_DIGIT;
import static io.techUSA.Springboot.service.PasswordValidatorService.ERROR_PASSWORD_CASE;
import static io.techUSA.Springboot.service.PasswordValidatorService.ERROR_PASSWORD_LENGTH;
import static io.techUSA.Springboot.service.PasswordValidatorService.ERROR_PASSWORD_SEQUENCE_REPEATED;
import static org.hamcrest.MatcherAssert.assertThat;
	import static org.hamcrest.Matchers.hasItem;
	import static org.junit.Assert.assertFalse;

	import java.util.Set;

	import org.junit.Before;
	import org.junit.Test;

import io.techUSA.Springboot.service.PasswordValidatorService;

	public class PasswordValidationTest {
	    private PasswordValidatorService passwordService;

	    @Before
	    public void getServiceInstance() {
	    	passwordService=new PasswordValidatorService(); 
	    }

	    @Test
	    public void testContainsOnlyLowercaseLetters() {
	        // act
	        Set<String> result = passwordService.validate("abcde");

	        // verify
	        assertFalse(result.contains(ERROR_PASSWORD_CASE));
	    }

	    @Test
	    public void testContainsUppercaseLetters() {
	        // act
	        Set<String> result = passwordService.validate("Xbcde");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_CASE));
	    }

	    @Test
	    public void testContainsBothLetterAndDigit() {
	        // act
	        Set<String> result = passwordService.validate("a0");

	        // verify
	        assertFalse(result.contains(ERROR_LETTER_AND_DIGIT));
	    }

	    @Test
	    public void testContainsBothDigitAndLetter() {
	        // act
	        Set<String> result = passwordService.validate("0a");

	        // verify
	        assertFalse(result.contains(ERROR_LETTER_AND_DIGIT));
	    }

	    @Test
	    public void testContainsOnlyLetters() {
	        // act
	        Set<String> result = passwordService.validate("a");

	        // verify
	        assertThat(result, hasItem(ERROR_LETTER_AND_DIGIT));
	    }

	    @Test
	    public void testContainsOnlyDigits() {
	        // act
	        Set<String> result = passwordService.validate("0");

	        // verify
	        assertThat(result, hasItem(ERROR_LETTER_AND_DIGIT));
	    }

	    @Test
	    public void testSize5orMore() {

	        // act
	        Set<String> result = passwordService.validate("12345");

	        // verify
	        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
	    }

	    @Test
	    public void testSizeLessThan5() {

	        // act
	        Set<String> result = passwordService.validate("1234");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_LENGTH));
	    }

	    @Test
	    public void testSize12orLess() {

	        // act
	        Set<String> result = passwordService.validate("123456789112");

	        // verify
	        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
	    }

	    @Test
	    public void testSizeMoreThan12() {

	        // act
	        Set<String> result = passwordService.validate("1234567891123");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_LENGTH));
	    }

	    @Test
	    public void testSequenceNotViolated() {

	        // act
	        Set<String> result = passwordService.validate("abcde12345");

	        // verify
	        assertFalse(result.contains(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }

	    @Test
	    public void testSequenceRepeatLetters() {

	        // act
	        Set<String> result = passwordService.validate("abab");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }

	    @Test
	    public void testSequenceRepeatSingleLetter() {

	        // act
	        Set<String> result = passwordService.validate("abbbef123456");

	        // verify
	        assertThat(result,hasItem(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }

	    @Test
	    public void testSequenceRepeatLettersAndDigits() {

	        // act
	        Set<String> result = passwordService.validate("ab1ab1");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }

	    @Test
	    public void testSequenceRepeatLettersNotAtFront() {

	        // act
	        Set<String> result = passwordService.validate("prefixabab");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }

	    @Test
	    public void testSequenceRepeatLettersNotAtBack() {

	        // act
	        Set<String> result = passwordService.validate("ababpostfix");

	        // verify
	        assertThat(result, hasItem(ERROR_PASSWORD_SEQUENCE_REPEATED));
	    }



}

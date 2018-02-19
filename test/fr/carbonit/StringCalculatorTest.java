package fr.carbonit;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void add_empty_string_should_return_zero() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(0, stringCalculator.add(""));
	}
	
	@Test
	public void add_one_string_should_return_one() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(1, stringCalculator.add("1"));
	}
	
	@Test
	public void add_one_and_two_with_comma_should_return_three() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(3, stringCalculator.add("1,2"));
	}
	
	@Test
	public void add_with_comma_and_line_break_should_return_sum_of_numbers() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("1\n2,3"));
	}
	
	@Test
	public void add_with_custom_delimiter_should_return_sum_of_numbers() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("//;\n1,2;3"));
	}
	
	@Test
	public void add_should_return_exception_when_one_number_is_negative() throws Exception {
		expectedEx.expect(ForbiddenNegativeNumberException.class);
	    expectedEx.expectMessage("Les nombres négatifs ne sont pas autorisés");
	    
		StringCalculator stringCalculator = new StringCalculator();
		stringCalculator.add("-5,2,9");
	}
	
	@Test
	public void add_should_return_exception_when_some_numbers_are_negatives() throws Exception {
		expectedEx.expect(ForbiddenNegativeNumberException.class);
	    expectedEx.expectMessage("Les nombres négatifs ne sont pas autorisés : -5, -10");
	    
		StringCalculator stringCalculator = new StringCalculator();
		stringCalculator.add("-5,2,-10,9");
	}
	
	@Test
	public void add_should_ignore_numbers_upper_than_thousand() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(15, stringCalculator.add("5,10,1664"));
	}
	
	@Test
	public void add_with_several_characters_custom_delimiter_should_return_sum_of_numbers() throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(9, stringCalculator.add("//[***]\n2***3***4"));
	}
}

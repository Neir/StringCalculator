package fr.carbonit;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalculator {
	
	private static final String CUSTOM_SEPARATOR_SYMBOL = "//";
	private static final int START_CUSTOM_SEPARATOR = 2;
	private static final int END_CUSTOM_SEPARATOR = 3;
	private static final int NUMBER_LIMIT = 1000;

	public int add(String text) throws ForbiddenNegativeNumberException {
		String regex = ",|\n";
		if(text.isEmpty()) {
			return 0;
		}
		
		StringBuilder textNumber = new StringBuilder(text);
		regex = regex.concat(findCustomSeparator(textNumber));
		
		return sum(textNumber.toString().split(regex));
	}
	
	private String findCustomSeparator(StringBuilder textNumber) {
		String customSeparator = "";
		int endSeparatorIndex;
		if (textNumber.length() >= START_CUSTOM_SEPARATOR &&
				textNumber.substring(0, START_CUSTOM_SEPARATOR).equals(CUSTOM_SEPARATOR_SYMBOL)) {
			if(textNumber.charAt(START_CUSTOM_SEPARATOR) == '[') {
				endSeparatorIndex = textNumber.indexOf("]");
				customSeparator = '|' + textNumber.substring(START_CUSTOM_SEPARATOR + 1, endSeparatorIndex);
			} else {
				endSeparatorIndex = END_CUSTOM_SEPARATOR;
				customSeparator = '|' + textNumber.substring(START_CUSTOM_SEPARATOR, endSeparatorIndex);
			}
			textNumber = textNumber.delete(0, endSeparatorIndex + 1);
		}
		
		return customSeparator;
	}

	private int sum(String[] stringNumbers) throws ForbiddenNegativeNumberException{
		int[] numbers = Arrays.stream(stringNumbers).mapToInt(Integer::parseInt).toArray();
		int[] negativeNumber = Arrays.stream(numbers).filter(number -> number < 0).toArray();
		if (negativeNumber.length == 1) {
			throw new ForbiddenNegativeNumberException();
		}
		if (negativeNumber.length > 1) {
			String commaSeparatedNegativeNumbers = Arrays.stream(negativeNumber)
					.mapToObj(nn -> ((Integer) nn).toString())
					.collect(Collectors.joining(", "));
			throw new ForbiddenNegativeNumberException(commaSeparatedNegativeNumbers);
		}
		return Arrays.stream(numbers).filter(number -> number < 1000).sum();
	}
}
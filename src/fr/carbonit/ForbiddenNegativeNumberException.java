package fr.carbonit;

public class ForbiddenNegativeNumberException extends Exception {

	public ForbiddenNegativeNumberException() {
		super("Les nombres n�gatifs ne sont pas autoris�s");
	}
	
	public ForbiddenNegativeNumberException(String concatNumbers) {
		super("Les nombres n�gatifs ne sont pas autoris�s : " + concatNumbers);
	}
}

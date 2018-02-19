package fr.carbonit;

public class ForbiddenNegativeNumberException extends Exception {

	public ForbiddenNegativeNumberException() {
		super("Les nombres négatifs ne sont pas autorisés");
	}
	
	public ForbiddenNegativeNumberException(String concatNumbers) {
		super("Les nombres négatifs ne sont pas autorisés : " + concatNumbers);
	}
}

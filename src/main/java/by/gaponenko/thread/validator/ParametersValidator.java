package by.gaponenko.thread.validator;

public class ParametersValidator {
    private static final int NUMBER_SPACESHIP_PARAMETERS = 3;

    private ParametersValidator() {
    }

    public static boolean isCorrectNumberOfParameters(String[] parameters) {
        return (parameters != null && parameters.length == NUMBER_SPACESHIP_PARAMETERS);
    }
}

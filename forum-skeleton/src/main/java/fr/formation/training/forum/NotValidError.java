package fr.formation.training.forum;


public class NotValidError {

    private String input;

    private String notValidated;

    private boolean globalError = false;

    public NotValidError(String input, String notValidated, boolean globalError) {
        this.input = input;
        this.notValidated = notValidated;
        this.globalError = globalError;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getNotValidated() {
        return notValidated;
    }

    public void setNotValidated(String notValidated) {
        this.notValidated = notValidated;
    }

    public boolean isGlobalError() {
        return globalError;
    }

    public void setGlobalError(boolean globalError) {
        this.globalError = globalError;
    }
}

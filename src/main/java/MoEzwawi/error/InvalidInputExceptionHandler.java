package MoEzwawi.error;

import MoEzwawi.util.Log;

/**
 * Handles {@link InvalidInputException} by logging it as a warning and
 * displaying a user-friendly error message to the console.
 *
 * <p>If the exception is not of this type, it is passed to the next handler in the chain.</p>
 */
public class InvalidInputExceptionHandler extends ExceptionShieldingHandler {

    /**
     * Handles {@link InvalidInputException} specifically, or delegates otherwise.
     *
     * @param ex the exception to handle
     */
    @Override
    public void handleException(Exception ex) {
        if (ex instanceof InvalidInputException) {
            Log.warn(ex.getMessage(), ex);
            System.err.println("❗ " + ex.getMessage());
        } else if (this.nextHandler != null) {
            this.nextHandler.handleException(ex);
        }
    }
}


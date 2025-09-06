package MoEzwawi.error;

import MoEzwawi.util.Log;

/**
 * Handles general {@link RuntimeException}s that are not already filtered by more specific handlers.
 *
 * <p>Logs the error at {@code SEVERE} level and shows a generic message to the user.
 * If the exception is not a runtime exception, it is delegated to the next handler.</p>
 */
public class RuntimeExceptionHandler extends ExceptionShieldingHandler {

    /**
     * Handles {@link RuntimeException}s or passes the exception to the next handler.
     *
     * @param ex the exception to handle
     */
    @Override
    public void handleException(Exception ex) {
        if (ex instanceof RuntimeException) {
            Log.error(ex.getMessage(), ex);
            System.err.println("❗ Unexpected error, please try again.");
        } else if (this.nextHandler != null) {
            this.nextHandler.handleException(ex);
        }
    }
}



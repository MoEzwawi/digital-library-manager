package MoEzwawi.error;

import MoEzwawi.util.Log;

/**
 * Default fallback handler for uncaught exceptions.
 *
 * <p>This handler is placed at the end of the chain to ensure no exception goes unhandled.
 * It logs the error and displays a generic failure message.</p>
 */
public class GenericExceptionHandler extends ExceptionShieldingHandler {

    /**
     * Handles any exception not previously handled by earlier handlers in the chain.
     *
     * @param ex the exception to handle
     */
    @Override
    public void handleException(Exception ex) {
        Log.error(ex.getMessage(), ex);
        System.err.println("❗ Unexpected error, please try again later.");
    }
}


package MoEzwawi.error;

/**
 * Abstract base class for implementing Exception Shielding using the
 * Chain of Responsibility design pattern.
 *
 * <p>Each handler in the chain decides whether it can process a given exception.
 * If not, it delegates to the next handler (if present).</p>
 *
 * <p>Subclasses must override {@link #handleException(Exception)} to define
 * custom exception filtering and handling behavior.</p>
 */
public abstract class ExceptionShieldingHandler {

    /**
     * The next handler in the chain.
     */
    protected ExceptionShieldingHandler nextHandler;

    /**
     * Sets the next handler to be used if the current handler does not process the exception.
     *
     * @param nextHandler the next {@code ExceptionShieldingHandler} in the chain
     */
    public void setNextHandler(ExceptionShieldingHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Attempts to handle the given exception.
     * If the current handler does not recognize the exception, it delegates to the next one.
     *
     * @param ex the exception to handle
     */
    public abstract void handleException(Exception ex);
}


package MoEzwawi.error;

/**
 * Exception exposed to the application layer with a user-friendly message,
 * while preserving the technical cause for logging and debugging.
 */
public class ShieldedException extends RuntimeException {

    /**
     * A user-friendly message safe to display to the end user.
     */
    private final String userMessage;

    /**
     * Constructs a new ShieldedException with the specified message.
     *
     * @param userMessage a clean message suitable for the user interface
     */
    public ShieldedException(String userMessage) {
        super(userMessage);
        this.userMessage = userMessage;
    }

    /**
     * Constructs a new ShieldedException with message and technical cause.
     *
     * @param userMessage a clean message suitable for the user interface
     * @param cause the internal technical cause (for logging)
     */
    public ShieldedException(String userMessage, Throwable cause) {
        super(userMessage, cause);
        this.userMessage = userMessage;
    }

    /**
     * Returns the user-friendly message, safe to display to end users.
     */
    public String getUserMessage() {
        return userMessage;
    }
}


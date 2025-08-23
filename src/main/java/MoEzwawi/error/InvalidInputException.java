package MoEzwawi.error;

/**
 * Exception thrown when invalid or inconsistent input is provided
 * while creating a bibliographic item.
 * <p>
 *     This exception is used to signal problems such as:
 *     <ul>
 *         <li>Missing required parameters (e.g.title, author)</li>
 *         <li>Malformed values (e.g. non-numerical year, negative pages)</li>
 *         <li>Other invalid states that prevent creating a valid item.</li>
 *     </ul>
 * </p>
 * <p>
 *     <b>Note:</b>
 *     <p>
           This exception is not shown directly to the end user,
           it is typically caught, logged and wrapped in a shielded exception
           with a user-friendly message.
 *     </p>
 * </p>
 */
public class InvalidInputException extends RuntimeException{
    /**
     * Constructs a new InvalidInputException with the specified message.
     *
     * @param message description of the error.
     */
    public InvalidInputException(String message){
        super(message);
    }
    /**
     * Constructs a new InvalidInputException with the specified message and cause.
     *
     * @param message description of the error.
     * @param cause   underlying cause of the error.
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

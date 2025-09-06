package MoEzwawi.error;

/**
 * Utility class for building a pre-configured exception shielding chain.
 *
 * <p>Creates a chain of {@link ExceptionShieldingHandler} instances in the following order:</p>
 * <ol>
 *   <li>{@link InvalidInputExceptionHandler}</li>
 *   <li>{@link RuntimeExceptionHandler}</li>
 *   <li>{@link GenericExceptionHandler}</li>
 * </ol>
 *
 * <p>This allows the application to handle exceptions in a prioritized and structured way.</p>
 */
public final class ExceptionHandlerChainBuilder {

    private ExceptionHandlerChainBuilder() {
        // Prevent instantiation
    }

    /**
     * Builds the exception shielding chain and returns the first handler in the chain.
     *
     * @return the first {@link ExceptionShieldingHandler} in the chain
     */
    public static ExceptionShieldingHandler buildChain() {
        ExceptionShieldingHandler invalidInputHandler = new InvalidInputExceptionHandler();
        ExceptionShieldingHandler runtimeHandler = new RuntimeExceptionHandler();
        ExceptionShieldingHandler genericHandler = new GenericExceptionHandler();

        invalidInputHandler.setNextHandler(runtimeHandler);
        runtimeHandler.setNextHandler(genericHandler);

        return invalidInputHandler;
    }
}


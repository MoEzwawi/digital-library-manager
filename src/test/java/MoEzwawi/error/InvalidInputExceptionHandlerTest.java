package MoEzwawi.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvalidInputExceptionHandlerTest {

    @Test
    public void testHandlesInvalidInputException() {
        ExceptionShieldingHandler handler = new InvalidInputExceptionHandler();
        Exception exception = new InvalidInputException("Missing required parameter");

        assertDoesNotThrow(() -> handler.handleException(exception));
    }

    @Test
    public void testDelegatesIfNotInvalidInput() {
        ExceptionShieldingHandler handler = new InvalidInputExceptionHandler();
        ExceptionShieldingHandler mockNext = mock(ExceptionShieldingHandler.class);
        handler.setNextHandler(mockNext);

        Exception exception = new RuntimeException("Unexpected error");

        handler.handleException(exception);

        verify(mockNext).handleException(exception);
    }
}

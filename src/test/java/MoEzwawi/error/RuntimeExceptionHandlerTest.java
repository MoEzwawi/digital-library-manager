package MoEzwawi.error;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RuntimeExceptionHandlerTest {

    @Test
    public void testHandlesRuntimeException() {
        ExceptionShieldingHandler handler = new RuntimeExceptionHandler();
        Exception exception = new RuntimeException("Database failure");

        assertDoesNotThrow(() -> handler.handleException(exception));
    }

    @Test
    public void testDelegatesIfNotRuntimeException() {
        ExceptionShieldingHandler handler = new RuntimeExceptionHandler();
        ExceptionShieldingHandler mockNext = mock(ExceptionShieldingHandler.class);
        handler.setNextHandler(mockNext);

        Exception exception = new IOException("File not found");

        handler.handleException(exception);

        verify(mockNext).handleException(exception);
    }
}

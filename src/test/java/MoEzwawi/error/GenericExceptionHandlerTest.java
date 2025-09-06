package MoEzwawi.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericExceptionHandlerTest {

    @Test
    public void testAlwaysHandlesAnyException() {
        ExceptionShieldingHandler handler = new GenericExceptionHandler();
        Exception exception = new Exception("Unknown fatal error");

        assertDoesNotThrow(() -> handler.handleException(exception));
    }
}


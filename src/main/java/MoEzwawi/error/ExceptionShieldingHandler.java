package MoEzwawi.error;

import MoEzwawi.util.Log;

public class ExceptionShieldingHandler {
    public void handleException(Exception ex){
        Log.error(ex.getMessage(), ex);
        throw new ShieldedException("Unexpected error, please try again later.");
    }
    public void handleRuntimeException(RuntimeException ex){
        Log.error(ex.getMessage(), ex);
        throw new ShieldedException("Unexpected error, please try again later.");
    }
    public void handleInvalidInputException(InvalidInputException ex){
        Log.warn(ex.getMessage(), ex);
        throw new ShieldedException(ex.getMessage());
    }
}

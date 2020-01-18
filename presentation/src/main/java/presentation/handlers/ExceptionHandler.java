package presentation.handlers;

import java.util.function.Supplier;

public class ExceptionHandler {

    /**
     * wraps a method that can throw an exception
     *
     * @param method method that will be wrapped
     * @return the result of the wrapped value or null if it failed
     */
    public static <T> T wrapException(Supplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            return null;
        }
    }

}

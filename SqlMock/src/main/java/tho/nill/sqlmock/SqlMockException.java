package tho.nill.sqlmock;

public class SqlMockException extends RuntimeException {

    public SqlMockException() {
    }

    public SqlMockException(String message) {
        super(message);
    }

    public SqlMockException(Throwable cause) {
        super(cause);
    }

    public SqlMockException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlMockException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

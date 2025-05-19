package tensor;

public class NotSquareMatrixException extends InvalidOperationException {
    public NotSquareMatrixException(String message) {
        super(message);
    }
}
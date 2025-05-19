package tensor;

public class SingularMatrixException extends InvalidOperationException {
    public SingularMatrixException(String message) {
        super(message);
    }
}
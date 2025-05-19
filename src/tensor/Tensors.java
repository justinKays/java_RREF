package tensor;

public class Tensors {

    // Scalar static operations
    // 24. 두 스칼라 덧셈
    public static Scalar add(Scalar s1, Scalar s2) {
        return Scalar.add(s1, s2);
    }

    // 25. 두 스칼라 곱셈
    public static Scalar multiply(Scalar s1, Scalar s2) {
        return Scalar.multiply(s1, s2);
    }

    // Vector static operations
    // 26. 두 벡터 덧셈
    public static Vector add(Vector v1, Vector v2) {
        return Vector.add(v1, v2);
    }

    // 27. 스칼라와 벡터 곱셈
    public static Vector multiply(Vector v, Scalar s) {
        return Vector.multiply(v, s);
    }
    // 오버로딩: 스칼라가 먼저 오는 경우도 편의상 제공 가능
    public static Vector multiply(Scalar s, Vector v) {
        return Vector.multiply(v, s); // 순서만 바꿔서 호출
    }


    // Matrix static operations
    // 28. 두 행렬 덧셈
    public static Matrix add(Matrix m1, Matrix m2) {
        return Matrix.add(m1, m2);
    }

    // 29. 두 행렬 곱셈
    public static Matrix multiply(Matrix m1, Matrix m2) {
        return Matrix.multiply(m1, m2);
    }

    // 32. static 가로 합치기 (Tensors에서 호출 가능하도록)
    public static Matrix hstack(Matrix m1, Matrix m2) {
        return Matrix.hstack(m1, m2);
    }

    // 33. static 세로 합치기 (Tensors에서 호출 가능하도록)
    public static Matrix vstack(Matrix m1, Matrix m2) {
        return Matrix.vstack(m1, m2);
    }

    // 기타 유용한 static 메소드 추가 가능 (예: 특정 값으로 채워진 행렬 생성 등)
    /**
     * Creates a matrix of given dimensions filled with a specific scalar value.
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @param value The scalar value to fill the matrix with.
     * @return A new Matrix.
     */
    public static Matrix filled(int rows, int cols, Scalar value) {
        if (rows <=0 || cols <=0) throw new IllegalArgumentException("Matrix dimensions must be positive for filled.");
        Scalar[][] data = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = value.clone(); // 값 복사
            }
        }
        return Factory.buildMatrix(data);
    }

    /**
     * Creates an identity matrix of a given dimension.
     * @param dimension The dimension of the square identity matrix.
     * @return A new identity Matrix.
     */
    public static Matrix identity(int dimension) {
        return Factory.buildMatrix(dimension);
    }

    /**
     * Creates a zero matrix of given dimensions.
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @return A new zero Matrix.
     */
    public static Matrix zeros(int rows, int cols) {
        return Factory.buildMatrix(rows, cols, 0.0); // 0.0으로 채운 행렬 생성
    }

    /**
     * Creates a matrix of ones of given dimensions.
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @return A new Matrix filled with ones.
     */
    public static Matrix ones(int rows, int cols) {
        return Factory.buildMatrix(rows, cols, 1.0); // 1.0으로 채운 행렬 생성
    }
}
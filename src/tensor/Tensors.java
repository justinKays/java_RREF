package tensor;

public class Tensors {

    // Scalar static operations
    // 24. 두 스칼라 덧셈
    public static Scalar add(Scalar s1, Scalar s2) {
        // 원래 Scalar.java의 static add
        return Factory.buildScalar(s1.getValue().add(s2.getValue()).toString());
    }

    // 25. 두 스칼라 곱셈
    public static Scalar multiply(Scalar s1, Scalar s2) {
        // 원래 Scalar.java의 static multiply
        return Factory.buildScalar(s1.getValue().multiply(s2.getValue()).toString());
    }

    // Vector static operations
    // 26. 두 벡터 덧셈
    public static Vector add(Vector v1, Vector v2) {
        // 원래 Vector.java의 static add
        if (v1.getSize() != v2.getSize()) {
            throw new DimensionMismatchException("Vectors must have the same size for addition. v1 size: " + v1.getSize() + ", v2 size: " + v2.getSize());
        }
        Vector result = v1.clone();
        for (int i = 0; i < result.getSize(); i++) {
            result.setElement(i, Tensors.add(v1.viewElement(i), v2.viewElement(i))); // Scalar.add -> Tensors.add로 변경
        }
        return result;
    }

    // 27. 스칼라와 벡터 곱셈
    public static Vector multiply(Vector v, Scalar s) {
        // 원래 Vector.java의 static multiply
        Vector result = v.clone();
        for (int i = 0; i < result.getSize(); i++) {
            result.setElement(i, Tensors.multiply(v.viewElement(i), s)); // Scalar.multiply -> Tensors.multiply로 변경
        }
        return result;
    }

    // 오버로딩: 스칼라가 먼저 오는 경우도 편의상 제공 가능
    public static Vector multiply(Scalar s, Vector v) {
        return Tensors.multiply(v, s); // 순서만 바꿔서 호출, Tensors.multiply로 변경
    }


    // Matrix static operations
    // 28. 두 행렬 덧셈
    public static Matrix add(Matrix m1, Matrix m2) {
        // 원래 Matrix.java의 static add
        int[] size1 = m1.getSize();
        int[] size2 = m2.getSize();
        if (size1[0] != size2[0] || size1[1] != size2[1]) {
            throw new DimensionMismatchException("Matrices must have the same dimensions for addition.");
        }
        Matrix result = m1.clone();
        for (int i = 0; i < size1[0]; i++) {
            for (int j = 0; j < size1[1]; j++) {
                result.setElement(i, j, Tensors.add(m1.viewElement(i, j), m2.viewElement(i, j))); // Scalar.add -> Tensors.add로 변경
            }
        }
        return result;
    }

    // 29. 두 행렬 곱셈
    public static Matrix multiply(Matrix m1, Matrix m2) {
        // 원래 Matrix.java의 static multiply
        int[] size1 = m1.getSize();
        int[] size2 = m2.getSize();
        if (size1[1] != size2[0]) {
            throw new DimensionMismatchException("Number of columns in the first matrix must equal the number of rows in the second matrix for multiplication. m1_cols: " + size1[1] + ", m2_rows: " + size2[0]);
        }
        int resultRows = size1[0];
        int resultCols = size2[1];
        Scalar[][] resultData = new Scalar[resultRows][resultCols];

        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < resultCols; j++) {
                Scalar sum = Factory.buildScalar("0");
                for (int k = 0; k < size1[1]; k++) {
                    Scalar val1 = m1.viewElement(i, k);
                    Scalar val2 = m2.viewElement(k, j);
                    sum.add(Tensors.multiply(val1, val2)); // Scalar.multiply -> Tensors.multiply로 변경, sum.add는 non-static
                }
                resultData[i][j] = sum;
            }
        }
        return Factory.buildMatrix(resultData);
    }

    // 32. static 가로 합치기
    public static Matrix hstack(Matrix m1, Matrix m2) {
        // 원래 Matrix.java의 static hstack
        int[] size1 = m1.getSize();
        int[] size2 = m2.getSize();
        if (size1[0] != size2[0]) {
            throw new DimensionMismatchException("Matrices must have the same number of rows for horizontal stacking. m1_rows: " + size1[0] + ", m2_rows: " + size2[0]);
        }
        int resultRows = size1[0];
        int resultCols = size1[1] + size2[1];
        Scalar[][] resultData = new Scalar[resultRows][resultCols];

        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < size1[1]; j++) {
                resultData[i][j] = m1.viewElement(i, j).clone();
            }
            for (int j = 0; j < size2[1]; j++) {
                resultData[i][size1[1] + j] = m2.viewElement(i, j).clone();
            }
        }
        return Factory.buildMatrix(resultData);
    }

    // 33. static 세로 합치기
    public static Matrix vstack(Matrix m1, Matrix m2) {
        // 원래 Matrix.java의 static vstack
        int[] size1 = m1.getSize();
        int[] size2 = m2.getSize();
        if (size1[1] != size2[1]) {
            throw new DimensionMismatchException("Matrices must have the same number of columns for vertical stacking. m1_cols: " + size1[1] + ", m2_cols: " + size2[1]);
        }
        int resultRows = size1[0] + size2[0];
        int resultCols = size1[1];
        Scalar[][] resultData = new Scalar[resultRows][resultCols];

        for (int i = 0; i < size1[0]; i++) {
            for (int j = 0; j < resultCols; j++) {
                resultData[i][j] = m1.viewElement(i, j).clone();
            }
        }
        for (int i = 0; i < size2[0]; i++) {
            for (int j = 0; j < resultCols; j++) {
                resultData[size1[0] + i][j] = m2.viewElement(i, j).clone();
            }
        }
        return Factory.buildMatrix(resultData);
    }

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

    public static Matrix identity(int dimension) {
        return Factory.buildMatrix(dimension);
    }

    public static Matrix zeros(int rows, int cols) {
        return Factory.buildMatrix(rows, cols, 0.0); // 0.0으로 채운 행렬 생성
    }

    public static Matrix ones(int rows, int cols) {
        return Factory.buildMatrix(rows, cols, 1.0); // 1.0으로 채운 행렬 생성
    }
}

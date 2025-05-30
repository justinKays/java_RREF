package tensor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; // 추가

public class Factory {
    // Scalar
    public static Scalar buildScalar(String ss) {
        return null;
    }

    public static Scalar buildScalar(BigDecimal bd) {
        return null;
    }

    public static Scalar buildScalar(double ii, double jj) {
        return null;
    }


    // Vector
    public static Vector buildVector(int nn, double dd) {
        return null;
    }

    public static Vector buildVector(int nn, double ii, double jj) {
        return null;
    }

    public static Vector buildVector(double[] arr) {
        return null;
    }

    public static Vector buildVector(Scalar[] arr) {
        return null;
    }

    public static Vector buildVector(List<Scalar> scalarList) {
        return null;
    }


    // Matrix
    public static Matrix buildMatrix(int mm, int nn, double dd) {
        return null;
    }

    public static Matrix buildMatrix(int mm, int nn, double ii, double jj) {
        return null;
    }

    public static Matrix buildMatrix(String csvFilePath) throws CsvParseException {
        return null;
    }

    public static Matrix buildMatrix(double[][] arr) {
        return null;
    }

    public static Matrix buildMatrix(Scalar[][] data) {
        return null;
    }

    public static Matrix buildMatrix(int dimension) {
        return null;
    }

    public static Matrix buildMatrix(List<Vector> rows) {
        return null;
    }
}
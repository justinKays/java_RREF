package test;

import tensor.*; // 모든 tensor 패키지 클래스 임포트
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.io.FileWriter; // CSV 테스트용
import java.io.IOException;  // CSV 테스트용
import java.io.PrintWriter; // CSV 테스트용

public class NewTest {

    // Helper to print test titles
    private static void printTitle(String title) {
        System.out.println("\n=========================================================");
        System.out.println("===== " + title + " =====");
        System.out.println("=========================================================");
    }

    // Helper for CSV file creation
    private static String createTestCsvFile(String fileName, String[] content) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : content) {
                out.println(line);
            }
        }
        return fileName;
    }

    public static void main(String[] args) {
        System.out.println("Tensor Library Test Suite - STARTED");
        System.out.println("Current Date/Time: " + java.time.LocalDateTime.now());

        testScalarCreation();
        testScalarBasicFunctions();
        testScalarOperations();

        testVectorCreation();
        testVectorBasicFunctions();
        testVectorOperations();
        testVectorAdvancedFunctions();

        testMatrixCreation();
        testMatrixBasicFunctions();
        testMatrixOperations();
        testMatrixAdvancedFunctions();
        testMatrixElementaryOperations();
        testMatrixRREFDeterminantInverse();

        testTensorsStaticMethods();
        testExceptionHandling();

        System.out.println("\nTensor Library Test Suite - COMPLETED");
    }

    public static void testScalarCreation() {
        printTitle("Scalar Creation (01, 02)");
        // Test.java 요구사항: Factory를 이용하여 스칼라 객체를 얻어낸다.
        // Test.java 요구사항: 스칼라 객체의 참조 타입은 인터페이스 타입만을 사용한다.
        Scalar s1 = Factory.buildScalar("3.14159"); // 명세 01: 값을 지정하여 스칼라 생성
        // 명세 01: 값을 지정하여 스칼라 생성, 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 01, 14s 값을 지정하여 스칼라 생성, 출력 : s1 (from String '3.14159'): " + s1);

        Scalar s2 = Factory.buildScalar(0.0, 1.0); // 명세 02: i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성
        // 명세 02: i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성, 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 02, 14s i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성, 출력 : s2 (random 0.0 to 1.0): " + s2);
        Scalar s3 = Factory.buildScalar(-5.0, -4.0); // 명세 02: i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성
        // 명세 02: i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성, 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 02, 14s i 이상 j 미만의 무작위 값을 요소로 하는 스칼라 생성, 출력 : s3 (random -5.0 to -4.0): " + s3);
    }

    public static void testScalarBasicFunctions() {
        printTitle("Scalar Basic Functions (12, 14s, 15s, 16, 17s)");
        Scalar s_a = Factory.buildScalar("10.5"); // 명세 01: 값을 지정하여 스칼라 생성
        Scalar s_b = Factory.buildScalar("20.0"); // 명세 01: 값을 지정하여 스칼라 생성
        Scalar s_a_copy = Factory.buildScalar("10.5"); // 명세 01: 값을 지정하여 스칼라 생성

        // 명세 12: (only 스칼라) 값을 지정/조회할 수 있다.
        System.out.println("명세 12 (only 스칼라) 값 조회 : s_a.getValue(): " + s_a.getValue());
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14s 스칼라 출력 : s_b before setValue: " + s_b);
        s_b.setValue(new BigDecimal("25.5")); // 명세 12: (only 스칼라) 값을 지정/조회할 수 있다.
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 12, 14s (only 스칼라) 값 지정 후 출력 : s_b after setValue(25.5): " + s_b);

        // 명세 15s: (@Override equals()) 객체의 동등성 판단
        System.out.println("명세 15s (@Override equals()) 객체의 동등성 판단 : s_a.equals(s_a_copy) (10.5 == 10.5): " + s_a.equals(s_a_copy));
        // 명세 15s: (@Override equals()) 객체의 동등성 판단
        System.out.println("명세 15s (@Override equals()) 객체의 동등성 판단 : s_a.equals(s_b) (10.5 == 25.5): " + s_a.equals(s_b));

        // 명세 16 (implements Comparable) 스칼라의 경우 값의 대소 비교
        System.out.println("명세 16 (implements Comparable) 스칼라 값 대소 비교 : s_a.compareTo(s_b) (10.5 vs 25.5): " + s_a.compareTo(s_b));
        // 명세 16 (implements Comparable) 스칼라의 경우 값의 대소 비교
        System.out.println("명세 16 (implements Comparable) 스칼라 값 대소 비교 : s_b.compareTo(s_a) (25.5 vs 10.5): " + s_b.compareTo(s_a));
        // 명세 16 (implements Comparable) 스칼라의 경우 값의 대소 비교
        System.out.println("명세 16 (implements Comparable) 스칼라 값 대소 비교 : s_a.compareTo(s_a_copy) (10.5 vs 10.5): " + s_a.compareTo(s_a_copy));

        // 명세 17s: (@Override clone()) 객체 복제 (deep copy)
        Scalar s_a_cloned = s_a.clone();
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17s, 14s (@Override clone()) 객체 복제 후 출력 : s_a: " + s_a + ", s_a_cloned: " + s_a_cloned);
        System.out.println("명세 17s (@Override clone()) 객체 복제 (동일 객체 여부) : s_a == s_a_cloned (identity): " + (s_a == s_a_cloned));
        // 명세 15s: (@Override equals()) 객체의 동등성 판단
        System.out.println("명세 17s, 15s (@Override clone()) 객체 복제 (동등성 판단) : s_a.equals(s_a_cloned) (equality): " + s_a.equals(s_a_cloned));
        s_a_cloned.setValue(new BigDecimal("100")); // 명세 12: (only 스칼라) 값을 지정/조회할 수 있다. (복제된 객체에)
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17s, 12, 14s (@Override clone()) 복제 객체 수정 후 원본/복제본 출력 (원본 불변 확인) : After s_a_cloned.setValue(100) -> s_a: " + s_a + ", s_a_cloned: " + s_a_cloned);
    }

    public static void testScalarOperations() {
        printTitle("Scalar Operations (18, 19 - non-static, 24, 25 - static)");
        Scalar s_x = Factory.buildScalar("5"); // 명세 01: 값을 지정하여 스칼라 생성
        Scalar s_y = Factory.buildScalar("3"); // 명세 01: 값을 지정하여 스칼라 생성
        Scalar s_z = Factory.buildScalar("2"); // 명세 01: 값을 지정하여 스칼라 생성

        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14s 스칼라 출력 : s_x original: " + s_x);
        s_x.add(s_y); // 명세 18: 스칼라는 다른 스칼라와 덧셈이 가능하다 (non-static, modifies self)
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 18, 14s 스칼라 덧셈(non-static) 후 출력 : s_x after s_x.add(s_y (3)): " + s_x);

        s_x.multiply(s_z); // 명세 19: 스칼라는 다른 스칼라와 곱셈이 가능하다 (non-static, modifies self)
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 19, 14s 스칼라 곱셈(non-static) 후 출력 : s_x after s_x.multiply(s_z (2)): " + s_x);

        // Reset for static tests
        s_x = Factory.buildScalar("10"); // 명세 01: 값을 지정하여 스칼라 생성
        s_y = Factory.buildScalar("4"); // 명세 01: 값을 지정하여 스칼라 생성

        // 명세 24: (Tensors) 전달받은 두 스칼라의 덧셈이 가능하다 (static, returns new)
        Scalar sum_static = Tensors.add(s_x, s_y);
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 24, 14s (Tensors) 두 스칼라 덧셈(static) 결과 출력 : Tensors.add(s_x (10), s_y (4)): " + sum_static + " (s_x: " + s_x + ", s_y: " + s_y + ")");

        // 명세 25: (Tensors) 전달받은 두 스칼라의 곱셈이 가능하다 (static, returns new)
        Scalar prod_static = Tensors.multiply(s_x, s_y);
        // 명세 14s: 스칼라 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 25, 14s (Tensors) 두 스칼라 곱셈(static) 결과 출력 : Tensors.multiply(s_x (10), s_y (4)): " + prod_static + " (s_x: " + s_x + ", s_y: " + s_y + ")");
    }


    public static void testVectorCreation() {
        printTitle("Vector Creation (03, 04, 05)");
        // Test.java 요구사항: Factory를 이용하여 벡터 객체를 얻어낸다.
        // Test.java 요구사항: 벡터 객체의 참조 타입은 인터페이스 타입만을 사용한다.
        Vector v1 = Factory.buildVector(3, 7.7); // 명세 03: 지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터 생성
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 03, 14v 지정 값 벡터 생성, 출력 : v1 (3 elements, value 7.7): " + v1);

        Vector v2 = Factory.buildVector(4, 10.0, 20.0); // 명세 04: i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터 생성
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 04, 14v 무작위 값 벡터 생성, 출력 : v2 (4 elements, random 10.0-20.0): " + v2);

        double[] arr_d = {1.1, 2.2, 3.3};
        Vector v3 = Factory.buildVector(arr_d); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 05, 14v 1차원 배열 벡터 생성, 출력 : v3 (from double[] {1.1, 2.2, 3.3}): " + v3);

        Scalar[] arr_s = {Factory.buildScalar("100"), Factory.buildScalar("200")};
        Vector v4 = Factory.buildVector(arr_s); // Factory.buildVector(Scalar[])
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14v (Scalar 배열 기반) 벡터 출력 : v4 (from Scalar[] {100, 200}): " + v4);
    }

    public static void testVectorBasicFunctions() {
        printTitle("Vector Basic Functions (11v, 13, 14v, 15v, 17v)");
        Vector vec = Factory.buildVector(new double[]{1.0, 2.0, 3.0}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        Vector vec_copy_val = Factory.buildVector(new double[]{1.0, 2.0, 3.0}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        Vector vec_other = Factory.buildVector(new double[]{1.0, 2.0, 4.0}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성

        // 명세 13: (벡터) 차원(길이) 조회
        System.out.println("명세 13 (벡터) 차원(길이) 조회 : vec.getSize(): " + vec.getSize());

        // 명세 11v: (only 벡터) 특정 위치의 요소를 지정/조회할 수 있다.
        System.out.println("명세 11v (only 벡터) 특정 위치 요소 조회 : vec.viewElement(1): " + vec.viewElement(1));
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14v 벡터 출력 (setElement 전) : vec before setElement(1, 99.0): " + vec);
        vec.setElement(1, Factory.buildScalar("99.0")); // 명세 11v: (only 벡터) 특정 위치의 요소를 지정/조회할 수 있다.
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 11v, 14v (only 벡터) 특정 위치 요소 지정 후 출력 : vec after setElement(1, 99.0): " + vec);
        // 명세 11v: (only 벡터) 특정 위치의 요소를 지정/조회할 수 있다.
        System.out.println("명세 11v (only 벡터) 특정 위치 요소 조회 (set 후) : vec.viewElement(1) after set: " + vec.viewElement(1));

        // 명세 15v: (@Override equals()) 객체의 동등성 판단
        vec.setElement(1, Factory.buildScalar("2.0")); // Reset for equals test (11v)
        System.out.println("명세 15v (@Override equals()) 벡터 객체 동등성 판단 : vec.equals(vec_copy_val) ([1,2, == [1,2,): " + vec.equals(vec_copy_val));
        System.out.println("명세 15v (@Override equals()) 벡터 객체 동등성 판단 : vec.equals(vec_other) ([1,2, == [1,2,): " + vec.equals(vec_other));
        Vector vec_diff_size = Factory.buildVector(new double[]{1.0, 2.0}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        System.out.println("명세 15v (@Override equals()) 벡터 객체 동등성 판단 (크기 다름) : vec.equals(vec_diff_size) (size mismatch): " + vec.equals(vec_diff_size));


        // 명세 17v: (@Override clone()) 객체 복제 (deep copy)
        Vector vec_cloned = vec.clone();
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17v, 14v (@Override clone()) 벡터 객체 복제 후 출력 : vec: " + vec + ", vec_cloned: " + vec_cloned);
        System.out.println("명세 17v (@Override clone()) 벡터 객체 복제 (동일 객체 여부) : vec == vec_cloned (identity): " + (vec == vec_cloned));
        // 명세 15v: (@Override equals()) 객체의 동등성 판단
        System.out.println("명세 17v, 15v (@Override clone()) 벡터 객체 복제 (동등성 판단) : vec.equals(vec_cloned) (equality): " + vec.equals(vec_cloned));
        vec_cloned.setElement(0, Factory.buildScalar("777")); // 명세 11v: (only 벡터) 특정 위치의 요소를 지정/조회할 수 있다. (복제된 객체에)
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17v, 11v, 14v (@Override clone()) 복제 객체 수정 후 원본/복제본 출력 (원본 불변 확인) : After vec_cloned.setElement(0, 777) -> vec: " + vec + ", vec_cloned: " + vec_cloned);
    }


    public static void testVectorOperations() {
        printTitle("Vector Operations (20, 21 - non-static, 26, 27 - static)");
        Vector v_a = Factory.buildVector(new double[]{1, 2, 3}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        Vector v_b = Factory.buildVector(new double[]{4, 5, 6}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        Scalar scal = Factory.buildScalar("3"); // 명세 01: 값을 지정하여 스칼라 생성

        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14v 벡터 출력 : v_a original: " + v_a);
        v_a.add(v_b); // 명세 20: 벡터는 다른 벡터와 덧셈이 가능하다 (non-static, modifies self)
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 20, 14v 벡터 덧셈(non-static) 후 출력 : v_a after v_a.add(v_b {4,5,6}): " + v_a);

        v_a.multiply(scal); // 명세 21: 벡터는 다른 스칼라와 곱셈이 가능하다 (non-static, modifies self)
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 21, 14v 벡터 스칼라 곱셈(non-static) 후 출력 : v_a after v_a.multiply(scal {3}): " + v_a);

        // Reset for static tests
        v_a = Factory.buildVector(new double[]{10, 20}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        v_b = Factory.buildVector(new double[]{3, 7}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        scal = Factory.buildScalar("2"); // 명세 01: 값을 지정하여 스칼라 생성

        // 명세 26: (Tensors) 전달받은 두 벡터의 덧셈이 가능하다 (static, returns new)
        Vector sum_static_v = Tensors.add(v_a, v_b);
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 26, 14v (Tensors) 두 벡터 덧셈(static) 결과 출력 : Tensors.add(v_a {10,20}, v_b {3,7}): " + sum_static_v + " (v_a: " + v_a + ")");

        // 명세 27: (Tensors) 전달받은 스칼라와 벡터의 곱셈이 가능하다 (static, returns new)
        Vector prod_static_v = Tensors.multiply(v_a, scal);
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 27, 14v (Tensors) 스칼라와 벡터 곱셈(static) 결과 출력 : Tensors.multiply(v_a {10,20}, scal {2}): " + prod_static_v + " (v_a: " + v_a + ")");
    }

    public static void testVectorAdvancedFunctions() {
        printTitle("Vector Advanced Functions (30, 31)");
        Vector vec_adv = Factory.buildVector(new double[]{10, 20, 30}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14v 벡터 출력 : Original vector vec_adv: " + vec_adv);

        // 명세 31: n-차원 벡터 객체는 자신으로부터 1xn 행렬(행벡터)을 생성하여 반환할 수 있다.
        Matrix rowMatrix = vec_adv.toRowMatrix();
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 31, 14m 벡터 -> 행행렬 변환 후 출력 : vec_adv.toRowMatrix(): \n" + rowMatrix);
        // 명세 13: (행렬) 크기(행 개수, 열 개수) 조회
        System.out.println("명세 13 (행렬) 크기 조회 : Row matrix size: " + Arrays.toString(rowMatrix.getSize()));

        // 명세 30: n-차원 벡터 객체는 자신으로부터 nx1 행렬(열벡터)을 생성하여 반환할 수 있다.
        Matrix colMatrix = vec_adv.toColMatrix();
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 30, 14m 벡터 -> 열행렬 변환 후 출력 : vec_adv.toColMatrix(): \n" + colMatrix);
        // 명세 13: (행렬) 크기(행 개수, 열 개수) 조회
        System.out.println("명세 13 (행렬) 크기 조회 : Column matrix size: " + Arrays.toString(colMatrix.getSize()));
    }


    public static void testMatrixCreation() {
        printTitle("Matrix Creation (06, 07, 08, 09, 10)");
        // Test.java 요구사항: Factory를 이용하여 행렬 객체를 얻어낸다.
        // Test.java 요구사항: 행렬 객체의 참조 타입은 인터페이스 타입만을 사용한다.
        Matrix m1 = Factory.buildMatrix(2, 3, 1.0); // 명세 06: 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 06, 14m 지정 값 행렬 생성, 출력 : m1 (2x3, value 1.0):\n" + m1);

        Matrix m2 = Factory.buildMatrix(3, 2, 0.0, 10.0); // 명세 07: i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 07, 14m 무작위 값 행렬 생성, 출력 : m2 (3x2, random 0-10):\n" + m2);

        String csvFile = "test_matrix.csv";
        try {
            createTestCsvFile(csvFile, new String[]{"1,2,3", "4,5,6", "7,8,9.5"});
            Matrix m3 = Factory.buildMatrix(csvFile); // 명세 08: csv 파일로부터 m x n 행렬 생성
            // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
            System.out.println("명세 08, 14m CSV 파일 행렬 생성, 출력 : m3 (from CSV '" + csvFile + "'):\n" + m3);
        } catch (IOException | CsvParseException e) {
            System.err.println("Error creating or parsing CSV for m3: " + e.getMessage());
        }

        String emptyCsvFile = "empty_test_matrix.csv";
        try {
            createTestCsvFile(emptyCsvFile, new String[]{});
            // Matrix m_empty_csv = Factory.buildMatrix(emptyCsvFile); // 명세 08: csv 파일로부터 m x n 행렬 생성 (빈 파일 케이스)
            // System.out.println("명세 08, 13, 14m 빈 CSV 파일 행렬 생성, 크기 및 내용 출력 : m_empty_csv (from empty CSV '" + emptyCsvFile + "') (size " + Arrays.toString(m_empty_csv.getSize()) + "):\n" + m_empty_csv);

            createTestCsvFile(emptyCsvFile, new String[]{",,"});
            Matrix m_commas_csv = Factory.buildMatrix(emptyCsvFile); // 명세 08: csv 파일로부터 m x n 행렬 생성 (콤마만 있는 케이스)
            // 명세 13: (행렬) 크기(행 개수, 열 개수) 조회, 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
            System.out.println("명세 08, 13, 14m CSV (콤마만) 행렬 생성, 크기 및 내용 출력 : m_commas_csv (from CSV with ' ,, ') (size " + Arrays.toString(m_commas_csv.getSize()) + "):\n" + m_commas_csv);
        } catch (IOException | CsvParseException e) {
            System.err.println("Error with empty CSV tests: " + e.getMessage());
        }

        double[][] arr_2d = {{1.1, 1.2}, {2.1, 2.2}, {3.1, 3.2}};
        Matrix m4 = Factory.buildMatrix(arr_2d); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 09, 14m 2차원 배열 행렬 생성, 출력 : m4 (from double[[):\n" + m4);

        Matrix m5_identity = Factory.buildMatrix(3); // 명세 10: 단위 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 10, 14m 단위 행렬 생성, 출력 : m5_identity (3x3):\n" + m5_identity);

        // Removed 0x0 identity matrix creation.
        // Matrix m0_identity = Factory.buildMatrix(0); // 명세 10: 단위 행렬 생성 (0차원)
        // System.out.println("명세 10, 13, 14m 0x0 단위 행렬 생성, 크기 및 내용 출력 : m0_identity (0x0):\n" + m0_identity + " size: " + Arrays.toString(m0_identity.getSize()));

        Scalar[][] scalar_arr_2d = {
                {Factory.buildScalar("10"), Factory.buildScalar("20")},
                {Factory.buildScalar("30"), Factory.buildScalar("40")}
        };
        Matrix m6 = Factory.buildMatrix(scalar_arr_2d); // Factory.buildMatrix(Scalar[][])
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m (Scalar 배열 기반) 행렬 출력 : m6 (from Scalar[[):\n" + m6);

        // Removed 0x0 matrix from array.
        // Matrix m_empty_arr = Factory.buildMatrix(new double[[); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (0x0 배열)
        // System.out.println("명세 09, 13, 14m 0x0 배열 행렬 생성, 크기 및 내용 출력 : m_empty_arr (from new double[[):\n" + m_empty_arr + " size: " + Arrays.toString(m_empty_arr.getSize()));

        // Removed 0xN matrix from array.
        // Matrix m_empty_rows_arr = Factory.buildMatrix(new double[[); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (0xN 배열)
        // System.out.println("명세 09, 13, 14m 0xN 배열 행렬 생성, 크기 및 내용 출력 : m_empty_rows_arr (from new double[[):\n" + m_empty_rows_arr + " size: " + Arrays.toString(m_empty_rows_arr.getSize()));
    }

    public static void testMatrixBasicFunctions() {
        printTitle("Matrix Basic Functions (11m, 13, 14m, 15m, 17m)");
        Matrix mat = Factory.buildMatrix(new double[][]{{1,2},{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix mat_copy_val = Factory.buildMatrix(new double[][]{{1,2},{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix mat_other_val = Factory.buildMatrix(new double[][]{{1,2},{3,5}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다. (디버깅용 추가 출력)
        System.out.println("명세 14m 행렬 출력 (mat 초기값) : mat"+mat);
        System.out.println("명세 14m 행렬 출력 (mat_copy_val 초기값) : mat2"+mat_copy_val);
        System.out.println("명세 14m 행렬 출력 (mat_other_val 초기값) : mat3"+mat_other_val);
        // 명세 13: (행렬) 크기(행 개수, 열 개수) 조회
        System.out.println("명세 13 (행렬) 크기 조회 : mat.getSize(): " + Arrays.toString(mat.getSize()));

        // 명세 11m: (only 행렬) 특정 위치의 요소를 지정/조회할 수 있다.
        System.out.println("명세 11m (only 행렬) 특정 위치 요소 조회 : mat.viewElement(1,0): " + mat.viewElement(1,0));
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m 행렬 출력 (setElement 전) : mat before setElement(1,0, Factory.buildScalar(\"99\")):\n" + mat);
        mat.setElement(1,0, Factory.buildScalar("99")); // 명세 11m: (only 행렬) 특정 위치의 요소를 지정/조회할 수 있다.
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 11m, 14m (only 행렬) 특정 위치 요소 지정 후 출력 : mat after setElement(1,0, Factory.buildScalar(\"99\")):\n" + mat);
        // 명세 11m: (only 행렬) 특정 위치의 요소를 지정/조회할 수 있다.
        System.out.println("명세 11m (only 행렬) 특정 위치 요소 조회 (set 후) : mat.viewElement(1,0) after set: " + mat.viewElement(1,0));

        // 명세 15m: (@Override equals()) 객체의 동등성 판단
        mat.setElement(1,0, Factory.buildScalar("3")); // Reset for equals (11m)
        System.out.println("명세 15m (@Override equals()) 행렬 객체 동등성 판단 : mat.equals(mat_copy_val): " + mat.equals(mat_copy_val));
        System.out.println("명세 15m (@Override equals()) 행렬 객체 동등성 판단 : mat.equals(mat_other_val): " + mat.equals(mat_other_val));
        Matrix mat_diff_size = Factory.buildMatrix(new double[][]{{1,2}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        System.out.println("명세 15m (@Override equals()) 행렬 객체 동등성 판단 (크기 다름) : mat.equals(mat_diff_size) (size mismatch): " + mat.equals(mat_diff_size));


        // 명세 17m: (@Override clone()) 객체 복제 (deep copy)
        Matrix mat_cloned = mat.clone();
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17m, 14m (@Override clone()) 행렬 객체 복제 후 출력 : mat:\n" + mat + "mat_cloned:\n" + mat_cloned);
        System.out.println("명세 17m (@Override clone()) 행렬 객체 복제 (동일 객체 여부) : mat == mat_cloned (identity): " + (mat == mat_cloned));
        // 명세 15m: (@Override equals()) 객체의 동등성 판단
        System.out.println("명세 17m, 15m (@Override clone()) 행렬 객체 복제 (동등성 판단) : mat.equals(mat_cloned) (equality): " + mat.equals(mat_cloned));
        mat_cloned.setElement(0,0, Factory.buildScalar("777")); // 명세 11m: (only 행렬) 특정 위치의 요소를 지정/조회할 수 있다. (복제된 객체에)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 17m, 11m, 14m (@Override clone()) 복제 객체 수정 후 원본/복제본 출력 (원본 불변 확인) : After mat_cloned.setElement(0,0, 777):\nmat:\n" + mat + "\nmat_cloned:\n" + mat_cloned);
    }

    public static void testMatrixOperations() {
        printTitle("Matrix Operations (22, 23 - non-static, 28, 29 - static)");
        Matrix m_a = Factory.buildMatrix(new double[][]{{1,2},{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix m_b = Factory.buildMatrix(new double[][]{{5,6},{7,8}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix m_c = Factory.buildMatrix(new double[][]{{1,0},{0,1}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (단위행렬 테스트용)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다. (디버깅용 추가 출력)
        System.out.println("명세 14m 행렬 출력 (m_a 초기값) : m_a"+m_a);
        System.out.println("명세 14m 행렬 출력 (m_b 초기값) : m_b"+m_b);
        System.out.println("명세 14m 행렬 출력 (m_c 단위행렬) : m_c Identity"+m_c);
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m 행렬 출력 : m_a original:\n" + m_a);
        m_a.add(m_b); // 명세 22: 행렬은 다른 행렬과 덧셈이 가능하다 (non-static, modifies self)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 22, 14m 행렬 덧셈(non-static) 후 출력 : m_a after m_a.add(m_b):\n" + m_a);

        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m 행렬 출력 (multiplyRight 전) : m_a before multiply by Identity:\n" + m_a);
        m_a.multiplyRight(m_c); // 명세 23: 행렬은 다른 행렬과 곱셈이 가능하다 (non-static, this = this * other)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 23 (this*other), 14m 행렬 곱셈(non-static) 후 출력 : m_a after m_a.multiplyRight(Identity):\n" + m_a);

        Matrix m_d = Factory.buildMatrix(new double[][]{{1,2,3},{4,5,6}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x3)
        Matrix m_e = Factory.buildMatrix(new double[][]{{7,8},{9,10},{11,12}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (3x2)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m 행렬 출력 : m_d (2x3) original:\n" + m_d);
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 14m 행렬 출력 : m_e (3x2) original:\n" + m_e);
        m_d.multiplyRight(m_e); // 명세 23: 행렬은 다른 행렬과 곱셈이 가능하다 (non-static, this = this * other)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 23 (this*other), 14m 행렬 곱셈(non-static) 후 출력 (2x3 * 3x2 -> 2x2) : m_d after m_d.multiplyRight(m_e (3x2)) (result should be 2x2):\n" + m_d);

        // 명세 23: 행렬은 다른 행렬과 곱셈이 가능하다 (non-static, this = other * this)
        m_d.multiplyLeft(m_d); // m_d is 2x2, m_d is 2x2 -> result 2x2
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 23 (other*this), 14m 행렬 자기 자신과 곱셈(non-static, left) 후 출력 : m_d mult by itself (left):\n" + m_d);
        // m_e is 3x2, m_d becomes 2x2 after previous op. For multiplyLeft(m_e), m_e is 'other', m_d is 'this'.
        // So, this will be m_e(3x2) * m_d(2x2) -> result 3x2. 'this' (m_d) will be updated.
        Matrix m_d_before_left_mult_e = m_d.clone(); // For clarity
        m_d.multiplyLeft(m_e); // this(m_d) = other(m_e) * this(m_d_before_left_mult_e)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 23 (other*this), 14m 행렬 곱셈(non-static, left, 3x2 * 2x2 -> 3x2) 후 출력 : m_d after m_d.multiplyLeft(m_e (3x2)) (result 3x2):\n" + m_d);

        // Reset for static tests
        m_a = Factory.buildMatrix(new double[][]{{1,0},{0,1}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        m_b = Factory.buildMatrix(new double[][]{{2,3},{4,5}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다. (디버깅용 추가 출력)
        System.out.println("명세 14m 행렬 출력 (m_a 재설정) : m_a"+m_a);
        System.out.println("명세 14m 행렬 출력 (m_b 재설정) : m_b"+m_b);
        // 명세 28: (Tensors) 전달받은 두 행렬의 덧셈이 가능하다 (static, returns new)
        Matrix sum_static_m = Tensors.add(m_a, m_b);
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 28, 14m (Tensors) 두 행렬 덧셈(static) 결과 출력 : Tensors.add(m_a, m_b):\n" + sum_static_m + "\n(m_a unchanged:\n" + m_a + ")");

        // 명세 29: (Tensors) 전달받은 두 행렬의 곱셈이 가능하다 (static, returns new)
        Matrix prod_static_m = Tensors.multiply(m_a, m_b);
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 29, 14m (Tensors) 두 행렬 곱셈(static) 결과 출력 : Tensors.multiply(m_a (I), m_b):\n" + prod_static_m + "\n(m_a unchanged:\n" + m_a + ")");

        Matrix m1_mult = Factory.buildMatrix(new double[][]{{1,2},{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x2)
        Matrix m2_mult = Factory.buildMatrix(new double[][]{{2,0,1},{0,3,0}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x3)
        Matrix prod_static_m2 = Tensors.multiply(m1_mult, m2_mult); // 명세 29: (Tensors) 전달받은 두 행렬의 곱셈이 가능하다 (static, returns new)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 29, 14m (Tensors) 두 행렬 곱셈(static, 2x2 * 2x3 -> 2x3) 결과 출력 : Tensors.multiply(m1_mult (2x2), m2_mult (2x3)) (result 2x3):\n" + prod_static_m2);
    }

    public static void testMatrixAdvancedFunctions() {
        printTitle("Matrix Advanced Functions (32-44)");
        Matrix mat1 = Factory.buildMatrix(new double[][]{{1,2},{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix mat2 = Factory.buildMatrix(new double[][]{{5,6},{7,8}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix mat_rect = Factory.buildMatrix(new double[][]{{1,2,3},{4,5,6}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x3)

        Matrix hstacked = mat1.hstack(mat2); // 명세 32: 행렬은 다른 행렬과 가로로 합쳐질 수 있다 (non-static, returns new)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 32, 14m 가로 합치기(non-static) 후 출력 : mat1.hstack(mat2):\n" + hstacked);

        Matrix vstacked = mat1.vstack(mat2); // 명세 33: 행렬은 다른 행렬과 세로로 합쳐질 수 있다 (non-static, returns new)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 33, 14m 세로 합치기(non-static) 후 출력 : mat1.vstack(mat2):\n" + vstacked);

        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다. (getRow 결과가 Vector이므로)
        System.out.println("명세 34, 14v 특정 행 추출 후 출력 : mat_rect.getRow(0): " + mat_rect.getRow(0));

        // 명세 14v: 벡터 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다. (getCol 결과가 Vector이므로)
        System.out.println("명세 35, 14v 특정 열 추출 후 출력 : mat_rect.getCol(1): " + mat_rect.getCol(1));

        Matrix sub = mat_rect.subMatrix(0,0,1,2); // 명세 36: 행렬은 특정 범위의 부분 행렬을 추출 (start/end row/col indices)
        // 명세 14m: 행렬 객체는 자신의 값을 문자열로 변환하여 반환할 수 있다.
        System.out.println("명세 36, 14m 부분 행렬 추출 후 출력 : mat_rect.subMatrix(0,0,1,2):\n" + sub);

        //==================    37 ~ 54    ============================
        Matrix oneToNine = Factory.buildMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix minorMatrix = oneToNine.minor(0,0);
        System.out.println("명세 37: minor 행렬 추출 \n" + "기준 행렬: " + oneToNine + "minor 행렬(0행, 0열 제외) -> " + minorMatrix + "\n");

        System.out.println("명세 38: 전치행렬 생성\n" + "기준 행렬: " + oneToNine + "전치행렬 -> " + oneToNine.transpose() + "\n");

        System.out.println("명세 39: 대각 요소 합(trace)\n" + "기준 행렬: " + oneToNine + "trace -> " + oneToNine.trace() + "\n\n");

        System.out.println("명세 40: 정사각 행렬 여부 판별\n" + "행렬 A: " + oneToNine + "A -> " + oneToNine.isSquare());
        Matrix oneToSix = Factory.buildMatrix(new double[][]{{10, 20, 30},{40, 50, 60}});
        System.out.println("행렬 B: " + oneToSix + "B -> " + oneToSix.isSquare() + "\n\n");

        Matrix upper_tri = Factory.buildMatrix(new double[][]{{1,2,3},{0,4,5},{0,0,6}});
        Matrix not_upper_tri = Factory.buildMatrix(new double[][]{{1,2,3},{1,4,5},{0,0,6}});
        System.out.println("명세 41: 상삼각 행렬 여부 판별\n" + "행렬 A: " + oneToNine + "A -> " + oneToNine.isUpperTriangular());
        System.out.println("행렬 B: " + not_upper_tri + "B -> " + not_upper_tri.isUpperTriangular());
        System.out.println("행렬 C: " + upper_tri + "C -> " + upper_tri.isUpperTriangular() + "\n\n");

        Matrix lower_tri = Factory.buildMatrix(new double[][]{{1,0,0},{2,3,0},{4,5,6}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix not_lower_tri = Factory.buildMatrix(new double[][]{{1,0,1},{2,3,0},{4,5,6}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        System.out.println("명세 42: 하삼각 행렬 여부 판별\n" + "행렬 A: " + oneToNine + "A -> " + oneToNine.isLowerTriangular());
        System.out.println("행렬 B: " + not_lower_tri + "B -> " + not_lower_tri.isLowerTriangular());
        System.out.println("행렬 C: " + lower_tri + "C -> " + lower_tri.isLowerTriangular() + "\n\n");

        Matrix iden = Factory.buildMatrix(2); // 명세 10: 단위 행렬 생성
        System.out.println("명세 43: 단위 행렬 여부 판별\n" + "행렬 A:" + oneToNine + "A -> " + oneToNine.isIdentity());
        System.out.println("행렬 B:" + iden + "B -> " + iden.isIdentity()+"\n\n");

        Matrix zeroMatrix = Factory.buildMatrix(2,3,0.0); // 명세 06: 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성
        System.out.println("명세 44: 영 행렬 여부 판별\n" + "행렬 A:" + oneToNine + "A -> " + oneToNine.isZeroMatrix());
        System.out.println("행렬 B:" + zeroMatrix + "B -> " + zeroMatrix.isZeroMatrix()+"\n\n");
    }

    public static void testMatrixElementaryOperations() {
        printTitle("Matrix Elementary Row/Column Operations (45-50)");
        Matrix oneToNine = Factory.buildMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});

        System.out.print("명세 45: 두 행 교환\n" + "기준 행렬: " + oneToNine);
        oneToNine.swapRows(0,1);
        System.out.println("swap(0행, 1행) -> " + oneToNine + "\n\n");
        oneToNine.swapRows(0,1);

        System.out.print("명세 46: 두 열 교환\n" + "기준 행렬: " + oneToNine);
        oneToNine.swapCols(0,2);
        System.out.println("swap(0열, 1열) -> "+ oneToNine + "\n\n");
        oneToNine.swapCols(0,2); // Swap back

        System.out.print("명세 47: 특정 행 상수배\n" + "기준 행렬: " + oneToNine);
        oneToNine.multiplyRow(0, Factory.buildScalar("2"));
        System.out.println("0행 2배 -> " + oneToNine + "\n\n");
        oneToNine.multiplyRow(0, Factory.buildScalar("0.5"));

        System.out.print("명세 48: 특정 열 상수배\n" + "기준 행렬: " + oneToNine);
        oneToNine.multiplyCol(1, Factory.buildScalar("3"));
        System.out.println("1열 3배 -> " + oneToNine + "\n\n");
        oneToNine.multiplyCol(1, Factory.buildScalar(BigDecimal.ONE.divide(new BigDecimal("3"), 10, RoundingMode.HALF_UP).toString())); // Undo???????????

        System.out.print("명세 49: 특정 행에 다른 행 상수배 더하기\n" + "기준 행렬: " + oneToNine);
        oneToNine.addScaledRow(1, 0, Factory.buildScalar("2")); // 명세 49: 특정 행에 다른 행의 상수배를 더함 (R1 = R1 + 2*R0)
        System.out.println("1행에 0행의 2배 더함 -> " + oneToNine + "\n");
        oneToNine.addScaledRow(1, 0, Factory.buildScalar("-2"));

        System.out.print("명세 50: 특정 열에 다른 열 상수배 더하기\n" + "기준 행렬: " + oneToNine);
        oneToNine.addScaledCol(1, 2, Factory.buildScalar("-1"));
        System.out.println("1열에 2열의 -1배 더함 -> " + oneToNine);
    }


    public static void testMatrixRREFDeterminantInverse() {
        printTitle("Matrix RREF, Determinant, Inverse (51-54)");
        Matrix oneToNine = Factory.buildMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix testMatrix = Factory.buildMatrix(new double[][]{{1,2,1},{0,1,3},{4,0,1}});

        System.out.print("명세 51: RREF 변환\n" + "행렬 A: " + oneToNine);
        Matrix oneToNineRREF = oneToNine.rref();
        System.out.print("A -> " + oneToNineRREF);
        Matrix testMatrixRREF = testMatrix.rref();
        System.out.print("행렬 B: " + testMatrix);
        System.out.println("B -> " + testMatrixRREF + "\n");
        //???????????????????????????????????????????//???????????????????????????????????????????


        System.out.println("명세 52: RREF 행렬 여부 판별\n" + "행렬 A: " + oneToNine + "A -> " + oneToNine.isRREF());
        System.out.println("행렬 B: " + oneToNineRREF + "B -> " + oneToNineRREF.isRREF() + "\n\n");

        Scalar det = oneToNine.determinant();
        System.out.println("명세 53: 행렬식 계산\n" + "기준 행렬: " + oneToNine + "행렬식 -> " + det + "\n\n");

        try {
            Matrix testMatrixInverse = testMatrix.inverse();
            System.out.print("명세 54: 역행렬 계산\n" + "행렬 A: " + testMatrix + "A -> " + testMatrixInverse);
            Matrix product_check = Tensors.multiply(testMatrixInverse, testMatrix);
        } catch (SingularMatrixException e) {
            System.err.println("Error calculating inverse: " + e.getMessage() + "\n");
        }
        try {
            System.out.print("행렬 B: " + oneToNine);
            oneToNine.inverse();
        } catch (SingularMatrixException e) {
            System.out.print("B 예외 발생 확인 -> " + e.getMessage() + "\n");
//            System.err.println("Error calculating inverse: " + e.getMessage());
        }

        Matrix singular_m = Factory.buildMatrix(new double[][]{{1,2},{2,4}});
        try {
            System.out.print("행렬 C: " + singular_m);
            singular_m.inverse(); // Expected to throw SingularMatrixException
        } catch (SingularMatrixException e) {
            System.out.println("C 예외 발생 확인 -> " + e.getMessage());
        }
    }

    public static void testTensorsStaticMethods() {
        printTitle("Tensors Static Utility Methods");
        // Tensors.java 요구사항: 모든 메소드가 public static. 위의 디폴트 static 메소드들을 호출.
        Scalar ts1 = Factory.buildScalar("100"); // 명세 01: 값을 지정하여 스칼라 생성
        Scalar ts2 = Factory.buildScalar("25"); // 명세 01: 값을 지정하여 스칼라 생성
        // 명세 24: 전달받은 두 스칼라의 덧셈이 가능하다 (static, returns new)
        System.out.println("명세 24 (Tensors.add -> Scalar.add) 스칼라 덧셈 : Tensors.add(" + ts1 + ", " + ts2 + "): " + Tensors.add(ts1, ts2));
        // 명세 25: 전달받은 두 스칼라의 곱셈이 가능하다 (static, returns new)
        System.out.println("명세 25 (Tensors.multiply -> Scalar.multiply) 스칼라 곱셈 : Tensors.multiply(" + ts1 + ", " + ts2 + "): " + Tensors.multiply(ts1, ts2));
        Vector tv1 = Factory.buildVector(new double[]{1,2}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        Vector tv2 = Factory.buildVector(new double[]{3,4}); // 명세 05: 1차원 배열로부터 n-차원 벡터 생성
        // 명세 26: 전달받은 두 벡터의 덧셈이 가능하다 (static, returns new)
        System.out.println("명세 26 (Tensors.add -> Vector.add) 벡터 덧셈 : Tensors.add(" + tv1 + ", " + tv2 + "): " + Tensors.add(tv1, tv2));
        // 명세 27: 전달받은 스칼라와 벡터의 곱셈이 가능하다 (static, returns new)
        System.out.println("명세 27 (Tensors.multiply -> Vector.multiply) 벡터-스칼라 곱셈 : Tensors.multiply(" + tv1 + ", " + ts1 + "): " + Tensors.multiply(tv1, ts1));
        System.out.println("명세 27 (Tensors.multiply -> Vector.multiply) 스칼라-벡터 곱셈 (오버로딩) : Tensors.multiply(" + ts1 + ", " + tv1 + "): " + Tensors.multiply(ts1, tv1));

        Matrix tm1 = Factory.buildMatrix(new double[][]{{1,0},{0,1}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        Matrix tm2 = Factory.buildMatrix(new double[][]{{5,6},{7,8}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성
        // 명세 28: 전달받은 두 행렬의 덧셈이 가능하다 (static, returns new)
        System.out.println("명세 28 (Tensors.add -> Matrix.add) 행렬 덧셈 : Tensors.add(tm1, tm2):\n" + Tensors.add(tm1, tm2));
        // 명세 29: 전달받은 두 행렬의 곱셈이 가능하다 (static, returns new)
        System.out.println("명세 29 (Tensors.multiply -> Matrix.multiply) 행렬 곱셈 : Tensors.multiply(tm1, tm2):\n" + Tensors.multiply(tm1, tm2));
        Matrix tm3 = Factory.buildMatrix(new double[][]{{1},{2}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x1)
        Matrix tm4 = Factory.buildMatrix(new double[][]{{3},{4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (2x1)
        // 명세 32: 행렬은 다른 행렬과 가로로 합쳐질 수 있다 (static)
        System.out.println("명세 32 (Tensors.hstack -> Matrix.hstack) 행렬 가로 합치기 : Tensors.hstack(tm3, tm4):\n" + Tensors.hstack(tm3, tm4));
        Matrix tm5 = Factory.buildMatrix(new double[][]{{1,2}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (1x2)
        Matrix tm6 = Factory.buildMatrix(new double[][]{{3,4}}); // 명세 09: 2차원 배열로부터 m x n 행렬 생성 (1x2)
        // 명세 33: 행렬은 다른 행렬과 세로로 합쳐질 수 있다 (static)
        System.out.println("명세 33 (Tensors.vstack -> Matrix.vstack) 행렬 세로 합치기 : Tensors.vstack(tm5, tm6):\n" + Tensors.vstack(tm5, tm6));
        // Tensors utility methods (명세 외 추가된 Tensors 메소드들)
        // Tensors.identity -> 명세 10: 단위 행렬 생성
        System.out.println("Tensors 유틸리티 (identity -> 명세 10) : Tensors.identity(3):\n" + Tensors.identity(3));
        // Tensors.zeros -> 명세 06: 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성 (0.0으로 채움)
        System.out.println("Tensors 유틸리티 (zeros -> 명세 06) : Tensors.zeros(2,3):\n" + Tensors.zeros(2,3));
        // Tensors.ones -> 명세 06: 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성 (1.0으로 채움)
        System.out.println("Tensors 유틸리티 (ones -> 명세 06) : Tensors.ones(3,2):\n" + Tensors.ones(3,2));
        // Tensors.filled -> (유사) 명세 09: 2차원 배열로부터 m x n 행렬 생성 (내부적으로 Scalar[][] 사용)
        System.out.println("Tensors 유틸리티 (filled -> 유사 명세 09) : Tensors.filled(2,2, Factory.buildScalar(\"7\")):\n" + Tensors.filled(2,2, Factory.buildScalar("7")));
    }

    public static void testExceptionHandling() {
        printTitle("Exception Handling Tests");
        // 예외 클래스 정의 및 사용 테스트

        // Scalar creation exceptions
        try {
            System.out.print("명세 01 (오류 테스트) 잘못된 문자열 스칼라 생성 시도 : Test: Factory.buildScalar(\"abc\") -> ");
            Factory.buildScalar("abc"); // Expected: IllegalArgumentException from BigDecimal
        } catch (IllegalArgumentException e) {
            System.out.println("명세 01 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
            System.out.print("명세 02 (오류 테스트) 잘못된 범위 무작위 스칼라 생성 시도 : Test: Factory.buildScalar(5,2) -> ");
            Factory.buildScalar(5,2); // Expected: IllegalArgumentException from ScalarImpl constructor
        } catch (IllegalArgumentException e) {
            System.out.println("명세 02 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Vector dimension mismatch
        Vector v_exc1 = Factory.buildVector(2, 1.0); // 명세 03
        Vector v_exc2 = Factory.buildVector(3, 1.0); // 명세 03
        try {
            System.out.print("명세 20, 26 (오류 테스트) 벡터 덧셈 차원 불일치 시도 : Test: v_exc1.add(v_exc2) (size mismatch) -> ");
            v_exc1.add(v_exc2); // Expected: DimensionMismatchException
        } catch (DimensionMismatchException e) {
            System.out.println("명세 20, 26 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Matrix dimension mismatch for add
        Matrix m_exc1 = Factory.buildMatrix(2, 2, 1.0); // 명세 06
        Matrix m_exc2 = Factory.buildMatrix(2, 3, 1.0); // 명세 06
        try {
            System.out.print("명세 22, 28 (오류 테스트) 행렬 덧셈 차원 불일치 시도 : Test: m_exc1.add(m_exc2) (dim mismatch) -> ");
            m_exc1.add(m_exc2); // Expected: DimensionMismatchException
        } catch (DimensionMismatchException e) {
            System.out.println("명세 22, 28 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Matrix dimension mismatch for multiply
        Matrix m_exc3 = Factory.buildMatrix(2, 3, 1.0); // 명세 06 (2x3)
        Matrix m_exc4 = Factory.buildMatrix(2, 2, 1.0); // 명세 06 (2x2)
        try {
            System.out.print("명세 23, 29 (오류 테스트) 행렬 곱셈 차원 불일치 시도 : Test: m_exc3.multiplyRight(m_exc4) (dim mismatch) -> ");
            m_exc3.multiplyRight(m_exc4); // Expected: DimensionMismatchException (cols of m1 != rows of m2)
        } catch (DimensionMismatchException e) {
            System.out.println("명세 23, 29 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Index out of bounds
        try {
            System.out.print("명세 11v (오류 테스트) 벡터 요소 접근 범위 초과 시도 : Test: v_exc1.viewElement(5) -> ");
            v_exc1.viewElement(5); // Expected: IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("명세 11v (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
            System.out.print("명세 11m (오류 테스트) 행렬 요소 접근 범위 초과 시도 : Test: m_exc1.viewElement(5,5) -> ");
            m_exc1.viewElement(5,5); // Expected: IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("명세 11m (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // CSV Parse Exception
        String badCsvFile = "bad_test_matrix.csv";
        try {
            createTestCsvFile(badCsvFile, new String[]{"1,2,3", "4,not_a_number,6"});
            System.out.print("명세 08 (오류 테스트) 잘못된 형식의 CSV 파일 파싱 시도 : Test: Factory.buildMatrix from bad CSV -> ");
            Factory.buildMatrix(badCsvFile); // Expected: CsvParseException (due to NumberFormatException)
        } catch (CsvParseException | IOException e) {
            System.out.println("명세 08 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
            createTestCsvFile(badCsvFile, new String[]{"1,2,3", "4,5"}); // Inconsistent columns
            System.out.print("명세 08 (오류 테스트) 일관되지 않은 열 개수의 CSV 파일 파싱 시도 : Test: Factory.buildMatrix from inconsistent CSV -> ");
            Factory.buildMatrix(badCsvFile); // Expected: CsvParseException
        } catch (CsvParseException | IOException e) {
            System.out.println("명세 08 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // NotSquareMatrixException
        Matrix rect_m_exc = Factory.buildMatrix(2,3,1.0); // 명세 06
        try {
            System.out.print("명세 39 (오류 테스트) 정사각 행렬 아닌 경우 trace 시도 : Test: rect_m_exc.trace() -> ");
            rect_m_exc.trace(); // Expected: NotSquareMatrixException
        } catch (NotSquareMatrixException e) {
            System.out.println("명세 39 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
            System.out.print("명세 53 (오류 테스트) 정사각 행렬 아닌 경우 determinant 시도 : Test: rect_m_exc.determinant() -> ");
            rect_m_exc.determinant(); // Expected: NotSquareMatrixException
        } catch (NotSquareMatrixException e) {
            System.out.println("명세 53 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        try {
            System.out.print("명세 54 (오류 테스트) 정사각 행렬 아닌 경우 inverse 시도 : Test: rect_m_exc.inverse() -> ");
            rect_m_exc.inverse(); // Expected: NotSquareMatrixException
        } catch (NotSquareMatrixException e) {
            System.out.println("명세 54 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // SingularMatrixException
        Matrix singular_m_exc = Factory.buildMatrix(new double[][]{{1,1},{1,1}}); // 명세 09 (Determinant is 0)
        try {
            System.out.print("명세 54 (오류 테스트) 특이 행렬 inverse 시도 : Test: singular_m_exc.inverse() -> ");
            singular_m_exc.inverse(); // Expected: SingularMatrixException
        } catch (SingularMatrixException e) {
            System.out.println("명세 54 (오류 테스트) 예외 발생 확인 : Caught expected: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
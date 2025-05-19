package tensor;

import java.util.List;

public interface Vector extends Cloneable {
    // 내부 List<Scalar> 직접 반환 대신, 불변 뷰 또는 복사본 반환 고려 가능 (여기서는 명세대로)
    List<Scalar> getScalars(); // 명칭 변경 getVector -> getScalars (명확성)

    // 11v. 특정 위치 요소 지정
    void setElement(int index, Scalar value);

    // 11v. 특정 위치 요소 조회
    Scalar viewElement(int index);

    // 13. 차원(길이) 조회
    int getSize(); // 명칭 변경 viewSize -> getSize

    // 30. n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환 (명세는 nx1 이지만 통상 row vector는 1xn)
    Matrix toRowMatrix();

    // 31. n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환 (명세는 1xn 이지만 통상 col vector는 nx1)
    Matrix toColMatrix();

    // 17v. 객체 복제
    Vector clone();

    // --- Non-static operations (modifies self) ---
    // 20. 벡터 덧셈
    Vector add(Vector other);

    // 21. 스칼라 곱셈
    Vector multiply(Scalar scalar);

    // --- Default static methods (returns new) ---
    // 26. 두 벡터 덧셈
    static Vector add(Vector v1, Vector v2) {
        if (v1.getSize() != v2.getSize()) {
            throw new DimensionMismatchException("Vectors must have the same size for addition. v1 size: " + v1.getSize() + ", v2 size: " + v2.getSize());
        }
        Vector result = v1.clone(); // 새 벡터 생성 기반으로
        for (int i = 0; i < result.getSize(); i++) {
            // result.setElement(i, Scalar.add(result.viewElement(i), v2.viewElement(i))); // clone을 했으므로 v1의 값을 사용
            result.setElement(i, Scalar.add(v1.viewElement(i), v2.viewElement(i)));
        }
        return result;
    }

    // 27. 스칼라와 벡터 곱셈
    static Vector multiply(Vector v, Scalar s) {
        Vector result = v.clone();
        for (int i = 0; i < result.getSize(); i++) {
            // result.setElement(i, Scalar.multiply(result.viewElement(i), s)); // clone을 했으므로 v의 값을 사용
            result.setElement(i, Scalar.multiply(v.viewElement(i), s));
        }
        return result;
    }
}
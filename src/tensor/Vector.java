package tensor;

import java.util.List;

public interface Vector extends Cloneable {
    List<Scalar> getScalars();

    // 11v. 특정 위치 요소 지정
    void setElement(int index, Scalar value);

    // 11v. 특정 위치 요소 조회
    Scalar viewElement(int index);

    // 13. 차원(길이) 조회
    int getSize();

    // 17v. 객체 복제
    Vector clone();

    // 20. 벡터 덧셈
    Vector add(Vector other);

    // 21. 스칼라 곱셈
    Vector multiply(Scalar scalar);

    // 30. n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환
    Matrix toRowMatrix();

    // 31. n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환
    Matrix toColMatrix();

    // 아래는 헬퍼 함수
    // Obj 클래스의 toString과 별개. 특정 scale로 스트링 변환
    public String toString(int scale);

}

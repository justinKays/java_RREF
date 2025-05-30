package tensor;

import java.util.List;

public interface Matrix extends Cloneable {
    List<Vector> getVectors();

    // 11m. 특정 위치 요소 지정
    void setElement(int rowIndex, int colIndex, Scalar value);

    // 11m. 특정 위치 요소 조회
    Scalar viewElement(int rowIndex, int colIndex);

    // 13. 행렬 크기 조회 (행 개수, 열 개수)
    int[] getSize(); // 명칭 변경 viewSize -> getSize

    // 17m. 객체 복제
    Matrix clone();

    // 22. 행렬 덧셈
    Matrix add(Matrix other);

    // 23. 행렬 곱셈
    Matrix multiplyLeft(Matrix other);

    Matrix multiplyRight(Matrix other);

    // 32. 가로 합치기
    Matrix hstack(Matrix other);

    // 33. 세로 합치기
    Matrix vstack(Matrix other);

    // 34. 특정 행을 벡터 형태로 추출
    Vector getRow(int rowIndex);

    // 35. 특정 열을 벡터 형태로 추출
    Vector getCol(int colIndex);

    // 36. 특정 범위의 부분 행렬 추출
    Matrix subMatrix(int startRow, int endRow, int startCol, int endCol);

    // 37. 특정 행/열 제외 부분 행렬 (minor)
    Matrix minor(int excludeRowIndex, int excludeColIndex);

    // 38. 전치행렬
    Matrix transpose();

    // 39. 대각 요소의 합 (nxn 행렬)
    Scalar trace();

    // 40. 정사각 행렬 여부 판별
    boolean isSquare();

    // 41. 상삼각 행렬 여부 판별 (nxn 행렬)
    boolean isUpperTriangular();

    // 42. 하삼각 행렬 여부 판별 (nxn 행렬)
    boolean isLowerTriangular();

    // 43. 단위 행렬 여부 판별 (nxn 행렬)
    boolean isIdentity();

    // 44. 영 행렬 여부 판별
    boolean isZeroMatrix();

    // 45. 특정 두 행의 위치 교환
    void swapRows(int rowIndex1, int rowIndex2);

    // 46. 특정 두 열의 위치 교환
    void swapCols(int colIndex1, int colIndex2);

    // 47. 특정 행에 상수배
    void multiplyRow(int rowIndex, Scalar scalar);

    // 48. 특정 열에 상수배
    void multiplyCol(int colIndex, Scalar scalar);

    // 49. 특정 행에 다른 행의 상수배를 더함 (targetRow += scalar * sourceRow)
    void addScaledRow(int targetRowIndex, int sourceRowIndex, Scalar scalar);

    // 50. 특정 열에 다른 열의 상수배를 더함 (targetCol += scalar * sourceCol)
    void addScaledCol(int targetColIndex, int sourceColIndex, Scalar scalar);

    // 51. RREF 행렬 구해서 반환 (새 행렬 반환)
    Matrix rref();

    // 52. 자신이 RREF 행렬인지 여부 판별
    boolean isRREF();

    // 53. 행렬식 (nxn 행렬)
    Scalar determinant();

    // 54. 역행렬 (nxn 행렬, 새 행렬 반환)
    Matrix inverse();

}

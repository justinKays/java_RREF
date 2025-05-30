package tensor;

import java.math.BigDecimal;

public interface Scalar extends Comparable<Scalar> {
    // 12. 값 조회
    BigDecimal getValue();

    // 12. 값 지정
    void setValue(BigDecimal value); 

    // 17s. 객체 복제
    Scalar clone();

    // 18. 스칼라 덧셈 
    Scalar add(Scalar other);

    // 19. 스칼라 곱셈 
    Scalar multiply(Scalar other);

    // 아래는 헬퍼 함수들
    // 0인지 판별
    public boolean isZero();
    
    // 1인지 판별
    public boolean isOne();

    // Obj 클래스의 toString과 별개. 특정 scale로 스트링 변환
    public String toString(int scale);
    
}

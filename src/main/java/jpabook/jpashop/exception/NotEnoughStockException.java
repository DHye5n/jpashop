package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException { // 사이트가 실행되는 동안 예외 처리
    // 기본 생성자
    public NotEnoughStockException() {
        super(); // 생성자 전용 super
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }


    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
    // cause : 이유

    protected NotEnoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package pcy.study.tobycleanspinrgpart1.domain;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}

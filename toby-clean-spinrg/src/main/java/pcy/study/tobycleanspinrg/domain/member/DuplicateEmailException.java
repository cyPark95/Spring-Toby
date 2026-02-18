package pcy.study.tobycleanspinrg.domain.member;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}

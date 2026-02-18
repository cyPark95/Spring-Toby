package pcy.study.tobycleanspinrg.domain.member;

public class DuplicateProfileException extends RuntimeException {

    public DuplicateProfileException(String message) {
        super(message);
    }
}

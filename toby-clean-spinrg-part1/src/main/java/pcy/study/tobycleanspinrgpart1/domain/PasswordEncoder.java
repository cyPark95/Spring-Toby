package pcy.study.tobycleanspinrgpart1.domain;

public interface PasswordEncoder {

    String encode(String password);

    boolean matched(String password, String passwordHash);
}

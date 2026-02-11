package pcy.study.tobycleanspinrg.domain;

public interface PasswordEncoder {

    String encode(String password);

    boolean matched(String password, String passwordHash);
}

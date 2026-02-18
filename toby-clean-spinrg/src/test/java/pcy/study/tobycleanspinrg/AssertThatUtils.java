package pcy.study.tobycleanspinrg;

import lombok.NonNull;
import org.assertj.core.api.AssertProvider;
import org.assertj.core.api.Assertions;
import org.springframework.test.json.JsonPathValueAssert;

import java.util.function.Consumer;

public class AssertThatUtils {

    public static @NonNull Consumer<AssertProvider<JsonPathValueAssert>> notNull() {
        return value -> Assertions.assertThat(value).isNotNull();
    }

    public static @NonNull Consumer<AssertProvider<JsonPathValueAssert>> equalsTo(String expected) {
        return value -> Assertions.assertThat(value).isEqualTo(expected);
    }
}

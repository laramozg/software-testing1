import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.Tan;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TanTest {

    @ParameterizedTest()
    @ValueSource(doubles = { -2 * Math.PI, -Math.PI, 0, 0+0.02, 10, -15})
    void checkDots(double param) {
        assertAll(
                () -> assertEquals(Math.tan(param), Tan.calc(param, 2), 0.1)
        );
    }

    @ParameterizedTest()
    @ValueSource(doubles = { -0.5 * Math.PI, -1.5 * Math.PI})
    void checkNegativeBound(double param) {
        assertAll(
                () -> assertEquals(Double.NEGATIVE_INFINITY, Tan.calc(param, 2), 0.0001)
        );
    }

    @ParameterizedTest()
    @ValueSource(doubles = { 0.5 * Math.PI, 2.5 * Math.PI})
    void checkPositiveBound(double param) {
        assertAll(
                () -> assertEquals(Double.POSITIVE_INFINITY, Tan.calc(param, 2), 0.0001)
        );
    }

    @ParameterizedTest()
    @ValueSource(doubles = { -Math.PI / 2 - 0.2, -Math.PI / 2 + 0.2, Math.PI / 2 - 0.2, Math.PI / 2 + 0.2})
    void checkNearBound(double param) {
        assertAll(
                () -> assertEquals(Math.tan(param), Tan.calc(param, 2), 0.4)
        );
    }

}

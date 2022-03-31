package pl.training;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @BeforeEach
    void beforeEach() {
        log.info("Before each");
    }

    @AfterEach
    void afterEach() {
        log.info("After each");
    }

    @BeforeAll
    static void beforeAll() {
        log.info("Before all");
    }

    @AfterAll
    static void afterAll() {
        log.info("After all");
    }

    @DisplayName("given two numbers")
    @Nested
    class GivenTWoNumbers {

        @Test
        void when_add_then_returns_their_sum() {
            // Given / Arrange
            var firstNumber = 1;
            var secondNumber = 3;
            // When / Act
            var actual = calculator.add(firstNumber, secondNumber);
            // Then / Assert
            assertEquals(1 + 3, actual);
        }

        @CsvFileSource(resources = "/data.csv")
        @ParameterizedTest(name = "attempt {index} with {0} - {1}")
        void when_substract_then_returns_their_difference(int firstValue, int secondValue) {
            assertEquals(firstValue - secondValue, calculator.substract(firstValue, secondValue));
        }

        @Test
        void and_divisor_equals_zero_then_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> calculator.divide(10,0));
        }

    }

    @Test
    void matchers_test() {
        assertThat(List.of(1, 2, 3, 4), hasItems(2, 4));
        assertThat("Java", both(containsString("va")).and(hasLength(4)));
        assertThat("22.2", new Digits());
    }

    @Test
    void async_test() throws InterruptedException {
        var latch = new CountDownLatch(1);
        var provider = new DataProvider();
        provider.getData(result -> {
            assertEquals("Success", result);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

}

class Digits extends TypeSafeMatcher<String> {


    @Override
    protected boolean matchesSafely(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Should contains digits");
    }

}

@Log
class DataProvider {

    void getData(Consumer<String> onLoad) {
        new Thread(() -> {
            fakeDelay();
            log.info("After sleep");
            onLoad.accept("Success");
        }).start();
    }

    @SneakyThrows
    private void fakeDelay() {
        Thread.sleep(1_000);
    }

}

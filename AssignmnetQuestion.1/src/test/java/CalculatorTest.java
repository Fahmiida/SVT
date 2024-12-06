import org.junit.jupiter.api.*;
import java.time.Duration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


//QuestionNo.1 to 7
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    void setUpBeforeAll() {
        System.out.println("Initializing resources before all tests.");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("Setting up before each test.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up after each test.");
    }

    @AfterAll
    void tearDownAfterAll() {
        System.out.println("Cleaning up resources after all tests.");
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
        assertNotEquals(4, calculator.add(2, 3), "2 + 3 should not equal 4");
    }

    @Test
    void testDivide() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0), "Division by zero should throw ArithmeticException");
        assertDoesNotThrow(() -> calculator.divide(10, 2), "Division by non-zero value should not throw an exception");
    }

    @Test
    void testNullValues() {
        assertNull(null, "Object should be null");
        assertNotNull(calculator, "Calculator object should not be null");
    }

    @Test
    void testArrayEquality() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual, "Arrays should be identical");
    }

    @Test
    void testSameInstance() {
        Calculator calc1 = calculator;
        assertSame(calc1, calculator, "Both references should point to the same instance");
    }

    @Test
    void testLineMatches() {
        List<String> expectedLines = List.of("Line1", "Line2", "Line3");
        List<String> actualLines = List.of("Line1", "Line2", "Line3");
        assertLinesMatch(expectedLines, actualLines, "Lines should match");
    }

    @Test
    void testExecutionTime() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            Thread.sleep(500); // Simulate a process
        }, "Method should complete within 1 second");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, 20, 30"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), () -> "Sum of " + a + " and " + b + " should be " + expected);
    }

    @RepeatedTest(5)
    void testGenerateRandomNumber() {
        int number = Integer.parseInt(calculator.generateRandomNumber());
        assertTrue(number >= 0 && number < 100, "Random number should be within 0 and 99");
    }
}

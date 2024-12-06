import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
// QuestionNo.5
public class GradeCalculatorTest {



    @DisplayName("Verify Grading System")

    @ParameterizedTest
    @CsvSource({
            "95, A",
            "85, B",
            "75, C",
            "65, D",
            "55, F"
    })
    void shouldReturnCorrectGrade(int score, String expectedGrade) {
        assertEquals(expectedGrade, GradeCalculator.getGrade(score), "Grade should match for the given score");
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "101"
    })
    void shouldThrowExceptionForInvalidScores(int invalidScore) {
        assertThrows(IllegalArgumentException.class, () -> GradeCalculator.getGrade(invalidScore), "Expected exception for invalid scores");
    }
}

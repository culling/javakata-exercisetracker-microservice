package ut.com.geneculling.javakata;

import com.geneculling.javakata.utils.ExerciseValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExerciseInputValidatorTest {
    @Test
    public void isValidDate_WhenEmpty_shouldReturnTrue_1(){
        assertTrue(ExerciseValidator.isValidDate(""));
    }

    @Test
    public void isValidDate_WhenEmpty_shouldReturnTrue_2(){
        assertTrue(ExerciseValidator.isValidDate(null));
    }

    @Test
    public void isValidDate_shouldReturnFalseWhenContainsLetters(){
        assertFalse(ExerciseValidator.isValidDate("a"));
    }

    @Test
    public void isValidDate_shouldReturnFalseWhenInvalid_nospaces_1(){
        assertFalse(ExerciseValidator.isValidDate("1111111111"));
    }

    @Test
    public void isValidDate_shouldReturnFalseWhenInvalid_impossibledate_1(){
        assertFalse(ExerciseValidator.isValidDate("2020 13 13"));
    }

    @Test
    public void isValidDate_withValidDate_shouldReturnTrue_1(){
        assertTrue(ExerciseValidator.isValidDate("2020 02 02"));
    }

    @Test
    public void isValidDate_withValidDate_shouldReturnTrue_2(){
        assertTrue(ExerciseValidator.isValidDate("2022 12 05"));
    }

    @Test
    public void isValidDuration_withValidDuration_shouldReturnTrue(){
        assertTrue(ExerciseValidator.isValidDuration("2"));
    }

    @Test
    public void isValidDuration_withInValidDuration_shouldReturnFalse_1(){
        assertFalse(ExerciseValidator.isValidDuration("apples"));
    }

    @Test
    public void isValidDuration_withInValidDuration_shouldReturnFalse_2(){
        assertFalse(ExerciseValidator.isValidDuration(""));
    }
    @Test
    public void isValidDuration_withInValidDuration_shouldReturnFalse_3(){
        assertFalse(ExerciseValidator.isValidDuration(null));
    }
}

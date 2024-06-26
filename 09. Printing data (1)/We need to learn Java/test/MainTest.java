import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.*;

public class MainTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void test() {
        Main.main(new String[]{""});
        String result = outputStreamCaptor.toString().trim();
        assertFalse("The output must not be empty.", result.isEmpty());
        String errorMessage = "The output must be:\n" +
                              "WE NEED TO\n\n" +
                              "LEARN JAVA\n\n" +
                              "AS QUICKLY AS POSSIBLE\n" +
                              "Actual output:\n" + result;
        String regex = "WE NEED TO(\\r\\n|\\r|\\n)+" +
                       "LEARN JAVA(\\r\\n|\\r|\\n)+" +
                       "AS QUICKLY AS POSSIBLE($|\\r\\n|\\r|\\n)";
        assertTrue(errorMessage, result.matches(regex));
    }
}
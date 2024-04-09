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
        String errorMessage = "The output must be:\nO X X\nO X O\nX O X\n\nActual output:\n" + result;
        assertTrue(errorMessage, result.matches("O X X(\\r\\n|\\r|\\n)O X O(\\r\\n|\\r|\\n)X O X($|\\r\\n|\\r|\\n)"));
    }
}
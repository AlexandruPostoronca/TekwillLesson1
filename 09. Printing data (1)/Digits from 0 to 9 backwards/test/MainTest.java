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
        String errorMessage = "The output must be:\n9 8 7 6 5 4 3 2 1 0\n\nActual output:\n" + result;
        assertTrue(errorMessage, result.matches("9\\s+8\\s+7\\s+6\\s+5\\s+4\\s+3\\s+2\\s+1\\s+0$"));
    }
}
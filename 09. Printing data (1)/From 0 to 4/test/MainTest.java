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
        String errorMessage = "The output must be:\n0\n1\n2\n3\n4\nActual output:\n" + result;
        assertTrue(errorMessage, result.matches("0(\\r\\n|\\r|\\n)1(\\r\\n|\\r|\\n)2(\\r\\n|\\r|\\n)3(\\r\\n|\\r|\\n)4($|\\r\\n|\\r|\\n)"));
    }
}
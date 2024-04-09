import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

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
        Main.main();
        String result = outputStreamCaptor.toString().trim();
        assertTrue("The output must start with 'Hello, '.", result.startsWith("Hello, "));
        assertTrue("The output must end with '!'.", result.endsWith("!"));
        assertTrue("You should include only your name!", result.matches("^Hello, .+!$"));
        assertFalse("You should not include `<` and `>`!", result.matches("^Hello, <.+>!$"));
    }
}
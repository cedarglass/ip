package callie;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CallieTest {
    @TempDir
    Path tempDir;

    @Test
    public void main_runsAndPrintsGoodbye() {
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        String originalUserDir = System.getProperty("user.dir");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setIn(new ByteArrayInputStream("bye\n".getBytes(StandardCharsets.UTF_8)));
        System.setProperty("user.dir", tempDir.toString());

        try {
            Callie.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
            System.setProperty("user.dir", originalUserDir);
        }

        String output = out.toString();
        assertTrue(output.contains("Hello, I'm Callie!"));
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }
}

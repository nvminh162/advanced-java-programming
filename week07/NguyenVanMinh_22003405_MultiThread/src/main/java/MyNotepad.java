import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyNotepad extends JFrame {
    public MyNotepad() {
        super("My Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//      Tao thanh menu voi nut open, save, exit
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);


//      Xu ly su kien nut open, mo file txt bat ky
        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                // 1. Without thread
                // loadingWithoutThread(fileName, textArea);
                // 2. With thread
                LoadingTask loadingTask = new LoadingTask(fileName, textArea);
                Thread thread = new Thread(loadingTask);
                thread.start();
            }
        });

        exitMenuItem.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyNotepad::new);
    }

    //sync
    private void loadingWithoutThread(String fileName, JTextArea textArea) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            textArea.setText("");
            String line = null;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                textArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//async
class LoadingTask implements Runnable {
    private final String fileName;
    private final JTextArea textArea;

    public LoadingTask(String fileName, JTextArea textArea) {
        this.fileName = fileName;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            textArea.setText("");
            String line = null;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                textArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
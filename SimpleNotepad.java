import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleNotepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem newItem, openItem, saveItem, exitItem;

    public SimpleNotepad() {
        // Frame title
        setTitle("Simple Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text area
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        // Menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            textArea.setText("");
        } else if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    textArea.read(br, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be opened.");
                }
            }
        } else if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(bw);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be saved.");
                }
            }
        } else if (e.getSource() == exitItem) {
            dispose();
        }
    }

    public static void main(String[] args) {
        // Let that GUI bloom like cherry blossoms ezz
        SwingUtilities.invokeLater(SimpleNotepad::new);
    }
}

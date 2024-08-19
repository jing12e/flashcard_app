package org.example.view;



import org.example.model.Flashcard;

import org.example.model.Flashcard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FlashcardManagerView extends JFrame {
    private JTextField wordField = new JTextField(15);
    private JTextField translationField = new JTextField(15);
    private JTextField idField = new JTextField(5);
    private JTextField levelField = new JTextField(5);
    private JButton createButton = new JButton("Create");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton viewButton = new JButton("View");
    private JButton listButton = new JButton("List All");
    private JTextArea displayArea = new JTextArea(10, 30);

    public FlashcardManagerView() {
        setTitle("Manage Flashcards");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        // ID
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("ID:"), c);
        c.gridx = 1;
        add(idField, c);

        // Word
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Word:"), c);
        c.gridx = 1;
        add(wordField, c);

        // Translation
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Translation:"), c);
        c.gridx = 1;
        add(translationField, c);

        // Level
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Level:"), c);
        c.gridx = 1;
        add(levelField, c);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 0));
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(listButton);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, c);

        // TextArea
        displayArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(scrollPane, c);

        setBackground(new Color(240, 240, 240));
    }

    public Flashcard getNewFlashcard() {
        try {
            return new Flashcard(
                    wordField.getText(),
                    translationField.getText(),
                    Integer.parseInt(idField.getText()),
                    Integer.parseInt(levelField.getText())
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID or Level", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public int getSelectedFlashcardId() {
        try {
            return Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public Flashcard getUpdatedFlashcard() {
        try {
            return new Flashcard(
                    wordField.getText(),
                    translationField.getText(),
                    Integer.parseInt(idField.getText()),
                    Integer.parseInt(levelField.getText())
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID or Level", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void refreshFlashcards(List<Flashcard> flashcards) {
        StringBuilder sb = new StringBuilder();
        for (Flashcard flashcard : flashcards) {
            sb.append("ID: ").append(flashcard.getId()).append("\n");
            sb.append("Word: ").append(flashcard.getWord()).append("\n");
            sb.append("Translation: ").append(flashcard.getTranslation()).append("\n");
            sb.append("Level: ").append(flashcard.getLevel()).append("\n");
            sb.append("Mastered: ").append(flashcard.isMastered() ? "Yes" : "No").append("\n");
            sb.append("-------------------------\n");
        }
        displayArea.setText(sb.toString());
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setViewButtonListener(ActionListener listener) {
        viewButton.addActionListener(listener);
    }

    public void setListButtonListener(ActionListener listener) {
        listButton.addActionListener(listener);
    }
}


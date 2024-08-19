package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FlashcardLearningView extends JFrame {
    private JTextArea wordDisplay = new JTextArea(3, 30);
    private JTextField answerField = new JTextField(20);
    private JButton submitAnswerButton = new JButton("Submit Answer");
    private JButton nextButton = new JButton("Next");
    private JButton prevButton = new JButton("Previous");
    private JButton showTranslationButton = new JButton("Show Translation");
    private JButton playAudioButton = new JButton("Play Audio");
    private JButton recordAudioButton = new JButton("Record Audio");
    private JLabel masteredLabel = new JLabel("Mastered: 0");
    private JLabel levelCountLabel = new JLabel("Levels: 0");
    private JLabel remainingLabel = new JLabel("Remaining: 0");
    private JTextArea feedbackArea = new JTextArea(5, 30);

    public FlashcardLearningView() {
        setTitle("Learning Mode");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        // Word Display
        wordDisplay.setEditable(false);
        wordDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        wordDisplay.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        controlPanel.add(wordDisplay, c);

        // Answer Field
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        controlPanel.add(new JLabel("Your Answer:"), c);

        c.gridx = 1;
        controlPanel.add(answerField, c);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 0));
        buttonPanel.add(showTranslationButton);
        buttonPanel.add(playAudioButton);
        buttonPanel.add(recordAudioButton);
        buttonPanel.add(submitAnswerButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        controlPanel.add(buttonPanel, c);

        // Feedback Area
        feedbackArea.setEditable(false);
        feedbackArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(feedbackArea);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        controlPanel.add(scrollPane, c);

        // Stats Labels
        JPanel statsPanel = new JPanel(new GridLayout(3, 1));
        statsPanel.add(masteredLabel);
        statsPanel.add(levelCountLabel);
        statsPanel.add(remainingLabel);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        controlPanel.add(statsPanel, c);

        add(controlPanel, BorderLayout.CENTER);
    }

    public String getAnswer() {
        return answerField.getText();
    }

    public void setWordDisplay(String word) {
        wordDisplay.setText(word);
    }

    public void setFeedback(String feedback) {
        feedbackArea.setText(feedback);
    }

    public void setMasteredCount(int count) {
        masteredLabel.setText("Mastered: " + count);
    }

    public void setLevelCount(int count) {
        levelCountLabel.setText("Levels: " + count);
    }

    public void setRemainingCount(int count) {
        remainingLabel.setText("Remaining: " + count);
    }

    public void setSubmitAnswerButtonListener(ActionListener listener) {
        submitAnswerButton.addActionListener(listener);
    }

    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void setPrevButtonListener(ActionListener listener) {
        prevButton.addActionListener(listener);
    }

    public void setShowTranslationButtonListener(ActionListener listener) {
        showTranslationButton.addActionListener(listener);
    }

    public void setPlayAudioButtonListener(ActionListener listener) {
        playAudioButton.addActionListener(listener);
    }

    public void setRecordAudioButtonListener(ActionListener listener) {
        recordAudioButton.addActionListener(listener);
    }
}

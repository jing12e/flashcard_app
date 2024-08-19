package org.example.view;

import org.example.view.FlashcardLearningView;
import org.example.view.FlashcardManagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton manageFlashcardsButton = new JButton("Manage Flashcards");
    private JButton learningModeButton = new JButton("Learning Mode");

    public MainView() {
        setTitle("Flashcard Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Add buttons to the frame
        add(manageFlashcardsButton);
        add(learningModeButton);
    }

    public void setManageFlashcardsButtonListener(ActionListener listener) {
        manageFlashcardsButton.addActionListener(listener);
    }

    public void setLearningModeButtonListener(ActionListener listener) {
        learningModeButton.addActionListener(listener);
    }
}



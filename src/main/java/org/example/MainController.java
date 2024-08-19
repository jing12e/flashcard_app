package org.example;

import org.example.controller.FlashcardController;
import org.example.controller.FlashcardLearningController;
import org.example.db.FlashcardDatabase;
import org.example.model.Flashcard;
import org.example.view.FlashcardLearningView;
import org.example.view.FlashcardManagerView;
import org.example.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainController {
    private MainView mainView;
    private FlashcardDatabase flashcardDatabase;
    private List<Flashcard> flashcards;

    public MainController() {
        flashcardDatabase = new FlashcardDatabase();
        flashcards = flashcardDatabase.loadFlashcards();

        /*if (flashcards.isEmpty()) {
            flashcards.add(new Flashcard("Hello", "Hola", 1, 1));
            flashcards.add(new Flashcard("Goodbye", "Adiós", 2, 1));
            flashcards.add(new Flashcard("Please", "Por favor", 3, 1));
            flashcardDatabase.saveFlashcards(flashcards);
        }*/

        mainView = new MainView();

        mainView.setManageFlashcardsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlashcardManagerView managerView = new FlashcardManagerView();
                new FlashcardController(managerView, flashcards, flashcardDatabase);
                managerView.setVisible(true);
            }
        });

        mainView.setLearningModeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlashcardLearningView learningView = new FlashcardLearningView();
                new FlashcardLearningController(flashcards, learningView);
                learningView.setVisible(true);
            }
        });

        // Setup to save data when the main window is closed
        mainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(mainView, "Do you want to save changes?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    flashcardDatabase.saveFlashcards(flashcards);
                }
                System.exit(0);
            }
        });

        mainView.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}

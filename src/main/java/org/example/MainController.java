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

        mainView.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}

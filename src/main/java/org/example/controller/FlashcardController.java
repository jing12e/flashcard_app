package org.example.controller;

import org.example.db.FlashcardDatabase;
import org.example.model.Flashcard;
import org.example.view.FlashcardManagerView;


import org.example.db.FlashcardDatabase;
import org.example.model.Flashcard;
import org.example.view.FlashcardManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FlashcardController {
    private FlashcardManagerView managerView;
    private List<Flashcard> flashcards;
    private FlashcardDatabase flashcardDatabase;

    public FlashcardController(FlashcardManagerView managerView, List<Flashcard> flashcards, FlashcardDatabase flashcardDatabase) {
        this.managerView = managerView;
        this.flashcards = flashcards;
        this.flashcardDatabase = flashcardDatabase;

        // Display the flashcards in the view and set up listeners
        setupView();
    }

    private void setupView() {
        managerView.refreshFlashcards(flashcards);

        managerView.setCreateButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flashcard newFlashcard = managerView.getNewFlashcard();
                if (newFlashcard != null) {
                    flashcards.add(newFlashcard);
                    flashcardDatabase.saveFlashcards(flashcards); // Save to JSON
                    managerView.refreshFlashcards(flashcards);
                }
            }
        });

        managerView.setDeleteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedId = managerView.getSelectedFlashcardId();
                flashcards.removeIf(flashcard -> flashcard.getId() == selectedId);
                flashcardDatabase.saveFlashcards(flashcards); // Save to JSON
                managerView.refreshFlashcards(flashcards);
            }
        });

        managerView.setUpdateButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flashcard updatedFlashcard = managerView.getUpdatedFlashcard();
                if (updatedFlashcard != null) {
                    for (int i = 0; i < flashcards.size(); i++) {
                        if (flashcards.get(i).getId() == updatedFlashcard.getId()) {
                            flashcards.set(i, updatedFlashcard);
                            break;
                        }
                    }
                    flashcardDatabase.saveFlashcards(flashcards); // Save to JSON
                    managerView.refreshFlashcards(flashcards);
                }
            }
        });
    }
}

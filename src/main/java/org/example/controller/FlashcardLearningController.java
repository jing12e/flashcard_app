package org.example.controller;

import org.example.model.Flashcard;
import org.example.view.FlashcardLearningView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class FlashcardLearningController {
    private FlashcardLearningView view;
    private List<Flashcard> flashcards;
    private int currentIndex;
    private int masteredCount;

    public FlashcardLearningController(List<Flashcard> flashcards, FlashcardLearningView view) {
        this.flashcards = flashcards;
        this.view = view;
        this.currentIndex = -1;
        this.masteredCount = 0;

        view.setSubmitAnswerButtonListener(new SubmitAnswerButtonListener());
        view.setNextButtonListener(new NextButtonListener());
        view.setPrevButtonListener(new PrevButtonListener());
        view.setShowTranslationButtonListener(new ShowTranslationButtonListener());
        view.setPlayAudioButtonListener(new PlayAudioButtonListener());
        view.setRecordAudioButtonListener(new RecordAudioButtonListener());

        showNextFlashcard();
    }

    private void showNextFlashcard() {
        if (flashcards.isEmpty()) {
            view.setWordDisplay("No flashcards available.");
            return;
        }
        currentIndex = (currentIndex + 1) % flashcards.size();
        Flashcard flashcard = flashcards.get(currentIndex);
        view.setWordDisplay(flashcard.getWord());
        view.setFeedback(""); // Clear feedback
        updateStats();
    }

    private void showPreviousFlashcard() {
        if (flashcards.isEmpty()) {
            view.setWordDisplay("No flashcards available.");
            return;
        }
        currentIndex = (currentIndex - 1 + flashcards.size()) % flashcards.size();
        Flashcard flashcard = flashcards.get(currentIndex);
        view.setWordDisplay(flashcard.getWord());
        view.setFeedback(""); // Clear feedback
        updateStats();
    }

    private void updateStats() {
        int remainingCount = (int) flashcards.stream().filter(f -> !f.isMastered()).count();
        view.setMasteredCount(masteredCount);
        view.setLevelCount((int) flashcards.stream().map(Flashcard::getLevel).distinct().count());
        view.setRemainingCount(remainingCount);
    }

    private class SubmitAnswerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentIndex < 0 || currentIndex >= flashcards.size()) {
                view.setFeedback("Invalid flashcard.");
                return;
            }
            Flashcard flashcard = flashcards.get(currentIndex);
            String userAnswer = view.getAnswer();
            if (userAnswer.equalsIgnoreCase(flashcard.getTranslation())) {
                if (!flashcard.isMastered()) { // Check if flashcard was not already mastered
                    flashcard.setMastered(true);
                    masteredCount++;
                    view.setFeedback("Correct! You mastered this flashcard.");
                } else {
                    view.setFeedback("You already mastered this flashcard.");
                }
            } else {
                view.setFeedback("Incorrect. The correct answer is: " + flashcard.getTranslation());
            }
            updateStats();
        }
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showNextFlashcard();
        }
    }

    private class PrevButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showPreviousFlashcard();
        }
    }

    private class ShowTranslationButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentIndex >= 0 && currentIndex < flashcards.size()) {
                Flashcard flashcard = flashcards.get(currentIndex);
                view.setFeedback("Translation: " + flashcard.getTranslation());
            }
        }
    }

    private class PlayAudioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement audio playback functionality
        }
    }

    private class RecordAudioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement audio recording functionality
        }
    }
}

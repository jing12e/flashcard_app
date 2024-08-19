package org.example.db;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Flashcard;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FlashcardDatabase {
    private static final String FILE_PATH = "flashcards.json";
    private static final String OUTPUT_FILE_PATH = "output.json";
    private Gson gson = new Gson();

    public void saveFlashcards(List<Flashcard> flashcards) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE_PATH)) {
            gson.toJson(flashcards, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Flashcard> loadFlashcards() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type flashcardListType = new TypeToken<ArrayList<Flashcard>>() {}.getType();
            return gson.fromJson(reader, flashcardListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

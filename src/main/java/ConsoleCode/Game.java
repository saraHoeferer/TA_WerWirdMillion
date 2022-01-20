package ConsoleCode;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

//Steuerung des Spiels

public class Game {
    //Einbinden der Fragen mittels der Gson-Methode fromJson(), Rückgabe eines ConsoleCode.Question[]
    public Question[] createQuestions() throws IOException {
        Gson gson = new Gson();
        String dirPath = new File("").getAbsolutePath();
        dirPath += "\\questions.json";
        Question[] questions = gson.fromJson(new FileReader(dirPath, StandardCharsets.UTF_8), Question[].class); // UTF_8 for mutated vowels
        return questions;
    }

    //Rückgabe einer ArrayList<ConsoleCode.Question>, die jeweils die Fragen einer bestimmten Kategorie enthält
    //die Fragen kommen aus dem ConsoleCode.Question[]
    public ArrayList<Question> getQuestionCategory(Question[] questions, int category) { //Fragen einer Kategorie
        ArrayList<Question> questionsCategory = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].getCategory() == category) {
                questionsCategory.add(questions[i]);
            }
        }
        return questionsCategory;
    }

    //Rückgabe einer zufällig ausgewählten Frage einer bestimmten Kategorie aus der ArrayList<ConsoleCode.Question>
    public Question getQuestionFromCategory(ArrayList<Question> questionsCategory) { //Frage aus Fragenkatalog einer Kategorie auswählen
        Random random = new Random();
        int randomInt = random.nextInt(questionsCategory.size());
        Question q1 = questionsCategory.get(randomInt);
        return q1;
    }
}

package ConsoleCode;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

//Game class
public class Game {

    //createQuestions imports the json-questions file using Gson-method fromJson()
    public Question[] createQuestions() throws IOException {        //IOException class signals failure during the import
        Gson gson = new Gson();
        String dirPath = new File("").getAbsolutePath();
        dirPath += "\\questions.json";
        Question[] questions = gson.fromJson(new FileReader(dirPath, StandardCharsets.UTF_8), Question[].class);  // UTF_8 is needed for mutated vowels
        return questions;              // return of Question array
    }


    //getQuestionCategory is a getter method that creates an ArrayList of questions for one of the 15 game categories
    public ArrayList<Question> getQuestionCategory(Question[] questions, int category) {        //method takes Question array and a category between 1 and 15 as parameters
        ArrayList<Question> questionsCategory = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {              //all questions of the passed category are added to an ArrayList
            if (questions[i].getCategory() == category) {
                questionsCategory.add(questions[i]);
            }
        }
        return questionsCategory;      //return of ArrayList of a certain category
    }


    //getQuestionFromCategory is a getter method that returns a random question from the ArrayList of a certain category
    public Question getQuestionFromCategory(ArrayList<Question> questionsCategory) {          //method takes ArrayList of a certain category as parameter
        Random random = new Random();
        int randomInt = random.nextInt(questionsCategory.size());    //random integer between 0 and size of ArrayList is created
        Question q1 = questionsCategory.get(randomInt);
        return q1;                    //return of question at index equal to random integer
    }
}

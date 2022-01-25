# Who wants to be a millionaire - Team Alphas
We programmed the game "Who wants to be a millionaire" in our project according to the TV-Show "Wer wird Millionär".
It is a knowledge game where you have to answer different questions to win the millions.
> The game language is German. Therefore, all questions and answers are German.

## Game
### Rules
> To win one million euros, you have to answer 15 different questions. These can be from all possible subject areas. When answering a question, you have four different and predetermined answer options. From there, you have to choose the right one. If you don't know the answer and don't want to guess, you have three different Jokers, which you can use once each. You can use them whenever you want, and you can also use all three of them at once.

> If you don't know the correct answer and don't want to guess, you can drop out at any time and receive the money you've won up to that point. However, if you answer a question incorrectly, you fall back to a fixed amount and may only take this.

### How to start the game
You can start the game by navigating to Gradle (in Intellij right toolbar elefant symbol) -> Tasks -> Application -> Run.

A window should open with the face of Günther Jauch. Then, just press the start button, and the game will begin.

### How to play the game
> We designed a GUI for a pleasing user experience. All you need is to click on the buttons provided in the GUI.

Different questions are asked depending on the category.
If a question is correct, you move up one category.

### Joker
There are three different jokers.
- **Fifty-fifty** Joker: two of the wrong answers are hidden, leaving only the correct answer and **one** wrong answer.
- **Help**Joker: Someone from our team will help you answer the question. 
> (Attention: The answer we give may not always be correct)
- **Second Chance** Joker: If you answered a question wrong, you could give another answer choice afterwards
> (Attention: Joker must be selected before)

### Categories
The categories are (also displayed in the GUI on the right side):

50€-> 100€ -> 200€ -> 300€ -> **500€ (1st security level)** -> 1.000€ -> 2.000€ -> 4.000€ -> 8.000€ -> **16.000€ (2nd security level)** -> 32.000€ -> 64.000€ -> 125.000€ -> 500.000€ -> 1.000.0000€

>The amount you are currently standing at is highlighted in the GUI on the right side.

### End
You can quit the game at the beginning of each question. Then, a window will open to ask if you want to leave the game or play again.
> When you reach the million or give a wrong answer, the window will automatically open.

### Prize money
- If you leave the game voluntarily, you win the amount you were standing at last.
- If you answer a question incorrectly, you fall back to the security level. If you lose before the 500€ question, you win 0€.
- If you make it to the million, you win the million.

## Code
### Classes
We have six classes in our program
- **Player**: Controls the spending of money and the increase of the category.
- **Question**: Responsible for hiding answer choices for the fifty-fifty joker and using the second chance joker.
- **Joker**: Controls the functions of each joker
- **Game**: Responsible for creating the question array and assigning the questions to their respective categories.
- **HelloFX**: Responsible for starting the graphical user interface
- **MainController**: Responsible for the game flow in the GUI and calling new windows.

### Questions
Our questions are stored in a JSON file called in the Game class to create the question array. `private ConsoleCode.Question[] createQuestions()`
> You may answer some questions twice after several playthroughs. We have close to 150 questions, and they are randomly picked each round.

### JavaFX
> We have programmed our GUI using JavaFX. The MainController class and the FXML files for styling the GUI are important here.

We have three FXML files for the different windows (start, game and end) and a MainController class that controls the whole game flow (incl. jokers, questions, categories, etc.).

package com.example.yekta.myquiz;

/**
 * Created by yekta on 26-Jan-18.
 */

public class Database {

    public int questionNumber = 6;
    public int optionNumber = 4;
    public String[] question = new String[questionNumber];
    public String[][] option = new String[questionNumber][optionNumber];

    public Database(){
        //Questions
        question[0] = "Who is this villain above?";
        question[1] = "Who killed Obi-Wan?";
        question[2] = "How Yoda died in Star Wars Return of the Jedi?";
        question[3] = "Who killed Sidious?";
        question[4] = "Where Luke is from?";
        question[5] = "Is Mance Windu a Sith Lord?";


        //Options
        option[0][0] = "Anakin Skywalker";
        option[0][1] = "Darth Vader";
        option[0][2] = "Darth Maul";
        option[0][3] = "Snoke";

        option[1][0] = "Darth Vader";
        option[1][1] = "Yoda";
        option[1][2] = "Jar Jar Binks";
        option[1][3] = "Anakin Skywalker";

        option[2][0] = "In his house";
        option[2][1] = "Vader killed him";
        option[2][2] = "Skywalker killed him";
        option[2][3] = "He killed himself";

        option[3][0] = "Me";
        option[3][1] = "Yoda";
        option[3][2] = "Jar Jar Binks";
        option[3][3] = "Vader";

        option[4][0] = "Tatooine";
        option[4][1] = "Naboo";
        option[4][2] = "Hoth";
        option[4][3] = "Coruscant";
    }



}

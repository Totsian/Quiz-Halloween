package com.example.quizhalloween.quest;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class QuestionGenerator {
    public static ArrayList<Question> getQuestions() {

        ArrayList<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Американцы", false));
        answers1.add(new Answer("Кельты", true));
        answers1.add(new Answer("Французы", false));
        answers1.add(new Answer("Белорусы", false));

        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("12 декабря", false));
        answers2.add(new Answer("31 октября", false));
        answers2.add(new Answer("В ночь с 31 октября на 1 ноября", true));
        answers2.add(new Answer("1 октября", false));

        ArrayList<Answer> answers3 = new ArrayList<>();
        answers3.add(new Answer("Ноябрьские святки", false));
        answers3.add(new Answer("Праздник плавающих яблок", true));
        answers3.add(new Answer("Колядки", false));
        answers3.add(new Answer("Праздник святых", false));

        ArrayList<Answer> answers4 = new ArrayList<>();
        answers4.add(new Answer("С Санта Клаусом", false));
        answers4.add(new Answer("Со окончанием сбора урожая", false));
        answers4.add(new Answer("С нечистой силой", true));
        answers4.add(new Answer("С тыквенным пирогом", false));

        ArrayList<Answer> answers5 = new ArrayList<>();
        answers5.add(new Answer("Черная кошка", false));
        answers5.add(new Answer("Метла", false));
        answers5.add(new Answer("Тыква", true));
        answers5.add(new Answer("Призраки", false));

        ArrayList<Answer> answers6 = new ArrayList<>();
        answers6.add(new Answer("Зеленый", true));
        answers6.add(new Answer("Черный", false));
        answers6.add(new Answer("Оранжевый", false));
        answers6.add(new Answer("Фиолетовый", false));

        ArrayList<Answer> answers7 = new ArrayList<>();
        answers7.add(new Answer("Надеть маску", true));
        answers7.add(new Answer("Съесть все конфеты", false));
        answers7.add(new Answer("Погладить черную кошку", false));
        answers7.add(new Answer("Кулон", false));

        ArrayList<Answer> answers8 = new ArrayList<>();
        answers8.add(new Answer("Коледование", true));
        answers8.add(new Answer("Лепка снежной бабы", false));
        answers8.add(new Answer("Сожжение масленицы", false));
        answers8.add(new Answer("Пасха", false));

        ArrayList<Answer> answers9 = new ArrayList<>();
        answers9.add(new Answer("Быть или не быть", false));
        answers9.add(new Answer("Кошелек или жизнь", true));
        answers9.add(new Answer("Жизнь или смерть", false));
        answers9.add(new Answer("Конфеты или жизнь", false));

        ArrayList<Answer> answers10 = new ArrayList<>();
        answers10.add(new Answer("Просить прощение", false));
        answers10.add(new Answer("Шутить", false));
        answers10.add(new Answer("Пугать", true));
        answers10.add(new Answer("Носить шляпы", false));

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Какой народ был «первооткрывателем» праздника Хэллоуин?", answers1));
        questions.add(new Question("Какого числа отмечается праздник?", answers2));
        questions.add(new Question("Как еще называют Хэллоуин?", answers3));
        questions.add(new Question("С чем ассоциируется этот праздник?", answers4));
        questions.add(new Question("Что является символом данного дня?", answers5));
        questions.add(new Question("Какой цвет не является символическим цветом дня всех Святых?", answers6));
        questions.add(new Question("Как можно защититься в канун Хэллоуина от злых духов?", answers7));
        questions.add(new Question("Какая русская традиция похожа на традиции Хэллоуина?", answers8));
        questions.add(new Question("Какую фразу часто можно услышать во время праздника?", answers9));
        questions.add(new Question("Что принято делать на Хэллоуин?", answers10));

        return questions;
    }
}

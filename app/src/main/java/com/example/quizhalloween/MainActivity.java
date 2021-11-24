package com.example.quizhalloween;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.quizhalloween.DialogFragment;
import com.example.quizhalloween.DialogUser;
import com.example.quizhalloween.FragmentChoose;
import com.example.quizhalloween.FragmentDoctor;
import com.example.quizhalloween.FragmentQuiz;
import com.example.quizhalloween.FragmentRating;
import com.example.quizhalloween.FragmentResult;
import com.example.quizhalloween.FragmentStart;
import com.example.quizhalloween.OnSelectedButtonListener;
import com.example.quizhalloween.R;

public class MainActivity extends AppCompatActivity implements OnSelectedButtonListener {

    public static final String KEY_RESULT = "key_result";
    int result = 0;

    FragmentStart fragmentStart;
    FragmentQuiz fragmentQuiz;
    FragmentResult fragmentResult;
    FragmentRating ratingResult;
    FragmentChoose fragmentChoose;
    FragmentDoctor fragmentDoctor;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        fragmentStart = new FragmentStart();
        ft.add(R.id.containerFrag, fragmentStart);
        ft.commit();
    }

    @Override
    public void onButtonSelected(int buttonIndex) {

//      переключение на фрагмент с квизом Хэллоуин
        if (buttonIndex == 1) {
            ft = getSupportFragmentManager().beginTransaction();
            fragmentQuiz = new FragmentQuiz();
            ft.replace(R.id.containerFrag, fragmentQuiz, "fragmentQuiz");
            ft.addToBackStack(null);
            ft.commit();

//      переключение на выход
        } else if (buttonIndex == 2) {
            FragmentManager manager = getSupportFragmentManager();
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(manager, "DialogFragment");

//      переключение на результат после игры
        } else if (buttonIndex == 3) {
            ft = getSupportFragmentManager().beginTransaction();
            Bundle resultVol = getIntent().getExtras();
            result = resultVol.getInt(KEY_RESULT);

            fragmentResult = new FragmentResult(result);
            ft.replace(R.id.containerFrag, fragmentResult, "fragmentRezult");
            ft.addToBackStack(null);
            ft.commit();

//      переключение на стартовый фрагмент
        } else if (buttonIndex == 4) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.containerFrag, fragmentStart);
            ft.addToBackStack(null);
            ft.commit();

//      переключение на диалог с введением данных пользователя
        } else if (buttonIndex == 5) {
            Bundle res = getIntent().getExtras();
            int resUser = res.getInt(KEY_RESULT);

            FragmentManager fm = getSupportFragmentManager();
            DialogUser dialogUser = new DialogUser(resUser);
            dialogUser.show(fm, "DialogUser");

//      переключение на фрагмент с рейтингом
        } else if (buttonIndex == 6) {
            ft = getSupportFragmentManager().beginTransaction();

            ratingResult = new FragmentRating();
            ft.replace(R.id.containerFrag, ratingResult);
            ft.addToBackStack(null);
            ft.commit();

//      переключение на фрагмент с выбором квиза
        } else if (buttonIndex == 7) {
            ft = getSupportFragmentManager().beginTransaction();
            fragmentChoose = new FragmentChoose();
            ft.replace(R.id.containerFrag, fragmentChoose);
            ft.addToBackStack(null);
            ft.commit();

//      переключение на фрагмент с квизом Доктор Кто
        } else if (buttonIndex == 8) {
            ft = getSupportFragmentManager().beginTransaction();
            fragmentDoctor = new FragmentDoctor();
            ft.replace(R.id.containerFrag, fragmentDoctor);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
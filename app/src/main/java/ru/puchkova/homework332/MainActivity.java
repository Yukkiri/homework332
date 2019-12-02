package ru.puchkova.homework332;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner languageSpinner;
    private Spinner colorSpinner;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        init();
    }

    private void init(){
        languageSpinner = findViewById(R.id.languageSpinner);
        ok = findViewById(R.id.ok);
        colorSpinner = findViewById(R.id.colorSpinner);
        initSpinnerLanguage();
        initSpinnerColor();

        ok.setOnClickListener(onClickListener);
    }

    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapterLanguage);
    }


    private void initSpinnerColor() {
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this, R.array.color, android.R.layout.simple_spinner_item);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Locale localeRu = new Locale("ru");
            Locale localeEn = new Locale("en");
            Configuration config = new Configuration();
            String language = languageSpinner.getSelectedItem().toString();
            if (language.equals("English")){
                config.setLocale(localeEn);
            } else {
                config.setLocale(localeRu);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
            getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            recreate();

            int color = colorSpinner.getSelectedItemPosition();;
            setColor(color);
        }
    };

    @IntDef({Color.RED, Color.GREEN, Color.BLUE})
    private @interface Color {
        int RED = 0;
        int GREEN = 1;
        int BLUE = 2;
    }

    private void setColor(int color){
        switch (color){
            case 0:
                Utils.changeToTheme(this, Utils.THEME_RED);
                break;
            case 1:
                Utils.changeToTheme(this, Utils.THEME_DEFAULT);
                break;
            case 2:
                Utils.changeToTheme(this, Utils.THEME_BLUE);
                break;
        }
    }
}

package com.example.raider.calkunits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import static com.example.raider.calkunits.Constants.*;

public class MainActivity extends AppCompatActivity {
    private boolean sourceUnitActive, sourceMmActive, sourceHolesActive;
    private EditText editUnit, editMM, editHoles;
    private Thread engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUnit = (EditText) findViewById(R.id.editUnits);
        editMM = (EditText) findViewById(R.id.editMM);
        editHoles = (EditText) findViewById(R.id.editHoles);
        engine = null;
        sourceUnitActive = false;
        sourceMmActive = false;
        sourceHolesActive = false;

        editUnit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sourceUnitActive = hasFocus;

            }
        });
        editMM.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sourceMmActive = hasFocus;
            }
        });
        editHoles.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sourceHolesActive = hasFocus;
            }
        });

        editUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sourceUnitActive) {
                    interruptEngine();
                    engine = createEngineThread(s, SOURCE_UNIT);
                    engine.start();
                }
            }
        });


        editMM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sourceMmActive) {
                    interruptEngine();
                    engine = createEngineThread(s, SOURCE_MM);
                    engine.start();
                }
            }
        });

        editHoles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sourceHolesActive) {
                    interruptEngine();
                    engine = createEngineThread(s, SOURCE_HOLES);
                    engine.start();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != null) {
            engine.interrupt();
        }
    }

    private Thread createEngineThread(Editable s, int sourceConstant) {
        return new Thread(new RunCalculation(editUnit, editMM, editHoles, s, sourceConstant));
    }

    private void interruptEngine () {
        if (engine != null) {
            Thread dummy = engine;
            engine = null;
            dummy.interrupt();
        }
    }
}

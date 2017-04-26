package com.example.raider.calkunits;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.raider.calkunits.Constants.*;

/**
 * Created by RAIDER on 07.04.2017.
 */

class RunCalculation implements Runnable {
    private EditText unit, mm, hole;
    private Editable currentValue;
    private int source;

    RunCalculation(EditText unit, EditText mm, EditText hole, Editable currentValue, int source) {
        this.unit = unit;
        this.mm = mm;
        this.hole = hole;
        this.currentValue = currentValue;
        this.source = source;
    }

    @Override
    public void run() {
        if (!(currentValue.toString()).equals("")) {
            final float current = Float.parseFloat(currentValue.toString());
            switch (source) {
                case SOURCE_UNIT:
                    final float resultMM0 = current * MM_IN_UNIT;
                    final int resultHoles0 = (int) (current * HOLES_IN_UNIT);
                    mm.post(new Runnable() {
                        @Override
                        public void run() {
                            mm.setText(String.format("%.2f", resultMM0).replace(",", "."), TextView.BufferType.EDITABLE);
                            hole.setText(String.valueOf(resultHoles0), TextView.BufferType.EDITABLE);
                        }
                    });
                    break;
                case SOURCE_MM:
                    final float resultUnit1 = current / MM_IN_UNIT;
                    final int resultHoles1 = (int) (resultUnit1 * HOLES_IN_UNIT);
                    unit.post(new Runnable() {
                        @Override
                        public void run() {
                            unit.setText(String.format("%.2f", resultUnit1).replace(",", "."), TextView.BufferType.EDITABLE);
                            hole.setText(String.valueOf(resultHoles1), TextView.BufferType.EDITABLE);
                        }
                    });
                    break;
                case SOURCE_HOLES:
                    final float resultUnit2 = current / HOLES_IN_UNIT;
                    final float resultMM2 = resultUnit2 * MM_IN_UNIT;
                    unit.post(new Runnable() {
                        @Override
                        public void run() {
                            unit.setText(String.format("%.2f", resultUnit2).replace(",", "."), TextView.BufferType.EDITABLE);
                            mm.setText(String.format("%.2f", resultMM2).replace(",", "."), TextView.BufferType.EDITABLE);
                        }
                    });
            }
        }

    }
}

package halloandroid.sabel.com.hallotablet;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvNachricht;
    private Button btnWeiterFertig;
    private EditText etEingabe;
    private boolean ersterKLick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNachricht = findViewById(R.id.tv_nachricht);
        btnWeiterFertig = findViewById(R.id.btn_weiter_fertig);
        btnWeiterFertig.setEnabled(false);
        etEingabe = findViewById(R.id.et_eingabe);

        tvNachricht.setText(R.string.willkommen);
        btnWeiterFertig.setText(R.string.weiter);
        ersterKLick = true;

        // Event Handler für den Button
        btnWeiterFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ersterKLick) {
                    tvNachricht.setText(getString(R.string.hallo, etEingabe.getText()));
                    etEingabe.setVisibility(View.INVISIBLE);
                    btnWeiterFertig.setText(R.string.fertig);
                    ersterKLick = false;
                } else {
                    finish();
                }
            }
        });
        // Mit Eingabe der Entertaste geht es auch weiter
        etEingabe.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (btnWeiterFertig.isEnabled()) {
                    btnWeiterFertig.performClick();
                }
                return true;
            }
        });


        // Button erst nach Eingabe des Namens aktivieren
        etEingabe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnWeiterFertig.setEnabled(editable.toString().contains(" "));
            }
        });

    }
}

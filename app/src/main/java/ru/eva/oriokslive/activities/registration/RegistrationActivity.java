package ru.eva.oriokslive.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.main.MainActivity;

public class RegistrationActivity extends AppCompatActivity implements ContractRegistrationActivity.View {

    private ContractRegistrationActivity.Presenter mPresenter;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText login = findViewById(R.id.login);
        EditText password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        mPresenter = new PresenterRegistrationActivity(this);

        button.setOnClickListener(view-> {
            mPresenter.onButtonWasClicked(login.getText().toString(), password.getText().toString());
            button.setEnabled(false);
        });
    }


    @Override
    public void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setButtonEnabled() {
        button.setEnabled(true);
    }
}

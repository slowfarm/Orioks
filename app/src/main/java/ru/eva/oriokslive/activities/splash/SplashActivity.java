package ru.eva.oriokslive.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.main.MainActivity;
import ru.eva.oriokslive.activities.registration.RegistrationActivity;

public class SplashActivity extends AppCompatActivity implements ContractSplashActivity.View {

    private ContractSplashActivity.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new PresenterSplashActivity(this);
        mPresenter.checkAccessToken();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void startRegistrationActivity() {
        startActivity(new Intent(this, RegistrationActivity.class));
        finish();
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

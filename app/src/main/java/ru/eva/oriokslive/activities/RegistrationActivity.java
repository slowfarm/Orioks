package ru.eva.oriokslive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;

public class RegistrationActivity extends AppCompatActivity implements OnTokenRecieved {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText login = findViewById(R.id.login);
        EditText password = findViewById(R.id.password);
        Button button = findViewById(R.id.button);
        if(StorageHelper.getInstance().getAccessToken(this) != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        button.setOnClickListener(view-> {
            if(login.getText().toString().equals("")) {
                Toast.makeText(this, "Пожалуйста введите логин", Toast.LENGTH_LONG).show();
            } else if(password.getText().toString().equals("")) {
                Toast.makeText(this, "Пожалуйста введите пароль", Toast.LENGTH_LONG).show();
            } else {
                RetrofitHelper.getInstance().setOnTokenReceived(this);
                String encodedString = "Basic "+Base64.encodeToString((login.getText().toString()+":"+password.getText().toString()).getBytes(), Base64.NO_WRAP);
                StorageHelper.getInstance().setLoginAndPassword(this, encodedString);
                RetrofitHelper.getInstance().getAccessToken(encodedString);
            }
        });
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        if(accessToken != null) {
            StorageHelper.getInstance().setAccessToken(this, accessToken.getToken());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Неверный логин либо пароль", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }
}

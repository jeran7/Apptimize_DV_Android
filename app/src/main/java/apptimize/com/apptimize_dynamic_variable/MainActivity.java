package apptimize.com.apptimize_dynamic_variable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apptimize.Apptimize;
import com.apptimize.ApptimizeTest;
import com.apptimize.ApptimizeVar;

public class MainActivity extends AppCompatActivity {
    Button loginWithGuestButton ;
    Button loginButton ;
    private static ApptimizeVar<Integer> buttonColor = ApptimizeVar.createInteger("buttonColor",1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Apptimize.setup(this, "DE9qRfhTaMsZvQcTaEnBL3xpyc9LLUY");
        loginButton = findViewById(R.id.button);
        loginWithGuestButton = findViewById(R.id.button2);
        Integer buttonColorInteger = buttonColor.value();
//        Apptimize.runTest("GuestFlow", new ApptimizeTest() {
//            @Override
//            public void baseline() {
//                loginWithGuestButton.setVisibility(View.INVISIBLE);
//                System.out.println("Var 1");
//            }
//
//            @SuppressWarnings("unused")
//            public void variation1() {
//                loginWithGuestButton.setVisibility(View.VISIBLE);
//                System.out.println("Var 2");
//            }
//        });

        System.out.println("Hello "+buttonColorInteger);


        switch (buttonColorInteger){
            case 2 :  loginWithGuestButton.setBackgroundColor(Color.GRAY); break;
            case 3 :  loginWithGuestButton.setBackgroundColor(Color.CYAN); break;
        }
        Apptimize.runTest("buttonColor", new ApptimizeTest() {
            @Override
            public void baseline() {
                // Variant: original

            }

            @SuppressWarnings("unused")
            public void variation1() {
                loginWithGuestButton.setBackgroundColor(Color.GRAY);
                System.out.println("Var 2");
                // Variant: Grey_Button
            }

            @SuppressWarnings("unused")
            public void variation2() {
                loginWithGuestButton.setBackgroundColor(Color.CYAN);
                System.out.println("Var 3");
                // Variant: Cyan_Button
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LandingPage.class));
            }
        });
        loginWithGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apptimize.track("Button Clicked");
                startActivity(new Intent(MainActivity.this, LandingPage.class));
            }
        });
    }
}

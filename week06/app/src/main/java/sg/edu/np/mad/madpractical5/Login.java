package sg.edu.np.mad.madpractical5;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
 FirebaseAuth mAuth;
 EditText username,password;
 Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submitbtn = findViewById(R.id.submit_button);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Lusername,Lpassword;
                Lusername = String.valueOf(username.getText());
                Lpassword = String.valueOf(password.getText());
                mAuth.signInWithEmailAndPassword(Lusername,Lpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.d(TAG,"Sign in with email successful");
                                    Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                                    startActivity(intent);
                                }else{
//                                    Log.W(TAG,"unsuccessful",task.getException());

                                }
                            }
                        });
            }
        });

    }
}
package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.api.internal.DataHolderNotifier;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
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
        EditText username = findViewById(R.id.Username_edti_text);
        EditText password = findViewById(R.id.Password_edit_text);
        Button login_button = findViewById(R.id.login_button);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");
//                myRef.child("user").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            Log.i("MAOMAOO","testing");
//                            DataSnapshot dataSnapShot = task.getResult();
//                            String name = String.valueOf(dataSnapShot.child("name").getValue());
//                            String password = String.valueOf(dataSnapShot.child("password").getValue());
//
//                            Log.i("MAOMAOO",name);
//                            Log.i("MAOMAOO",password);
//                        }
//
//
//                    }
//                });
//            }
//        });




//    // Read from the database
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        String value = dataSnapshot.getValue(String.class);
//                        Log.i("MAOMAOO",value);
////                    String name = String.valueOf(dataSnapshot.child("name").getValue());
////                    Log.i("MAOMAOO",name);
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
////                    Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });
//            }
//        });


    }
}
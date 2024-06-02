package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_db";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_FOLLOWED = "followed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_FOLLOWED + " BOOLEAN"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
        for (int i = 0;i<20;i++){
            Random random = new Random();
            int randomNum = random.nextInt(10000000);
            String name = "Name" + randomNum;
            int randomdesc = random.nextInt(10000000);
            String description = "Description " + randomdesc;
            int id = i;
            boolean followed = random.nextBoolean();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME,name);
            values.put(KEY_DESCRIPTION, description);
            values.put(KEY_FOLLOWED,followed);

            db.insert(TABLE_USERS, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public List<User> getUser(){
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
           do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int descriptionIndex = cursor.getColumnIndex(KEY_DESCRIPTION);
                int followedIndex = cursor.getColumnIndex(KEY_FOLLOWED);
                if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && followedIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String description = cursor.getString(descriptionIndex);
                    boolean followed = cursor.getInt(followedIndex) == 1;
                    User user = new User(name,description,id,followed);
                    userList.add(user);
                }
           }while (cursor.moveToNext());
        }
        return userList;
    }
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean followed = user.getFollowed();
        int id = user.getId();
        values.put(KEY_FOLLOWED, followed ? 1 : 0);
        db.update(TABLE_USERS,values,KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

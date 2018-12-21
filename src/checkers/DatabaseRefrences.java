package checkers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseRefrences {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference GAMES_REFERENCE = database.getReference("Game");
}

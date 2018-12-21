
package checkers;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Checkers {

    
    public static void main(String[] args)
    {
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("D:\ASU's Stuff\Semester 7 - Fall 2018\Agile Software Engineering\Project\Code\DesktopCheckers\src");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://checkers-9ebb8.firebaseio.com")
                    .build();
            FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.out.println("Firebase error");
            e.printStackTrace();
        }

        Board board = new Board();
        Game game = new Game();
        game.startGame();
        game.movePiece(4, 5, 10, 20, "red");
    }
    
}

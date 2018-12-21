package checkers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private String gamekey;
    private String myColor;

    public Game() {
        this.gamekey = DatabaseRefrences.GAMES_REFERENCE.push().getKey();
    }

    public Game(String gamekey) {
        this.gamekey = gamekey;
    }

    public String getGamekey() {
        return gamekey;
    }

    public void setGamekey(String gamekey) {
        this.gamekey = gamekey;
    }

    //white always starts the game
    public void startGame() {
        DatabaseReference gameRef = DatabaseRefrences.GAMES_REFERENCE.child(this.gamekey);
        gameRef.child("red").addChildEventListener(new OpponentMovesHandler());

        gameRef.child("white").addChildEventListener(new MyMovesHandler());
        this.myColor = "white";
    }

    public static Game joinGame(String gamekey) {
        DatabaseReference gameRef = DatabaseRefrences.GAMES_REFERENCE.child(gamekey);
        gameRef.child("red").addChildEventListener(new MyMovesHandler());

        gameRef.child("white").addChildEventListener(new OpponentMovesHandler());
        Game g = new Game(gamekey);
        g.myColor = "red";
        return g;
    }

    public void movePiece(int oldX, int oldY, int newX, int newY, String color) {
        DatabaseReference gameRef = DatabaseRefrences.GAMES_REFERENCE.child(this.gamekey);
        String oldPos = Integer.toString(oldX) + Integer.toString(oldY);
        Map<String, BoardPoint> data = new HashMap<String, BoardPoint>();
        data.put(oldPos, new BoardPoint(newX, newY));
        gameRef.child(color).setValueAsync(data);
    }

    public void capturePiece(int oldX, int oldY, String color){
        movePiece(oldX, oldY, -1, -1, color);
    }


    @Override
    public String toString() {
        return "Game{" +
                "gamekey='" + gamekey + '\'' +
                '}';
    }


    public static class OpponentMovesHandler implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            System.out.println(dataSnapshot);
            BoardPoint newPlace = dataSnapshot.getValue(BoardPoint.class);
            if(newPlace.getX() < 0 || newPlace.getY() < 0){
                return;
            }
            // write code for updating the pieces on the board
            // todo yala ya Ali
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    }

    public static class MyMovesHandler implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            System.out.println(dataSnapshot);

            BoardPoint newPlace = dataSnapshot.getValue(BoardPoint.class);
            if(newPlace.getX() < 0 || newPlace.getY() < 0){
                //my piece has been captured
                // remove it from the board
                // todo yala ya ali
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    }
}


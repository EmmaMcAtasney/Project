package com.passsafe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.DatabaseErrorHandler;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Button;


//this code has been written by Emma mc Atasney B00710246
public class Databasehelper extends SQLiteOpenHelper {
    //these are the variables needed for this class

    public static class FeedData implements BaseColumns {
        public static final String DatbaseName = "passwordGenerator.db";
        public static final String TableName = "passwords.db";
        public static final String COL2 = "ID";
        public static final String Col2 = "password";
        public static final String TAG = "Databasehelper";
        private SQLiteDatabase myDB;
     //  public static final String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
               // " CoL2 TEXT)";
    }
    public Databasehelper(Context context) {
        super(context, FeedData.DatbaseName, null, 1);
    }
//this overrides the onCreate method
      //  String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
      //  " CoL2 TEXT)";
    @Override
    public void onCreate(SQLiteDatabase db) {


       //String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
         //    " CoL2 TEXT)";
       //db.execSQL(createTable);
       makeTable(db);


    }


    public void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }
    }
//this creates the table inside the database.

  // String createTable = "CREATE TABLE " + FeedData.TableName + " ("+FeedData._ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+ FeedData.COL2 + "ID" + FeedData.Col2 + "password)";
   //db.execSQL(createTable);
   // Databasehelper dbHelper =  new Databasehelper(getContext());
    public void makeTable(SQLiteDatabase db){
       // String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
               // " CoL2 TEXT)";
       // SQLiteDatabase FeedData.DatbaseName = this.openOrCreateDatabase("passwordGenerator", SQLiteDatabase.OPEN_READWRITE, null);
       // try {
         //   SQLiteStatement stmt = dbObject.compileStatement("INSERT INTO TableName (password) values (?)");
           // stmt.bindString(1, text);
            //stmt.executeInsert();

       // } catch (Exception e) {
          //  e.printStackTrace();

      //  } finally {
         //   dbObject.close();
      //  }

        String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " CoL2 TEXT)";


        db.execSQL(createTable);
        //long result = db.insert(FeedData.TableName, null, contentValues);
}

    public void createDatabase(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + FeedData.TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " CoL2 TEXT)";
       // String createTable = "CREATE TABLE " + FeedData.TableName + " ("+FeedData.COL2+ "ID" + "INTEGER PRIMARY KEY AUTOINCREMENT," + FeedData.Col2 + "password)";
       // db.execSQL(createTable);
      //  String createTable = "CREATE TABLE " + FeedData.TableName + " ("+FeedData._ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+ FeedData.COL2 + "ID" + FeedData.Col2 + "password)";
      //  db.execSQL(createTable);
    }
//this will delete the old table if it exists before it creates the new table to store the password
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDatabase, int newDatabase) {
       // db.execSQL("DROP IF TABLE EXISTS " + TableName);
        String DeleteTable = "DROP TABLE IF EXISTS " + FeedData.TableName;
        db.execSQL(DeleteTable);
        onUpgrade(db, oldDatabase, newDatabase);
        onCreate(db);
    }

   // Databasehelper dbhelper = new Databasehelper(getContext());
//this will add the passwords into the database
    public boolean addData(String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       //contentValues.put(FeedData.COL2, "ID" );
        contentValues.put(FeedData.Col2, password);

        Log.d(FeedData.TAG, "addData: Adding " + password + " to " + FeedData.TableName);

        long result = db.insert(FeedData.TableName, null, contentValues);

       // if (result >= 1) return true; else return false;
       // if date as inserted incorrectly it will return -1
       if (result == -1) {
           return false;
       } else {
           return true;
       }
    }
    /**
     * Returns all the data from database
     * @return
     */


    public void readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedData.COL2,
                FeedData.Col2
        };
        String selection = FeedData.COL2 + " = ?";
        String[] selectionArgs = { "My ID" };
        String sortOrder =
                FeedData.Col2+ " DESC";

        Cursor cursor = db.query(
                FeedData.TableName,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


    }
   // SQLiteDatabase db = this.getReadableDatabase();
//this will sent the passwords into the List View so they can be viewed
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();

       // SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + FeedData.TableName, null);//error


        return data;
    }
    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + FeedData.COL2 + " FROM " + FeedData.TableName +
                " WHERE " + FeedData.Col2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */


    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + FeedData.TableName + " SET " + FeedData.Col2 +
                " = '" + newName + "' WHERE " + FeedData.COL2 + " = '" + id + "'" +
                " AND " + FeedData.Col2+ " = '" + oldName + "'";
        Log.d(FeedData.TAG, "updateName: query: " + query);
        Log.d(FeedData.TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }
    /**
     * Delete from database
     * @param id
     * @param name
     */

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FeedData.TableName + " WHERE "
                + FeedData.COL2 + " = '" + id + "'" +
                " AND " + FeedData.Col2 + " = '" + name + "'";
        Log.d(FeedData.TAG, "deleteName: query: " + query);
        Log.d(FeedData.TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}

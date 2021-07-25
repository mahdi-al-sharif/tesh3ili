package com.mahdi.tesh3ilisavingbox;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Member.class,Expense.class,Income.class},version = 1)
public abstract class CashDatabase extends RoomDatabase {

    private static CashDatabase instance;

    public abstract MemberDao memberDao();

    public abstract ExpenseDao expenseDao();

    public abstract IncomeDao incomeDao();

    public static synchronized CashDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    CashDatabase.class,"cashh_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private MemberDao memberDao;

        private PopulateDbAsyncTask(CashDatabase db){
            memberDao=db.memberDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            memberDao.insertMember(new Member("Mahdi Sharif"));
            memberDao.insertMember(new Member("kassem Kodeih"));
            memberDao.insertMember(new Member("Ali H. Yassin"));
            return null;
        }
    }


}

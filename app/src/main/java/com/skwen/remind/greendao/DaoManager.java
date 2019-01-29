package com.skwen.remind.greendao;

import com.blankj.utilcode.util.Utils;
import com.skwen.remind.bean.Record;
import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
public class DaoManager {

    private DaoSession daoSession;

    private static class Holder {
        private static DaoManager instance = new DaoManager();
    }

    private DaoManager() {
        String dbName = "sk_record.db";
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Utils.getApp().getApplicationContext(), dbName);
        Database db = helper.getWritableDb();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public static DaoManager getInstance() {
        return Holder.instance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }


    public List<Record> getAll() {
        return daoSession.loadAll(Record.class);
    }

    public List<Record> getRecord(String where, String args) {
        return daoSession.queryRaw(Record.class, where, args);
    }

    public Long insertRecord(Record record) {
        return daoSession.insert(record);
    }

    public void updateRecord(Record record) {
        daoSession.update(record);
    }

    public void deleteRecord(Record record) {
        daoSession.delete(record);
    }
}

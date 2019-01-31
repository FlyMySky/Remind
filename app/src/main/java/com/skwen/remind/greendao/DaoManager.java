package com.skwen.remind.greendao;

import com.blankj.utilcode.util.Utils;
import com.skwen.remind.bean.Record;
import com.skwen.remind.utils.MyContentProvider;
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

    public List<Record> getArgsRecord(int yearTime, int mouthTime, int dayTime) {

        return daoSession.queryBuilder(Record.class)
                .where(RecordDao.Properties.YearTime.eq(yearTime), RecordDao.Properties.MouthTime.eq(mouthTime), RecordDao.Properties.DayTime.eq(dayTime))
                .list();
    }

    public List<Record> getArgsRecord(boolean isOpen, boolean isOver) {

        return daoSession.queryBuilder(Record.class)
                .where(RecordDao.Properties.IsOpen.eq(isOpen), RecordDao.Properties.IsOver.eq(isOver))
                .list();
    }

    public Long insertRecord(Record record) {
        Long count = daoSession.insert(record);
        if (count > 0) {
            Utils.getApp().getContentResolver().notifyChange(MyContentProvider.URI, null);
        }
        return count;
    }

    public void updateRecord(Record record) {
        daoSession.update(record);
        Utils.getApp().getContentResolver().notifyChange(MyContentProvider.URI, null);
    }

    public void deleteRecord(Record record) {
        daoSession.delete(record);
        Utils.getApp().getContentResolver().notifyChange(MyContentProvider.URI, null);
    }
}

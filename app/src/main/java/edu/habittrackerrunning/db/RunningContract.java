package edu.habittrackerrunning.db;

import android.provider.BaseColumns;

/**
 * Created by edu on 7/19/2017.
 */

public class RunningContract {
    public static final String DB_NAME = "com.edu.habittrackerrunning.db";
    public static final int DB_VERSION = 1;

    public class RunningEntry implements BaseColumns {
        public static final String TABLE = "habits";

        public static final String TASK_RUNNING_LOCATION = "location";
        public static final String TASK_RUNNING_DISTANCE = "distance";
    }
}


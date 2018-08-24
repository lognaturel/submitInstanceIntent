package com.example.ln.submitinstanceintent;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final Uri INSTANCES_URI =
            Uri.parse("content://org.odk.collect.android.provider.odk.instances/instances");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noSettings(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        startActivity(intent);
    }

    public void badUrlOnly(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://totallyfake77712.appspot.com");

        startActivity(intent);
    }

    public void badUrlBlankUsernameAndPassword(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://totallyfake77712.appspot.com");
        intent.putExtra("USERNAME", "");
        intent.putExtra("PASSWORD", "");

        startActivity(intent);
    }

    public void urlOnly(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://nafundi-test.appspot.com");

        startActivity(intent);
    }

    public void urlBlankUsernameAndPassword(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://nafundi-test.appspot.com");
        intent.putExtra("USERNAME", "");
        intent.putExtra("PASSWORD", "");

        startActivity(intent);
    }

    public void urlUsernamePasswordBlank(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://nafundi-test.appspot.com");
        intent.putExtra("USERNAME", "test");
        intent.putExtra("PASSWORD", "");

        startActivity(intent);
    }

    public void urlUsernamePassword(View view) {
        int firstInstanceID = getFirstUnsentID();

        Intent intent = new Intent();
        intent.setClassName("org.odk.collect.android",
                "org.odk.collect.android.activities.InstanceUploaderActivity");
        intent.putExtra("instances", new long[]{firstInstanceID});
        intent.putExtra("URL", "https://nafundi-test.appspot.com");
        intent.putExtra("USERNAME", "test");
        intent.putExtra("PASSWORD", "test");

        startActivity(intent);
    }

    public int getFirstUnsentID() {
        Cursor cursor = getContentResolver().query(INSTANCES_URI,
                null, null, null, null);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));

                    if (!cursor.getString(cursor.getColumnIndex("status"))
                            .equals("submitted")) {
                        cursor.close();
                        return id;
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return -1;
    }
}

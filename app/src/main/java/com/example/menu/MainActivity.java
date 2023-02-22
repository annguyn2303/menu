package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textview_menu);
        textView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = MainActivity.this.startActionMode(mActionModeCallBack);
                view.setSelected(true);
                return true;
            }
        });
    }

    public ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_context_action, menu);
            actionMode.setTitle("Choose your option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.context_share:
                    Toast.makeText(MainActivity.this, "Text has been shared", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.context_delete:
                    Toast.makeText(MainActivity.this, "Text has been deleted", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void showSetting(){
        Intent intent = new Intent(getApplicationContext(), Setting.class);
        startActivity(intent);
    }

    public void showFavorite(){
        Intent intent = new Intent(getApplicationContext(), Favorite.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_settings:
                showSetting();
                return true;
            case R.id.option_favorites:
                showFavorite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
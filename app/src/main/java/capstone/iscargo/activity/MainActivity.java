package capstone.iscargo.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.ChattingFragment;
import capstone.iscargo.fragment.FriendsFragment;
import capstone.iscargo.fragment.SettingFragment;
import capstone.iscargo.fragment.TasksFragment;

public class MainActivity extends AppCompatActivity {
    private static class Fragments {
        public Fragment fragment;
        public int id;
        public String title;

        public Fragments(Fragment fragment, int id, String title) {
            this.fragment = fragment;
            this.id = id;
            this.title = title;
        }
    }

    private static Fragments fragments[] = {
            new Fragments(new FriendsFragment(), R.id.friends, "친구"),
            new Fragments(new ChattingFragment(), R.id.chatting, "채팅"),
            new Fragments(new TasksFragment(), R.id.tasks, "업무 목록"),
            new Fragments(new SettingFragment(), R.id.setting, "더보기")
    };

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialDisplay();
        setBottomMenu();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.friendsMenuChatting:
                Toast.makeText(getApplicationContext(), "채팅 연결", Toast.LENGTH_SHORT).show();
                break;
            case R.id.friendsMenuDelete:
                Toast.makeText(getApplicationContext(), "친구 삭제", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }

    private void setInitialDisplay() {
        title = (TextView)findViewById(R.id.title);
        title.setText(fragments[0].title);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragments[0].fragment).commit();
    }

    private void setBottomMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                for (int index = 0; index < fragments.length; index++)
                    if (item.getItemId() == fragments[index].id) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment, fragments[index].fragment)
                                .commit();
                        title.setText(fragments[index].title);
                        return true;
                    }

                return false;
            }
        });
    }
}
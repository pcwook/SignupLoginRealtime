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
import capstone.iscargo.fragment.FragmentChatting;
import capstone.iscargo.fragment.FragmentFriends;
import capstone.iscargo.fragment.FragmentSetting;
import capstone.iscargo.fragment.FragmentTasks;

public class ActivityMain extends AppCompatActivity {
    private static class SubActivity {
        public Fragment fragment;
        public int id;
        public String title;

        public SubActivity(Fragment fragment, int id, String title) {
            this.fragment = fragment;
            this.id = id;
            this.title = title;
        }
    }

    private static SubActivity subActivity[] = {
            new SubActivity(new FragmentFriends(), R.id.friends, "친구"),
            new SubActivity(new FragmentChatting(), R.id.chatting, "채팅"),
            new SubActivity(new FragmentTasks(), R.id.tasks, "업무 목록"),
            new SubActivity(new FragmentSetting(), R.id.setting, "더보기")
    };

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.title);
        title.setText(subActivity[0].title);

        setInitialDisplay();
        setBottomMenu();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test1:
                Toast.makeText(getApplicationContext(), "test1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test2:
                Toast.makeText(getApplicationContext(), "test2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test3:
                Toast.makeText(getApplicationContext(), "test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test4:
                Toast.makeText(getApplicationContext(), "test4", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }

    private void setInitialDisplay() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, subActivity[0].fragment).commit();
    }

    private void setBottomMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                for (int index = 0; index < subActivity.length; index++)
                    if (item.getItemId() == subActivity[index].id) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment, subActivity[index].fragment)
                                .commit();
                        title.setText(subActivity[index].title);
                        return true;
                    }

                return false;
            }
        });
    }
}
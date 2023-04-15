package capstone.iscargo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private static class SubActivity {
        private Fragment fragment;
        private int id;
        private String title;

        public SubActivity(Fragment fragment, int id, String title) {
            this.fragment = fragment;
            this.id = id;
            this.title = title;
        }

        public Fragment getFragment() {
            return fragment;
        }
        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }

    private static SubActivity subActivity[] = {
            new SubActivity(new Friends(), R.id.friends, "친구"),
            new SubActivity(new Chatting(), R.id.chatting, "채팅"),
            new SubActivity(new Tasks(), R.id.tasks, "업무 목록"),
            new SubActivity(new Setting(), R.id.setting, "더보기")
    };

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.title);
        title.setText(subActivity[0].getTitle());

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
        getSupportFragmentManager().beginTransaction().replace(R.id.container, subActivity[0].getFragment()).commit();
    }

    private void setBottomMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                for (int index = 0; index < subActivity.length; index++)
                    if (item.getItemId() == subActivity[index].getId()) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, subActivity[index].getFragment()).commit();
                        title.setText(subActivity[index].getTitle());
                        return true;
                    }

                return false;
            }
        });
    }
}
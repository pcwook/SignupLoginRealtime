package capstone.iscargo;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private static Fragment friends = new Friends();
    private static Fragment chatting = new Chatting();
    private static Fragment tasks = new Tasks();
    private static Fragment setting = new Setting();

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContext();
    }

    private void setContext() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Friends()).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.friends:
                        transaction.replace(R.id.container, friends);
                        break;
                    case R.id.chatting:
                        transaction.replace(R.id.container, chatting);
                        break;
                    case R.id.tasks:
                        transaction.replace(R.id.container, tasks);
                        break;
                    case R.id.setting:
                        transaction.replace(R.id.container, setting);
                        break;
                    default:
                        return false;
                }
                transaction.commit();
                return true;
            }
        });
    }
}
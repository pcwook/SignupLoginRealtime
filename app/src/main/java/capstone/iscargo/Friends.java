package capstone.iscargo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Friends extends androidx.fragment.app.Fragment {
    public Friends() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.friendsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new FriendsAdapter(view.getContext(), getNames(), getCompanies()));

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        for (int i = 1; i <= 4; i++)
            menu.add(0, i, 0, "Test");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    // 테스트용 데이터 생성
    private String[] getNames() {
        String[] names = new String[100];
        String[] testNames = {"이성민", "지상원", "강형준", "박찬욱", "이수환"};

        for (int r = 0; r < 100; r += 5)
            for (int i = 0, c = r; i < 5; i++, c++)
                names[c] = testNames[i];

        return names;
    }

    // 테스트용 데이터 생성
    private String[] getCompanies() {
        String[] companies = new String[100];

        for (int i = 0; i < 100; i++)
            companies[i] = "영진전문대학교";

        return companies;
    }
}
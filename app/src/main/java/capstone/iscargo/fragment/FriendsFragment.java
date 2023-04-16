package capstone.iscargo.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.R;
import capstone.iscargo.fragment.adapter.FriendsGroupAdapter;
import capstone.iscargo.fragment.adapter.FriendsListAdapter;
import capstone.iscargo.fragment.entity.FriendsEntity;
import capstone.iscargo.fragment.listener.FriendsGroupClickListener;

public class FriendsFragment extends Fragment {
    private FriendsListAdapter friendsListAdapter;
    private FriendsGroupAdapter friendsGroupAdapter;
    private FriendsGroupClickListener friendsGroupClickListener;
    private int selectedGroupPosition;

    public FriendsFragment() {
        selectedGroupPosition = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends_fragment, container, false);

        EditText friendsSearch = (EditText)view.findViewById(R.id.friendsSearch);
        friendsSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                friendsListAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        friendsListAdapter = new FriendsListAdapter(getEntities());
        RecyclerView recyclerViewList = view.findViewById(R.id.friendsList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewList.setAdapter(friendsListAdapter);

        RecyclerView recyclerViewGroup = view.findViewById(R.id.friendsGroup);
        friendsGroupClickListener = new FriendsGroupClickListener() {
            @Override
            public void onClick(int position, String groupTitle) {
                recyclerViewGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        friendsGroupAdapter.notifyDataSetChanged();
                    }
                });
                selectedGroupPosition = position;
                friendsListAdapter.selectGroup(groupTitle, friendsSearch.getText().toString());
            }
        };
        friendsGroupAdapter = new FriendsGroupAdapter(getGroupTitles(), friendsGroupClickListener, selectedGroupPosition);
        recyclerViewGroup.setLayoutManager((new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerViewGroup.setAdapter(friendsGroupAdapter);

        return view;
    }

    // 테스트용 목록 데이터 생성 함수
    private ArrayList<FriendsEntity> getEntities() {
        ArrayList<FriendsEntity> entities = new ArrayList<>();
        String members[] = {"지상원", "이성민", "강형준", "박찬욱", "이수환"};

        for (int row = 0, width = members.length; row < 100 / 5; row++)
            for (int index = 0, column = row * width; index < 5; index++, column++) {
                String company = column % 2 == 0 ? "영진전문대학교" : "YJ University";
                String phoneNumber = column % 2 == 0 ? "010-1234-5678" : "010-8765-4321";

                entities.add(new FriendsEntity(members[index], company, phoneNumber, "그룹" + (index % 5 + 1)));
            }

        return entities;
    }

    // 테스트용 그룹 데이터 생성 함수
    private ArrayList<String> getGroupTitles() {
        ArrayList<FriendsEntity> entities = getEntities();
        ArrayList<String> groupTitles = new ArrayList<>();

        groupTitles.add("전체");
        for (int index = 0; index < entities.size(); index++) {
            String groupTitle = entities.get(index).getGroupTitle();

            if (!groupTitles.contains(groupTitle))
                groupTitles.add(groupTitle);
        }

        return groupTitles;
    }
}
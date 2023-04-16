package capstone.iscargo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.fragment.adapter.FriendsGroupAdapter;
import capstone.iscargo.fragment.adapter.FriendsListAdapter;
import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.FriendsEntity;
import capstone.iscargo.fragment.listener.FriendsGroupClickListener;

public class FriendsFragment extends Fragment {
    private FriendsGroupClickListener listener;
    private FriendsGroupAdapter adapter;
    private int selectedGroupPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends_fragment, container, false);

        RecyclerView recyclerViewList = view.findViewById(R.id.friendsList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewList.setAdapter(new FriendsListAdapter(getEntities()));

        RecyclerView recyclerViewGroup = view.findViewById(R.id.friendsGroup);
        listener = new FriendsGroupClickListener() {
            @Override
            public void onClick(int position) {
                recyclerViewGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                selectedGroupPosition = position;
            }
        };
        adapter = new FriendsGroupAdapter(getGroupTitles(), listener, selectedGroupPosition);
        recyclerViewGroup.setLayoutManager((new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerViewGroup.setAdapter(adapter);

        return view;
    }

    // 테스트용 데이터 생성 함수
    private FriendsEntity[] getEntities() {
        final int SIZE = 100;

        FriendsEntity entities[] = new FriendsEntity[SIZE];
        String members[] = {"지상원", "이성민", "강형준", "박찬욱", "이수환"};

        for (int row = 0, width = members.length; row < SIZE / width; row++)
            for (int index = 0, column = row * width; index < 5; index++, column++) {
                String company = column % 2 == 0 ? "영진전문대학교" : "YJ University";
                String phoneNumber = column % 2 == 0 ? "010-1234-5678" : "010-8765-4321";

                entities[column] = new FriendsEntity(members[index], company, phoneNumber, "그룹" + (index % 5 + 1));
            }

        return entities;
    }

    private ArrayList<String> getGroupTitles() {
        FriendsEntity[] entities = getEntities();
        ArrayList<String> groupTitles = new ArrayList<>();

        groupTitles.add("전체");
        for (int index = 0; index < entities.length; index++) {
            String groupTitle = entities[index].getGroupTitle();

            if (!groupTitles.contains(groupTitle))
                groupTitles.add(groupTitle);
        }

        return groupTitles;
    }
}
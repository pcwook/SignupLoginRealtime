package capstone.iscargo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.R;
import capstone.iscargo.fragment.adapter.TasksGroupAdapter;
import capstone.iscargo.fragment.adapter.TasksListAdapter;
import capstone.iscargo.fragment.entity.TasksEntity;
import capstone.iscargo.fragment.listener.TasksGroupClickListener;

public class TasksFragment extends Fragment {
    private TasksGroupClickListener listener;
    private TasksGroupAdapter adapter;
    private int selectedGroupPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_fragment, container, false);

        RecyclerView recyclerViewList = view.findViewById(R.id.tasksList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewList.setAdapter(new TasksListAdapter(getEntities()));

        RecyclerView recyclerViewGroup = view.findViewById(R.id.tasksGroup);
        listener = new TasksGroupClickListener() {
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
        adapter = new TasksGroupAdapter(getGroupTitles(), listener, selectedGroupPosition);
        recyclerViewGroup.setLayoutManager((new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerViewGroup.setAdapter(adapter);

        return view;
    }

    // 테스트용 목록 데이터 입력 함수
    private TasksEntity[] getEntities() {
        final int SIZE = 10;

        TasksEntity[] entities = new TasksEntity[SIZE];
        String contents[][] = {
                {
                    "수입 40’ 여주 16:00 18톤 MSC 신항 의왕 정리 뒤작업뒤작업뒤작업",
                    "수입 60’ 중부 08:00 27톤 MSC 신항 의왕 정리 앞작업앞작업앞작업",
                    "수입 35’ 남부 12:30 12톤 MSC 신항 의왕 정리 옆작업옆작업옆작업"
                },
                {
                    "테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 1",
                    "테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 2"
                },
                {
                    "안녕하세요",
                    "지금 시각은 새벽 1시 9분입니다.",
                    "저는 언제 잘 수 있을까요?",
                    "감사합니다."
                }
        };
        String groups[] = {"대충", "그룹", "입", "니다"};

        for (int index = 0; index < SIZE; index++)
            entities[index] = new TasksEntity("(에어맨로직스) 김희곤 부장", "2023. 03. 25 16 : 45", contents[index % 3], groups[index % 4]);

        return entities;
    }

    private ArrayList<String> getGroupTitles() {
        TasksEntity entities[] = getEntities();
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
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import capstone.iscargo.R;
import capstone.iscargo.fragment.adapter.TasksGroupAdapter;
import capstone.iscargo.fragment.adapter.TasksListAdapter;
import capstone.iscargo.fragment.entity.TasksEntity;
import capstone.iscargo.fragment.listener.TasksGroupClickListener;

public class TasksFragment extends Fragment {
    private TasksListAdapter tasksListAdapter;
    private TasksGroupAdapter tasksGroupAdapter;
    private TasksGroupClickListener tasksGroupClickListener;
    private int selectedGroupPosition;

    public TasksFragment() {
        selectedGroupPosition = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_fragment, container, false);

        EditText tasksSearch = (EditText)view.findViewById(R.id.tasksSearch);
        tasksSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tasksListAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tasksListAdapter = new TasksListAdapter(getEntities());
        RecyclerView recyclerViewList = view.findViewById(R.id.tasksList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewList.setAdapter(tasksListAdapter);

        RecyclerView recyclerViewGroup = view.findViewById(R.id.tasksGroup);
        tasksGroupClickListener = new TasksGroupClickListener() {
            @Override
            public void onClick(int position, String groupTitle) {
                recyclerViewGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        tasksGroupAdapter.notifyDataSetChanged();
                    }
                });
                selectedGroupPosition = position;
                tasksListAdapter.selectGroup(groupTitle, tasksSearch.getText().toString());
            }
        };
        tasksGroupAdapter = new TasksGroupAdapter(getGroupTitles(), tasksGroupClickListener, selectedGroupPosition);
        recyclerViewGroup.setLayoutManager((new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerViewGroup.setAdapter(tasksGroupAdapter);

        return view;
    }

    // 테스트용 목록 데이터 입력 함수
    private ArrayList<TasksEntity> getEntities() {
        ArrayList<TasksEntity> entities = new ArrayList<>();
        String names[] = {
                "(에어맨로직스) 김희곤 부장",
                "(영진전문대학교) 이성민 팀장",
                "(YJ University) 지상원 개발자",
                "(영진) 강형준 개발자",
                "(YJ) 이수환 개발자",
                "(영진전문대) 박찬욱 개발자"
        };
        String contents[][] = {
                {
                    "수입 40’ 여주 16:00 18톤 MSC 신항 의왕 정리 뒤작업뒤작업뒤작업"
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
                },
                {
                    "지금 시각은 새벽 5시 27분입니다.",
                    "자고싶어요",
                    "감사합니다."
                }
        };
        Calendar date = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy. MM. dd HH : mm");
        date.set(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE));

        for (int index = 0; index < 15; index++)
            entities.add(new TasksEntity(
                    names[(int)(Math.random() * names.length)],
                    simpleDateFormat.format(date.getTime()),
                    contents[(int)(Math.random() * contents.length)],
                    "그룹" + (index % 3 + 1)));

        return entities;
    }

    private ArrayList<String> getGroupTitles() {
        ArrayList<TasksEntity> entities = getEntities();
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
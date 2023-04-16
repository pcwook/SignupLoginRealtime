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
import capstone.iscargo.fragment.adapter.ChattingListAdapter;
import capstone.iscargo.fragment.entity.ChattingEntity;

public class ChattingFragment extends Fragment {
    private ChattingListAdapter chattingListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chatting_fragment, container, false);

        setViewEntity(view);
        setRecyclerView(view);

        return view;
    }

    private void setViewEntity(View view) {
        ((EditText)view.findViewById(R.id.chattingSearch)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                chattingListAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setRecyclerView(View view) {
        chattingListAdapter = new ChattingListAdapter(getEntities());
        RecyclerView recyclerView = view.findViewById(R.id.chattingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(chattingListAdapter);
    }

    // 테스트용 데이터 입력 함수
    private ArrayList<ChattingEntity> getEntities() {
        ArrayList<ChattingEntity> entities = new ArrayList<>();
        String titles[] = {
                "캡스톤디자인(I) 채팅방",
                "자료구조 채팅방",
                "IoT시스템프로그래밍 채팅방",
                "모바일프로그래밍 채팅방",
                "웹프로그래밍응용 채팅방",
                "전공영어(I) 채팅방",
                "CCNA(III) 채팅방"
        };
        String contents[] = {
                "안녕하세요",
                "저는 캡스톤디자인 앱 개발을 담당한 지상원입니다.",
                "이 내용은 채팅방 내용 외관을 확인하기 위해 제작되었습니다.",
                "봐주셔서 감사합니다."
        };
        Calendar date = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        date.set(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH));

        for (int index = 0, titleIndex = 0, contentIndex = 0; index < 100; index++)
            entities.add(new ChattingEntity(
                    titles[titleIndex++ % titles.length],
                    contents[contentIndex++ % contents.length],
                    simpleDateFormat.format(date.getTime()),
                    index));

        return entities;
    }
}
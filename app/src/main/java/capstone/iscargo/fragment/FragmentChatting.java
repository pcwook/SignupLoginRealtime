package capstone.iscargo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import capstone.iscargo.fragment.adapter.AdapterChatting;
import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.EntityChatting;

public class FragmentChatting extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.chattingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AdapterChatting(view.getContext(), getEntities()));

        return view;
    }

    // 테스트용 데이터 입력 함수
    private EntityChatting[] getEntities() {
        final int SIZE = 100;

        EntityChatting entities[] = new EntityChatting[SIZE];
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
        date.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        for (int index = 0, titleIndex = 0, contentIndex = 0; index < SIZE; index++) {
            entities[index] = new EntityChatting(
                    titles[titleIndex++],
                    contents[contentIndex++],
                    simpleDateFormat.format(date.getTime()),
                    index);

            if (titleIndex == titles.length)
                titleIndex = 0;
            if (contentIndex == contents.length)
                contentIndex = 0;
        }

        return entities;
    }
}
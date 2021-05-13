package com.lalaalal.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import androidx.annotation.NonNull;

import java.util.*;

/*
 * 기능 별로 파일 분리에 대하여 말씀하셔서 이렇게 구현해 봤습니다!
 *
 * LinearLayout 을 상속받아 activity_main.xml 에서 다른 View 와 동일한 방법으로 사용할 수 있어요!
 * R.string.default_todo_message 는 res/values/strings.xml 에 위치해 있습니
 */
public class MyCalendar extends LinearLayout {
    private final HashMap<MyDate, String> todo = new HashMap<>();

    private Button addBtn;
    private Button removeBtn;
    private CalendarView calendarView;
    private EditText addEt;
    private TextView todoTv;

    // Activity 에서 View 로 사용하기 위해 필요한 생성자입니다!
    public MyCalendar(@NonNull Context context) {
        super(context);

        init();
    }

    // Activity 에서 View 로 사용하기 위해 필요한 생성자입니다!
    public MyCalendar(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        inflate(getContext(), R.layout.my_calendar, this);

        addBtn = findViewById(R.id.add_btn);
        removeBtn = findViewById(R.id.remove_btn);
        calendarView = findViewById(R.id.calender);
        addEt = findViewById(R.id.add_et);
        todoTv = findViewById(R.id.todo_tv);

        addBtn.setOnClickListener((view) -> {
            String message = addEt.getText().toString();
            MyDate date = new MyDate(calendarView.getDate());

            setTodo(date, message);
            todoTv.setText(message);
        });
        removeBtn.setOnClickListener((view) -> {
            MyDate date = new MyDate(calendarView.getDate());

            removeTodo(date);
            todoTv.setText(R.string.default_todo_message);
        });
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            MyDate date = new MyDate(year, month, dayOfMonth);
            String message = getTodo(date);

            todoTv.setText(message);
        });
    }

    public void setTodo(MyDate date, String message) {
        todo.put(date, message);
    }

    public void removeTodo(MyDate date) {
        todo.remove(date);
    }

    public String getTodo(MyDate date) {
        String message = todo.get(date);
        if (message == null)
            return getContext().getString(R.string.default_todo_message);
        return todo.get(date);
    }
}

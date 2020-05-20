package com.example.aid.ui.notifications;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.ui.notifications.NotificationsViewModel;


public class NotificationsFragment extends Fragment {
    private View view;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        ((TextView)getActivity().findViewById(R.id.arrow_Publish)).setTypeface(font);
        ((TextView)getActivity().findViewById(R.id.arrow_Photo)).setTypeface(font);
        ((TextView)getActivity().findViewById(R.id.arrow_Info)).setTypeface(font);
        ((TextView)getActivity().findViewById(R.id.arrow_Mark)).setTypeface(font);
        ((TextView)getActivity().findViewById(R.id.arrow_Set)).setTypeface(font);
        ((TextView)getActivity().findViewById(R.id.arrow_Logout)).setTypeface(font);
        waitInfo();
        waitLogout();
        waitMark();
        waitPhoto();
        waitPublish();
        waitSet();
    }
    private void waitLogout() {
        TextView word =  view.findViewById(R.id.user_logout);
        TextView icon =   view.findViewById(R.id.arrow_Logout);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
    }
    private void waitInfo() {
        TextView word =   view.findViewById(R.id.user_allinfo);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Info);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }

        });
    }

    private void waitPublish() {
        TextView word =   view.findViewById(R.id.user_publish);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Publish);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitMark() {
        TextView word =  view.findViewById(R.id.user_mark);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Mark);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitSet() {
        TextView word =  view.findViewById(R.id.user_set);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Set);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , MainActivity.class);
                startActivity(i);
            }

        });
    }
    private void waitPhoto() {
        TextView wordname =   view.findViewById(R.id.user_name);
        TextView wordphone =   view.findViewById(R.id.user_val);
        wordname.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                startActivity(i);
            }
        });
        wordphone.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), photoActivity.class);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Photo);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                startActivity(i);
            }

        });
        ImageView photo =   view.findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                startActivity(i);
            }

        });
    }

}
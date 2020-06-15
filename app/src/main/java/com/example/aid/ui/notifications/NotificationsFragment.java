package com.example.aid.ui.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.aid.MainActivity;
import com.example.aid.R;
import com.example.aid.data.DAL.UserDAL;
import com.example.aid.data.model.user;

import java.util.Map;


public class NotificationsFragment extends Fragment {
    private View view;
    private String id;
    private byte[] photo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NavController nav = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        Map<String, NavArgument> id = nav.getCurrentDestination().getArguments();
        NavArgument argument = id.get("id");
        String res = argument.getDefaultValue().toString();
        this.id = res;
        if(this.id.length()==11){
            view = inflater.inflate(R.layout.fragment_notifications, container, false);
            Log.e("id",this.id);
            TextView phone = (TextView) view.findViewById(R.id.user_phoneID);
            phone.setText(this.id);
            UserDAL userDAL = new UserDAL(getContext());
            TextView name =  view.findViewById(R.id.user_name);
            user user = userDAL.selectNameAndPhoto(this.id);
            name.setText(user.getName());
            ImageView photo = view.findViewById(R.id.photo);
            this.photo = user.getHead();
            if(this.photo==null){
                photo.setImageResource(R.mipmap.photo);
            }else{
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(this.photo, 0, this.photo.length, opts);
                photo.setImageBitmap(bitmap);
            }
        }
        else {
            view = inflater.inflate(R.layout.manager_mine, container, false);
            TextView phone = (TextView) view.findViewById(R.id.manager_phoneID);
            phone.setText(this.id);
        }
        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        if(this.id.length()==11){
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
        }else{
            ((TextView)getActivity().findViewById(R.id.arrow_themeM)).setTypeface(font);
            ((TextView)getActivity().findViewById(R.id.arrow_commentM)).setTypeface(font);
            ((TextView)getActivity().findViewById(R.id.arrow_taskM)).setTypeface(font);
            ((TextView)getActivity().findViewById(R.id.arrow_LogoutM)).setTypeface(font);
            waitTask();
            waitLogoutM();
            waitComments();
            waitTheme();
        }
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
                Intent i = new Intent(getActivity(), identityEditActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Info);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), identityEditActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
    }

    private void waitPublish() {
        TextView word =   view.findViewById(R.id.user_publish);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , publishActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Publish);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , publishActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
    }
    private void waitMark() {
        TextView word =  view.findViewById(R.id.user_mark);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , markActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Mark);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , markActivity.class);
                startActivity(i); i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
    }
    private void waitSet() {
        TextView word =  view.findViewById(R.id.user_set);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , receiveActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Set);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , receiveActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);startActivity(i);
            }

        });
    }
    private void waitPhoto() {
        TextView wordname =   view.findViewById(R.id.user_name);
        TextView wordphone =   view.findViewById(R.id.user_phoneID);
        wordname.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        wordphone.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), photoActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_Photo);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
        ImageView photo =   view.findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , photoActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
    }
    private void waitLogoutM() {
        TextView word =  view.findViewById(R.id.m_logout);
        TextView icon =   view.findViewById(R.id.arrow_LogoutM);
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
    private void waitTask() {
        TextView word =   view.findViewById(R.id.m_task_text);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), managerMineActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                i.putExtra("type","task");
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_taskM);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), managerMineActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                i.putExtra("type","task");
                startActivity(i);
            }

        });
    }

    private void waitComments() {
        TextView word =   view.findViewById(R.id.m_comment);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), managerMineActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                i.putExtra("type","comment");
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_commentM);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), managerMineActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                i.putExtra("type","comment");
                startActivity(i);
            }

        });
    }
    private void waitTheme() {
        TextView word =  view.findViewById(R.id.m_theme_text);
        word.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , themeActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }
        });
        TextView icon =   view.findViewById(R.id.arrow_themeM);
        icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity() , themeActivity.class);
                i.putExtra("id",NotificationsFragment.this.id);
                startActivity(i);
            }

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if(this.id.length()==11){
            UserDAL userDAL = new UserDAL(getActivity());
            user user = userDAL.selectNameAndPhoto(this.id);
            ImageView photo = view.findViewById(R.id.photo);
            this.photo = user.getHead();
            if(this.photo==null){
                photo.setImageResource(R.mipmap.photo);
            }else{
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(this.photo, 0, this.photo.length, opts);
                photo.setImageBitmap(bitmap);
            }
        }
    }
}
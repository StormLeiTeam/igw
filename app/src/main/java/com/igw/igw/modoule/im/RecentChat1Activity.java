package com.igw.igw.modoule.im;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.igw.igw.R;
import com.igw.igw.activity.BaseActivity;
import io.rong.imkit.fragment.ConversationListFragment;

public class RecentChat1Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_chat1);

        ConversationListFragment conversationListFragment = new ConversationListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationListFragment);
        transaction.commit();
    }


}

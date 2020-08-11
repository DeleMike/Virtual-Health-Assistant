package com.medicminds.thebot;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String BOT_NAME = "The Bot";
    private ChatView chatView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        chatView = findViewById(R.id.chat_view);


        chatView.addMessage(new ChatMessage("Hello I'm your Health Assistant",
                new Date().getTime(), ChatMessage.Type.RECEIVED, BOT_NAME));

        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(final ChatMessage chatMessage) {
                if(!(chatMessage.getMessage().trim().length() > 0)){
                    Toast.makeText(MainActivity.this, "Only whitespace", Toast.LENGTH_SHORT).show();
                    return false;
                }
                chatMessage.setSender("Delé");

                chatView.addMessage(new ChatMessage(chatMessage.getMessage(),
                        new Date().getTime(), ChatMessage.Type.SENT, chatMessage.getSender()));

                chatView.getInputEditText().setText("");

                Log.i(TAG, "sendMessage: INPUT = "+chatMessage.getMessage());
                Log.i(TAG, "sendMessage: FORMATTED TIME = "+chatMessage.getFormattedTime());
                Log.i(TAG, "sendMessage: SENDER = "+chatMessage.getSender());

                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reply(chatMessage.getMessage());
                    }
                }, sendDelay);



              return Boolean.parseBoolean(null);
            }

        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {
                toolbar.setSubtitle("typing...");
            }

            @Override
            public void userStoppedTyping() {
                toolbar.setSubtitle("online");
            }
        });

    }

    //the chat bot replies here
    private void reply(String typedMessage) {
        if(typedMessage == null){
            typedMessage = "";
        }

        String [] questions = {"Hello", "Hi", "How are you", "what\'s happening today"};

        String [] answers = {"Hi there", "Hello", "I\'m fine, any problem ?" , "Nothing much, just waiting for your questions"};

        if(typedMessage.equalsIgnoreCase(questions[0])){
            chatView.addMessage(new ChatMessage(answers[0], new Date().getTime(),
                    ChatMessage.Type.RECEIVED, BOT_NAME));
        }else if(typedMessage.equalsIgnoreCase(questions[1])){
            chatView.addMessage(new ChatMessage(answers[1], new Date().getTime(),
                    ChatMessage.Type.RECEIVED, BOT_NAME));
        }else if(typedMessage.equalsIgnoreCase(questions[2]) || typedMessage.contains(questions[2])){
            chatView.addMessage(new ChatMessage(answers[2], new Date().getTime(),
                    ChatMessage.Type.RECEIVED, BOT_NAME));
        }else if(typedMessage.equalsIgnoreCase(questions[3]) || typedMessage.contains("happening")){
            chatView.addMessage(new ChatMessage(answers[3], new Date().getTime(),
                    ChatMessage.Type.RECEIVED, BOT_NAME));
        }else{
            chatView.addMessage(new ChatMessage("I\'m a work in a progress\nI will understand you later.", new Date().getTime(),
                    ChatMessage.Type.RECEIVED, BOT_NAME));
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

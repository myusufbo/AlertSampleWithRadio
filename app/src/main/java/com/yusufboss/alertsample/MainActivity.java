package com.yusufboss.alertsample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    Button btnSimple,btnMultiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimple=(Button)findViewById(R.id.button);
        btnMultiple=(Button)findViewById(R.id.button2);

        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceListAlertDialog();
            }
        });

    btnMultiple.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showMultiChoiceListAlertDialog();
        }
    });

    }
    private String mCheckedItem;
    private void showSingleChoiceListAlertDialog() {
        final String[] list = new String[]{"Female", "Male"};
        int checkedItemIndex = 0;
        mCheckedItem = list[checkedItemIndex];

        createAlertDialogBuilder()
                .setTitle("Edit your gender")
                .setSingleChoiceItems(list,
                        checkedItemIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCheckedItem = list[which];
                                showToast(mCheckedItem);
                            }
                        })
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton(
                        "Save",
                        new ButtonClickedListener(mCheckedItem + " has been chosen.")
                )
                .show();
    }

    private AlertDialog.Builder createAlertDialogBuilder() {
//        if (mTheme == NATIVE_THEME) {
            return new AlertDialog.Builder(this);
//        }

//        return new AlertDialogPro.Builder(this, mTheme);
    }
    private List<String> mCheckedItems = new ArrayList<String>();
    private void showMultiChoiceListAlertDialog() {
        final String[] list= new String[]{"A","B","C","d"};

        createAlertDialogBuilder()
                .setTitle("Contacts")
                .setMultiChoiceItems(list,null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCheckedItems.add(list[which]);
                                }
                                else if(mCheckedItems.contains(which)){
                                    mCheckedItems.remove(Integer.valueOf(which));
                                }

                                 /*else {
                                    mCheckedItems.remove(list[which]);
                                }*/
                                showToast(
                                        list[which] + " is "
                                                + (isChecked ? "checked" : "unchecked" + ".")
                                );
                            }
                        })
                .setNeutralButton("More info", /*new ButtonClickedListener("More info")*/null)
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton(
                        "Choose",
                        /*new ButtonClickedListener("Choose " + mCheckedItems.toString()*/null)

                .show();
                /*.setTitle("Contacts")
                .setMessage("Hello World")
                .setPositiveButton("Ok",null)
                .setNegativeButton("Cancel",null)
                .show();
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
        }
    }
    private Toast mToast = null;

    private void showToast(CharSequence toastText) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }

}

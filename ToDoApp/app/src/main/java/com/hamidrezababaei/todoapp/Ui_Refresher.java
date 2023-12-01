package com.hamidrezababaei.todoapp;

import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hamidrezababaei.todoapp.db.CheckList;
import com.hamidrezababaei.todoapp.db.CheckListDAO;
import com.hamidrezababaei.todoapp.db.Items;
import com.hamidrezababaei.todoapp.db.ItemsDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

public class Ui_Refresher extends Thread {
    Context context;
    LayoutInflater inflater;
    LinearLayout layout;
    CheckListDAO checkListDAO_D_E;
    TodoListDatabase db = TodoListDatabase.getTodoListDatabase(context);
    ItemsDAO itemsDAO_1 = db.getItemsDAO();

    Ui_Refresher(Context context, LayoutInflater inflater, LinearLayout layout) {
        this.context = context;
        this.inflater = inflater;
        this.layout = layout;

    }

    @Override
    public void run() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    createlist();
                    sleep(200);
                } catch (Exception e) {

                }
            }
        });
    }

    void createlist() {
        TodoListDatabase db = TodoListDatabase.getTodoListDatabase(context);
        layout.removeAllViews();
        CheckListDAO checkListDAO = db.getCheckListDAO();
        checkListDAO_D_E = checkListDAO;
        int i = 0;
        for (CheckList checklist : checkListDAO.getCheckLists()) {
            //Log.e("checkList ID : ", ""+checklist);
            View fragment = inflater.inflate(R.layout.checklist_fragment, layout, false);

            layout.addView(fragment);
            ViewGroup view = (ViewGroup) layout.getChildAt(i++); // i

            TextView text_percent = (TextView) view.getChildAt(0);
            if(itemsDAO_1.isCheckedPALL(checklist.getCheklistId()) > 0){
                int numberOfTrue = itemsDAO_1.isCheckedPTRUE(checklist.getCheklistId() , true);
                int numberOfAll = itemsDAO_1.isCheckedPALL(checklist.getCheklistId());
                float percent = (float)(numberOfTrue*100/numberOfAll);
                Log.e("percent" , percent + "" );
                text_percent.setText(""+percent+"%");
            }

            TextView textView = (TextView) view.getChildAt(1);
            textView.setText(checkListDAO.getCheckLists_name_byId( checklist.getCheklistId()).toString());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , CheckList_Items.class);
                    intent.putExtra("CHECKLISTID" , String.valueOf( checklist.getCheklistId()));
                    Log.e("id of check list when go checklist item activity", "onClick: "+ checklist.getCheklistId() );
                    context.startActivity(intent);
                }
            });

            ImageButton imageButton = (ImageButton) view.getChildAt(2);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "index" +  checklist.getCheklistId(), Toast.LENGTH_SHORT).show();
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.custom_dialog_update);
                    dialog.show();

                    EditText name = dialog.findViewById(R.id.update_editText_customDialog_name);
                    EditText description = dialog.findViewById(R.id.update_editText_customDialog_description);
                    Button save = dialog.findViewById(R.id.update_send_btn_customDialog);

                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!name.getText().toString().equals("") && !description.getText().toString().equals("") ) {
                                checkListDAO_D_E.updateChecklist(name.getText().toString(), description.getText().toString(), checklist.getCheklistId());
                                new Ui_Refresher(context, inflater, layout).start();
                                dialog.cancel();
                            }else{
                                Toast.makeText(context,"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            ImageButton imageButton1 = (ImageButton) view.getChildAt(3);
            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("id of check list when delete check list", "onClick: "+ chItemIndex );
                    checkListDAO_D_E.deleteCheckList(new CheckList( checklist.getCheklistId()));
                    new Ui_Refresher(context, inflater, layout).start();
                }
            });

        }
    }


}


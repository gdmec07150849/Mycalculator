package cn.edu.gdmec.a07150849.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button calculate;
    private EditText weightEdit;
    private RadioButton maleRb;
    private RadioButton femaleRb;
    private TextView resTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = (Button) findViewById(R.id.calculate);
        weightEdit = (EditText) findViewById(R.id.weight);
        maleRb = (RadioButton) findViewById(R.id.male);
        femaleRb = (RadioButton) findViewById(R.id.female);
        resTv = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        regisEvent();
    }

    private void regisEvent(){

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!weightEdit.getText().toString().trim().equals("")){
                    if(maleRb.isChecked()||femaleRb.isChecked()){
                        Double weight = Double.parseDouble(weightEdit.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------------结果-----------\n");
                        if(maleRb.isChecked()){
                            sb.append("男生标准身高：");
                            double res = evaluateHeight(weight,"男");
                            sb.append((int)res+"厘米");

                        }else{
                            sb.append("女生的标准身高：");
                            double res =  evaluateHeight(weight,"女");
                            sb.append((int)res+"厘米");
                        }
                        resTv.setText(sb.toString());

                    }else{
                        showMessage("请选择性别！");

                    }
                }else{
                    showMessage("请输入体重！");
                }

            }
        });





    }
    private double evaluateHeight(double weight,String sex){

        double height;
        if(sex=="男"){
            height = 170-(62-weight)/0.6;
        }else{
            height = 158-(52-weight)/0.5;
        }
    return height;
    }
    private void showMessage(String message){
        AlertDialog adl = new AlertDialog.Builder(this).create();
        adl.setTitle("系统信息");
        adl.setMessage(message);
        adl.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        adl.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add(0,1,0,"退出");



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case 1:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

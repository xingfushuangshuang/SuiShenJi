package com.zhidisoft.sqldemo2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zhidisoft.sqldemo2.R;
import com.zhidisoft.sqldemo2.adapter.SqlAdapter;
import com.zhidisoft.sqldemo2.bean.User;
import com.zhidisoft.sqldemo2.dbutil.DBAction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_id, et_name, et_pwd;
    private Button btn_insert, btn_delete, btn_update, btn_selectName,btn_selectId;
    private List<User> list;//数据源
    private ListView lv;
    private DBAction dba;
    private SqlAdapter sqlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBAction cdb=new DBAction(MainActivity.this);
//        cdb.Insert(new Object[]{"张三","11111"});
        initView();
    }

    /**
     * 初始化界面
     */
    void initView() {
        et_id = (EditText) findViewById(R.id.et_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        lv = (ListView) findViewById(R.id.lv);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_selectName = (Button) findViewById(R.id.btn_selectName);
        btn_selectId= (Button) findViewById(R.id.btn_selectId);
        //设置监听
        btn_insert.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_selectName.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_selectId.setOnClickListener(this);
        //初始化与SqlAdapter相关的变量
        dba = new DBAction(this);
        list = new ArrayList<>();
        sqlAdapter = new SqlAdapter(list, this);
        lv.setAdapter(sqlAdapter);//lv设置加载模板
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                insert();
                queryAll();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_selectName:
                selectByName();
                break;
            case R.id.btn_delete:
                deleteById();
                break;
            case R.id.btn_selectId:
                selectById();
                break;
        }
    }

    /**
     * 插入
     */
    void insert() {
        String username = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        User user = new User(username, pwd);
        DBAction cdb = new DBAction(this);
        cdb.Insert(user);
        Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询所有数据
     */
    void queryAll() {
        //创建数据操作实例 -->作为全局变量 移到onCreate中实例化
//        DBAction dba=new DBAction(this);
        //初始化list
        list = dba.queryAll();
        //设置sqlAdapter对象（onCreate中已经实例化，）
        sqlAdapter.setList(list);
        //更新
        sqlAdapter.notifyDataSetChanged();
    }

    /**
     * 修改对应ID数据
     */
    void update() {
        //获取id 以及username,pwd
        String id = et_id.getText().toString();
        String username = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        try {
            //获取对应User对象
            User user = new User(Integer.parseInt(id), username, pwd);
            dba.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //数据源发生改变后 调用queryAll() 更新
        queryAll();
    }

    /**
     * 查询对应名称的数据
     */
    void selectByName(){
        String username = et_name.getText().toString();
        //更改适配器数据
        sqlAdapter.setList(dba.select(username));
        //更新
        sqlAdapter.notifyDataSetChanged();
    }

    /**
     * 删除对应ID的数据
     */
    void deleteById(){
        String id=et_id.getText().toString();
        dba.deleteById(id);
        queryAll();
    }

    /**
     * 查询对应id的数据
     */
    void selectById(){
        String id=et_id.getText().toString();
        ArrayList<User> list_id=new ArrayList<>();//新建list 每次查询初始化 只显示一条数据
        list_id.add(dba.selectById(id));
        sqlAdapter.setList(list_id);
        sqlAdapter.notifyDataSetChanged();
    }
}

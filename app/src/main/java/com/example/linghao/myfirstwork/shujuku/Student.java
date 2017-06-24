package com.example.linghao.myfirstwork.shujuku;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

public class Student extends BmobObject  {
  private String name;
    private String score;
    private String zhanghao;
    private String psw;
    private String aclass;
    private String tel;

    public String getName(){
        return name;
    }
    public  void setName(String name){
        this.name=name;
    }
    public String getScore(){
        return score;
    }
    public  void setScore(String score){
        this.score=score;
    }
    public String getZhanghao(){
        return  zhanghao;
    }
    public  void setZhanghao(String zhanghao){
        this.zhanghao=zhanghao;
    }
    public String getPsw(){
        return  psw;
    }
    public  void setPsw(String psw){
        this.psw=psw;
    }
    public String getAclass(){
        return  aclass;
    }
    public  void setAclass(String aclass){
        this.aclass=aclass;
    }
    public String getTel(){
        return  tel;
    }
    public  void setTel(String tel){
        this.tel=tel;
    }


}

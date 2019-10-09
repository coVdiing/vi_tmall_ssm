package com.vi.tmall.pojo;

public class User {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //在显示评价者的时候进行匿名显示
    public  String getAnonymouseName(){
        if(null==name)
            return null;
        if(name.length() <= 1){
            return "*";
        }
        if(name.length()==2) {
            return name.substring(0,1)+"*";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name.charAt(0)+"");
        for(int i = 0;i < name.length() - 2; i++)
            sb.append("*");
        sb.append(name.charAt(name.length()-1));
        return sb.toString();
    }
}
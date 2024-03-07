package com.hunghhph44272.myapp123;

import java.util.HashMap;

public class ToDo {
    private String id,content,title;

    public ToDo() {
    }

    public ToDo(String id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public HashMap<String,Object> convertHashMap() //ham xu ly du lieu trong firebase
    {
        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("title",title);
        work.put("content",content);
        return work;
    }
}

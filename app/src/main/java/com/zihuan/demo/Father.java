package com.zihuan.demo;

import java.util.ArrayList;
import java.util.List;

class Father {
    protected List<String> mList = new ArrayList();
    protected String mStr = "1";

    public Father() {
        mList.add("1");
        mList.add("2");
    }
}

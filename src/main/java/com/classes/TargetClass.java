package com.classes;

import com.interfaces.TargetInterface;

/**
 * Created by 淋漓尽致 on 2016/12/7.
 */
public class TargetClass implements TargetInterface {
    public int targetMethodA(int num) {
        System.out.println("------A------");
        System.out.println("------num A--"+num+"-----");
        System.out.println("------end A------");
        return num;
    }

    public int targetMethodB(int num) {
        System.out.println("------B------");
        System.out.println("------num B--"+num+"-----");
        System.out.println("------end B------");
        return num;
    }

    public int methodC(int num) {
        System.out.println("------c--------");
        System.out.println("--------num----"+num);
        return 0;
    }
}

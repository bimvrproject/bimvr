package com.jhbim.bimvr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aa {
    public static void main(String[] args) {
        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List list2 =new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        list1.removeAll(list2);

        Iterator<String> it=list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

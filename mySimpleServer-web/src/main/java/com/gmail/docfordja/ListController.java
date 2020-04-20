package com.gmail.docfordja;

/**
 * Created by anton on 13.04.2020.
 */


public class ListController  implements Runnable{

    @Override
    public void run() {
        GetListServlet gls = new GetListServlet();
        gls.clearMap();
        System.out.println("ok");




    }
}

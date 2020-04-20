package com.gmail.docfordja;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, int fromIndex) {
        GetListServlet gls = new GetListServlet();
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            ////    if (sourceList.get(i).getForr() == null) {
            if (sourceList.get(i).getTo() == null) {
                list.add(sourceList.get(i));
            } else if (sourceList.get(i).getTo().equals(gls.getLogg()) || sourceList.get(i).getFrom().equals(gls.getLogg()) ||
                    ("registration" + sourceList.get(i).getFrom()).equals(gls.getLogg())) {
                list.add(sourceList.get(i));
            } else {
                Message ms = new Message("deletedeletedeletedeletedelete", "");
                list.add(ms);
            }


        }for(String room : gls.getKeys()){
            if(gls.getSession().getAttribute("room").equals(room)){
              Message[] mess =  gls.getRoomMessage(room,(Integer) gls.getSession().getAttribute("from_room"));
              for (Message mes : mess){
                  list.add(mes);
              }
            }
        }



    }
}

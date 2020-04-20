package com.gmail.docfordja;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GetListServlet extends HttpServlet {
	private static String logg;
	private MessageList msgList = MessageList.getInstance();
	private static HashMap<String, String> publicList = new HashMap<>();
	private static String stat;
	private static HashMap<String, List<Message>> allRooms = new HashMap<>();
	private static HttpSession session;

    public HttpSession getSession(){return session;}

	public void addRoomMessage(String name, Message mes) {
		if (allRooms.get(name) == null) {
			List<Message> m = new LinkedList<>();
			m.add(mes);
			allRooms.put(name, m);
		}else {
			allRooms.get(name).add(mes);
		}
	}
	public Message[] getRoomMessage(String room, int value){
    	Message[] messages = new Message[allRooms.get(room).size() - value + 1];
    	for(int i = value ; i < allRooms.get(room).size(); i++){
				messages[i] = allRooms.get(room).get(value);}
		return messages;
	}

	public String[] getKeys(){
    	String[] keys = new String[allRooms.size()];
    	int num = 0;
		for (String key : allRooms.keySet()){
			keys[num] = key;
			num++;
		}

    	return keys;
	}

    public String getStat(){
    	return stat;
	}

	public void clearMap() {
		publicList.clear();
	}

	public static String getLogg() {
		return logg;
	}

	private void setLogg(String str) {
		this.logg = str;
	}

	public String fromList() {
		String result = "";
		for (String key : publicList.keySet()) {
			result += "> " + publicList.get(key) + "\n";
		}

		return result;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		session = req.getSession();
		String fromStr = req.getParameter("from");
		String login = req.getParameter("login");
		String status = req.getParameter("status");
		String room = req.getParameter("room");
 		int fromRoom = Integer.parseInt(req.getParameter("fromRoom"));
		stat = status;
		session.setAttribute(room,room);
		session.setAttribute("from_room",fromRoom);
		if (login.indexOf("registration") == 0) {
			login = login.substring("registration".length());
		}
		if (status.equals("public")) {
			publicList.put(login, login);
		}
		if (status.equals("private")) {
			publicList.remove(login);
		}
		TimeClass timeClass = new TimeClass();
		Thread thread = new Thread(timeClass);
		thread.start();
		setLogg(login);
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
			if (from < 0) from = 0;
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		resp.setContentType("application/json");
        /// send will be here<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String json = msgList.toJSON(from);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
			byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);

			//PrintWriter pw = resp.getWriter();
			//pw.print(json);
		}
	}
}

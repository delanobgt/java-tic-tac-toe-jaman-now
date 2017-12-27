package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
    private Socket conn;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    
    public boolean connect(String url, int port) {
        boolean connected;
        try {
            conn = new Socket(url, port);
            connected = true;
            initStream();
        } catch (Exception ex) {
            connected = false;
        }
        return connected;
    }
    
    private void initStream() {
        try {
            os = new ObjectOutputStream(conn.getOutputStream());
            is = new ObjectInputStream(conn.getInputStream());
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }    
    
    public Object readObject() {
        try {
           return is.readObject();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return null;
    }
    
    public void sendObject(Object x) {
        try {
           os.writeObject(x);
           os.flush();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
    
    public void close() {
        try {
            os.close();
            is.close();
            conn.close();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
}

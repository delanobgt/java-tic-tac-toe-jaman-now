package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket server;
    private Socket conn;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private int port;
    
    public Server(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
    
    public boolean waitForConnection() {
        boolean connected;
        try {
            conn = server.accept();
            connected = true;
            initStream();
        } catch (Exception ex) {
//            ex.printStackTrace();
            connected = false;
        }
        return connected;
    }
    
    private void initStream() {
        try {
            os = new ObjectOutputStream(conn.getOutputStream());
            is = new ObjectInputStream(conn.getInputStream());
        } catch (Exception ex) {
            System.out.println(ex);
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
            server.close();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
    
    public int getPort() {
        return this.port;
    }
    
    public InetAddress getInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}

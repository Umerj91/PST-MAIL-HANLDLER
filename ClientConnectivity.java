package com.example.pstmailhandler;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

//import android.support.v7.app.AppCompatActivity;
//import com.google.gson.Gson;

class Command implements Serializable
{
    public String cmdtext;// { get; set; }
    public String  email;// { get; set; }
}
@SuppressLint("SetTextI18n")
public class ClientConnectivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    Button btnConnect;
    String SERVER_IP;
    int SERVER_PORT;
    public Socket socket;
    String inMsg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        etIP = findViewById( R.id.etIP );
        etPort = findViewById( R.id.etPort );
        tvMessages = findViewById( R.id.tvMessages );
        etMessage = findViewById( R.id.etMessage );
        btnSend = findViewById( R.id.btnSend );

         btnConnect = findViewById( R.id.btnConnect );
        btnConnect.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread( new ClientThread() );
                t.start();

//                tvMessages.setText("");
//                SERVER_IP = etIP.getText().toString().trim();
//                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
//                Thread1 = new Thread(new Thread1());
//                Thread1.start();
            }
        } );
        btnSend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread( new Thread3( message ) ).start();
                }
            }
        } );
    }

    private PrintWriter output;
    private BufferedReader input;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private DataOutputStream dos;
    private DataInputStream dis;

    class Thread1 implements Runnable {
        public void run() {

            try {
                socket = new Socket( SERVER_IP, SERVER_PORT );
                dis = new DataInputStream( socket.getInputStream() );
                dos = new DataOutputStream( socket.getOutputStream() );
                output = new PrintWriter( socket.getOutputStream() );

                input = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

                ois = new ObjectInputStream( socket.getInputStream() );
                oos = new ObjectOutputStream( socket.getOutputStream() );
                String r = dis.readUTF();
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText( "Connected\n" );
                        tvMessages.append( "Server :" + r );
                    }
                } );

                new Thread( new Thread2() ).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    //final String message = input.readLine();
                    String message = (String) dis.readUTF();

                    if (message != null) {
                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append( "server: " + message + "\n" );
                            }
                        } );
                    } else {
                        Thread1 = new Thread( new Thread1() );
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run() {
//            try {
//                oos.writeObject( message );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //output.write(message);
            //output.flush();
            try {
                dos.writeUTF( message );
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread( new Runnable() {
                               @Override
                               public void run() {
                                   tvMessages.append( "client: " + message + "\n" );
                                   etMessage.setText( "" );
                               }
                           }
            );
        }
    }


    public class ClientThread implements Runnable {
        public void run() {
            try {
                Log.d( "method", "C: Method..." );
                InetAddress serverAddr = InetAddress.getByName( "192.168.181.117" );
                Log.d( "ClientActivity", "C: Connecting..." );
                Socket socket = new Socket( "192.168.181.117", 46460);
                byte[] input = new byte[1024];

                BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                BufferedWriter out = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                DataOutputStream dos =new DataOutputStream(socket.getOutputStream());
//                        out.write( "umerj5750@gmail.com" );
                //                   out.write( "READ" );
                //out.flush();


//                String email="umerj5750@gmail.com";
//                String msg="READ";
                Command cmd=new Command();
                cmd.cmdtext="READ";
                cmd.email="umerj5750@gmail.com";
//                ObjectOutputStream oos=new ObjectOutputStream( socket.getOutputStream() );
//                oos.writeObject( cmd );
                dos.writeUTF(cmd.email);
                dos.flush();
                dos.writeUTF( cmd.cmdtext );
                // dos.writeUTF( email );
                // String inMsg = "";
                dos.flush();
                while (true) {
                    String results = "";
                    try {


                        boolean b = false;
//                        String str1 = new String(input);
//                        System.out.println("str1 >> "+str1);

                        socket.getInputStream().read(input) ;



                        String jstr=      new String(input);
//                       Log.d("get",jstr);
//                        JSONObject jsonObject=new JSONObject(jstr);
//                        Log.d( "obj","create json" );
//                        Gson gson=new Gson();
//                        MailHandler mh=  gson.fromJson( jstr,MailHandler.class );
//                      Log.d( "emailobj","Got email.." );

                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {

                                tvMessages.append( "Server :" + new String(input) );
                                Log.d( "mails",new String(input) );
                            }
                        } );

                        // socket.close();
                        Log.d( "ClientActivity", "C: Closed." );
                    } catch (Exception e) {
                        Log.e( "ClientActivity", "S: Error", e );
                    }
                }
            } catch (Exception e) {
                Log.e( "ClientActivity", "C: Error", e );
            }
        }

    }
}
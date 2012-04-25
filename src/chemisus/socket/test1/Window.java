/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chemisus.socket.test1;

import chemisus.socket.Packet;
import chemisus.socket.Server;
import chemisus.socket.Session;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chemisus
 */
public class Window extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Packet.Register(PacketTest.class);
        
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Window().setVisible(true);
            }
        });
    }
    
    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnServerOpen = new javax.swing.JButton();
        btnServerClose = new javax.swing.JButton();
        btnClientOpen = new javax.swing.JButton();
        btnClientClose = new javax.swing.JButton();
        btnClientSend = new javax.swing.JButton();
        btnServerSend = new javax.swing.JButton();
        btnTestStart = new javax.swing.JButton();
        btnTestStop = new javax.swing.JButton();
        lblSession = new javax.swing.JLabel();
        lblClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnServerOpen.setText("Listen");
        btnServerOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerOpenActionPerformed(evt);
            }
        });

        btnServerClose.setText("Close");
        btnServerClose.setEnabled(false);
        btnServerClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerCloseActionPerformed(evt);
            }
        });

        btnClientOpen.setText("Connect");
        btnClientOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientOpenActionPerformed(evt);
            }
        });

        btnClientClose.setText("Close");
        btnClientClose.setEnabled(false);
        btnClientClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientCloseActionPerformed(evt);
            }
        });

        btnClientSend.setText("Send");
        btnClientSend.setEnabled(false);
        btnClientSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientSendActionPerformed(evt);
            }
        });

        btnServerSend.setText("Send");
        btnServerSend.setEnabled(false);
        btnServerSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerSendActionPerformed(evt);
            }
        });

        btnTestStart.setText("Start");
        btnTestStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestStartActionPerformed(evt);
            }
        });

        btnTestStop.setText("Stop");
        btnTestStop.setEnabled(false);
        btnTestStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestStopActionPerformed(evt);
            }
        });

        lblSession.setText("0");

        lblClient.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnServerOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnServerClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnServerSend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClientOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClientClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClientSend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTestStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTestStop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClient)
                            .addComponent(lblSession))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnServerOpen)
                    .addComponent(btnServerClose)
                    .addComponent(btnServerSend))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClientOpen)
                    .addComponent(btnClientClose)
                    .addComponent(btnClientSend))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTestStart)
                    .addComponent(btnTestStop)
                    .addComponent(lblSession))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClient)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnServerOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerOpenActionPerformed
        openServer();
    }//GEN-LAST:event_btnServerOpenActionPerformed

    private void btnServerCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerCloseActionPerformed
        closeServer();
    }//GEN-LAST:event_btnServerCloseActionPerformed

    private void btnClientOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientOpenActionPerformed
        openClient();
    }//GEN-LAST:event_btnClientOpenActionPerformed

    private void btnClientCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientCloseActionPerformed
        closeClient();
    }//GEN-LAST:event_btnClientCloseActionPerformed

    private void btnServerSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerSendActionPerformed
        sendServer();
    }//GEN-LAST:event_btnServerSendActionPerformed

    private void btnClientSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientSendActionPerformed
        sendClient();
    }//GEN-LAST:event_btnClientSendActionPerformed

    private void btnTestStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestStartActionPerformed
        testStart();
    }//GEN-LAST:event_btnTestStartActionPerformed

    private void btnTestStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestStopActionPerformed
        testStop();
    }//GEN-LAST:event_btnTestStopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientClose;
    private javax.swing.JButton btnClientOpen;
    private javax.swing.JButton btnClientSend;
    private javax.swing.JButton btnServerClose;
    private javax.swing.JButton btnServerOpen;
    private javax.swing.JButton btnServerSend;
    private javax.swing.JButton btnTestStart;
    private javax.swing.JButton btnTestStop;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblSession;
    // End of variables declaration//GEN-END:variables

    private Server server;
    
    private Session client;
    
    private Session session;
    
    private volatile boolean testing = false;
    
    private volatile boolean done = true;
    
    private Thread thread;
    
    private Thread packets;
    
    public void openServer()
    {
        try {
            server = new Server(15555);
            
            server.addCallback(new Server.Callback() {
                @Override
                public void onRequest(Server server, Socket socket) {
                    btnServerSend.setEnabled(true);
                    
                    try {
                        session = new Session(socket);
                        
                        session.addCallback(new Session.Callback() {
                            @Override
                            public void onReceived(Session session, Packet packet) {
                            }

                            @Override
                            public void onStopped(Session session) {
                                btnServerSend.setEnabled(false);
                                
                                btnTestStart.setEnabled(true);
                                
                                Window.this.session = null;
                            }

                            @Override
                            public void onStarted(Session session) {
                            }
                        });
                        
                        session.start();
                    } catch (IOException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onStopped(Server server) {
                    btnServerOpen.setEnabled(true);

                    btnServerClose.setEnabled(false);
                }

                @Override
                public void onStarted(Server server) {
                    btnServerOpen.setEnabled(false);

                    btnServerClose.setEnabled(true);
                                
                    btnTestStart.setEnabled(false);
                }
            });
            
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeServer()
    {
        try {
            server.close();

            if (session != null)
            {
                session.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendServer()
    {
        try {
            session.write(new PacketTest());
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openClient()
    {
        try {
//            client = new Session("chemisus.servegame.org", 15555);
            client = new Session("localhost", 15555);

            client.addCallback(new Session.Callback() {

                @Override
                public void onReceived(Session session, Packet packet) {
                }

                @Override
                public void onStopped(Session session) {
                    btnClientOpen.setEnabled(true);

                    btnClientClose.setEnabled(false);

                    btnClientSend.setEnabled(false);
                    
                    client = null;
                }

                @Override
                public void onStarted(Session session) {
                }
            });
            
            client.start();
            
            btnClientOpen.setEnabled(false);
            
            btnClientClose.setEnabled(true);
            
            btnClientSend.setEnabled(true);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeClient()
    {
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendClient()
    {
        try {
            client.write(new PacketTest());
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testStart()
    {
        testing = true;
        
        packets = new Thread() {
            @Override
            public void run() {
                while (testing || !done)
                {
                    lblSession.setText(session.getInCount() + " " + session.getOutCount());

                    lblClient.setText(client.getOutCount() + " " + client.getInCount());
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        thread = new Thread() {
            @Override
            public void run() {
                openServer();

                openClient();

                done = false;

                packets.start();
                
                while (testing)
                {
                    ConcurrentLinkedDeque<Thread> threads = new ConcurrentLinkedDeque<>();

                    for (int i = 0; i < 15000; i++)
                    {
                        threads.add(new Thread() {
                            @Override
                            public void run() {
                                sendClient();

                                sendServer();
                            }
                        });
                    }

                    for (Thread thread : threads)
                    {
                        thread.start();
                    }

                    for (Thread thread : threads)
                    {
                        try {
                            thread.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                done = true;

                closeServer();
            }
        };
        
        thread.start();
        
        btnTestStop.setEnabled(true);
    }
    
    public void testStop()
    {
        new Thread() {

            @Override
            public void run() {
                testing = false;

                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
}

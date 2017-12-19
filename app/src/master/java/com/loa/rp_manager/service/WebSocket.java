package rp_manager.service;

import org.androidannotations.annotations.EService;

import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.UnknownHostException;

/**
 * Created by Hanyas on 04/09/2017.
 */
@EService
public class WebSocket {
    private WebSocketServer webSocketServer = null;

    public WebSocket() {
        try {
            webSocketServer = new WebSocketServer() {
                @Override
                public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {

                }

                @Override
                public void onClose(org.java_websocket.WebSocket conn, int code, String reason, boolean remote) {

                }

                @Override
                public void onMessage(org.java_websocket.WebSocket conn, String message) {

                }

                @Override
                public void onError(org.java_websocket.WebSocket conn, Exception ex) {

                }
            };
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

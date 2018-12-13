package com.wildBirds.BlueChat.api.webSocket;


import com.wildBirds.BlueChat.api.webSocket.types.LocalProcedure;
import com.wildBirds.BlueChat.api.webSocket.types.RemoteProcedure;
import com.wildBirds.WebSocketRpc.api.WSR;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(WSR().getHandler(),"/socket").setAllowedOrigins("*");
        System.out.println("REGISTERED");
    }

    @Bean
    public WSR WSR() {
        return new WSR(LocalProcedure.class, RemoteProcedure.class);
    }
}

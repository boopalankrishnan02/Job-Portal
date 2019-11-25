//package com.jb.frontend.config;
////package com.jb.backend.dbconfig;
////package com.jb.backend.dbconfig;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.ChannelRegistration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//@ComponentScan("com.jb")
//public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//		
//	public void registerStompEndPoints(StompEndpointRegistry registry) {
//		System.out.println("REGISTER STOMP ENDPOINTS....");
//		registry.addEndpoint("/chatmodule").withSockJS();
//	}
//	
//	public void configureMessageBroker(MessageBrokerRegistry configurer) {
//		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY....");
//		configurer.enableSimpleBroker("/queue/","/topic/");
//		configurer.setApplicationDestinationPrefixes("/app");
//	}
//	
//////	public void configureClientInboundChannel(ChannelRegistration registration) {
//////		// TODO Auto-generated method stub
//////		
//////	}
//////
//////
//////	public void configureClientOutboundChannel(ChannelRegistration registration) {
//////		// TODO Auto-generated method stub
//////		
//////	}
//}

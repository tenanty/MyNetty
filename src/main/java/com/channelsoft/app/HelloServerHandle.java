package com.channelsoft.app;

import org.jboss.netty.channel.*;

/**
 * Created by yuanshun on 2015/1/22.
 */
public class HelloServerHandle extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected...");
        e.getChannel().write("Hello client...");
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed...");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String message = (String)e.getMessage();
        System.out.println(message);
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("server exceptionCaught...." + e);
        e.getChannel().close();
    }
}

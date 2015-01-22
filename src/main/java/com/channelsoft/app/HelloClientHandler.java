package com.channelsoft.app;

import org.jboss.netty.channel.*;


/**
 * Created by yuanshun on 2015/1/22.
 */
public class HelloClientHandler extends SimpleChannelHandler {
    /**
     * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx,ChannelStateEvent e) {
        System.out.println("Hello world, I'm client.");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String message = (String) e.getMessage();
        System.out.println(message);
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("Unexcepted exception from downstream.");
        e.getChannel().close();

    }
}

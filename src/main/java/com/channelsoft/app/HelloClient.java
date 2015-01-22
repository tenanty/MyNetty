package com.channelsoft.app;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by yuanshun on 2015/1/22.
 */
public class HelloClient {
    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new HelloClientHandler());
//                return Channels.pipeline(new HelloClientHandler());
                return pipeline;
            }
        });
        // 连接到本地的8000端口的服务端
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));

        future.getChannel().getCloseFuture().awaitUninterruptibly();
        bootstrap.releaseExternalResources();
    }
}

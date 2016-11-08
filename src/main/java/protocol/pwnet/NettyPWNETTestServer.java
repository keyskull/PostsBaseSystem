package protocol.pwnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Created by Jane on 2016/11/4.
 */
public class NettyPWNETTestServer {
    public static void main(String args[]) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new NettyPWNETInitializer());
            // Start the connection attempt.
            //Channel ch = b.connect("127.0.0.1", 8888).sync().channel();
            Channel ch2 =  b.bind(8889).sync().channel();

            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";
            PWNETHeader header = new PWNETHeader(version, content.length(), sessionId);
            PWNETMessage message = new PWNETMessage(header, content);
           // ch2.writeAndFlush(message);
            ch2.closeFuture().sync();
           // ch.closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }


}

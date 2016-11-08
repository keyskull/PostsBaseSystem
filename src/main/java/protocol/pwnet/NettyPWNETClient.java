package protocol.pwnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Created by Jane on 2016/11/4.
 */
public class NettyPWNETClient {

    public static void main(String args[]) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new NettyPWNETInitializer());
            // Start the connection attempt.
            Channel ch =  b.bind(8888).channel();

            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";
            PWNETHeader header = new PWNETHeader(version, content.length(), sessionId);
            PWNETMessage message = new PWNETMessage(header, content);
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(message.toString().getBytes()),new InetSocketAddress("127.0.0.1",8889)));
            ch.close();

        } finally {
            group.shutdownGracefully();
        }
    }
}

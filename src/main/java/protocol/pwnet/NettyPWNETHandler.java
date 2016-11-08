package protocol.pwnet;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import sun.plugin2.message.Message;

/**
 * Created by Jane on 2016/11/4.
 */
public class NettyPWNETHandler extends SimpleChannelInboundHandler<PWNETMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PWNETMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println(msg.toString());
        ctx.pipeline().writeAndFlush(msg);
    }
}

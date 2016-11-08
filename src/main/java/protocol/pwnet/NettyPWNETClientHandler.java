package protocol.pwnet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Jane on 2016/11/4.
 */
public class NettyPWNETClientHandler extends SimpleChannelInboundHandler<PWNETMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PWNETMessage message) throws Exception {
        System.out.println(message);
    }
}

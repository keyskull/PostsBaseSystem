package protocol.pwnet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Jane on 2016/11/4.
 */
@ChannelHandler.Sharable
public class PWNETEncoder extends MessageToByteEncoder<PWNETMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PWNETMessage message, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        PWNETHeader header = message.getPWNETHeader();

        // 这里写入的顺序就是协议的顺序.

        // 写入Header信息
        ctx.handler();
        out.writeInt(header.getVersion());
        out.writeInt(message.getContent().length());
        out.writeBytes(header.getSessionId().getBytes());

        // 写入消息主体信息
        out.writeBytes(message.getContent().getBytes());
    }
}

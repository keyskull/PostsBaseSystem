package protocol.pwnet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Jane on 2016/11/4.
 */
public class PWNETDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {

        // 获取协议的版本
        ByteBuf in = msg.content();
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        System.out.println(sessionByte.toString());
        String sessionId = new String(sessionByte);

        // 组装协议头
        PWNETHeader header = new PWNETHeader(version, contentLength, sessionId);

        // 读取消息内容
        byte[] content = new byte[in.readableBytes()];
        in.readBytes(content);
        System.out.println(content.toString());

        PWNETMessage message = new PWNETMessage(header, new String(content));

        out.add(message);
    }
}

package protocol.pwnet;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Jane on 2016/11/4.
 */
public class PWNETMessage {
    private PWNETHeader pwnetHeader;
    private String content;

    public PWNETMessage(PWNETHeader pwnetHeader, String content) {
        this.pwnetHeader = pwnetHeader;
        this.content = content;
    }

    public PWNETHeader getPWNETHeader() {
        return pwnetHeader;
    }

    public void setLuckHeader(PWNETHeader pwnetHeader) {
        this.pwnetHeader = pwnetHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,contentLength=%d,sessionId=%s,content=%s]",
                pwnetHeader.getVersion(),
                pwnetHeader.getContentLength(),
                pwnetHeader.getSessionId(),
                content);
    }

}

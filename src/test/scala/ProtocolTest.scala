/**
  * Created by Jane on 2016/11/8.
  */

import org.scalatest.FunSuite
import protocol.pwnet.NettyPWNETServer

class ProtocolTest extends FunSuite {
  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      new NettyPWNETServer
    }
  }
}

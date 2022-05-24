package com.fangj.camel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext

/**
 * Camel最核心的功能就是提供路由引擎。
 * 你可以自己定义路由规则，包括从哪里接收消息，怎么处理消息，发给哪个目标。
 * 最关键的是你去集成各种系统而不用关心怎么转换数据格式，也不用关心通讯协议是什么。
 * Camel的组件有去实现不同协议和数据类型的API，开箱即用，现在已经支持超过8 0 种的协议和数据类型。
 * Apache ServiceMix 和 ActiveMQ 已经使用 Camel 作为进行企业 级 集成的一种方式。
 * 有的人说Camel是一个轻量级的ESB（ Enterprise Service Bus ），因为它有路由功能、转换功能、监控功能、编排功能等等，但是它并没有容器和可靠的消息总线，
 * 所以其实它并不能算是一个ESB服务。但是它可以部署到Open -ESB 上，比如Service Mix 。
 * camel文件路由转换
 */
object CamelFile {

  def main(args: Array[String]): Unit = {
    val ctx = new DefaultCamelContext()
    ctx.addRoutes(new RouteBuilder() {
      // noop =true表示保留源文件，如果不加则表示移动文件到指定目录，原文件夹的文件会不在
      override def configure(): Unit = {
        from("file:/Users//camel/file_from?noop=true").to("file:/Users//camel/file_to")
      }
    })
    ctx.start()
    Thread.sleep(10000)
  }

}

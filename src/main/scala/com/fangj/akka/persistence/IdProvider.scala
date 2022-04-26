package com.fangj.akka.persistence

import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, RecoveryCompleted, SaveSnapshotSuccess, SnapshotOffer}

object IdProvider {

  case object GenerateId

  case class IdGenerated(id: Int)


}

class IdProvider extends PersistentActor with ActorLogging {

  import IdProvider._

  /**
   * 作为持久化Actor的唯一表示，用于持久化或者查询时使用
   */
  override val persistenceId: String = "IdProvider"

  private var currentId = 0

  /**
   * Actor重启恢复是执行的逻辑
   *
   * @return
   */
  override def receiveRecover: Receive = {
    case IdGenerated(id) =>
      println(s"${id}>>>")
      currentId = id
    case SnapshotOffer(_, snapshot: Int) =>
      // 在快照完整的情况下，Actor优先从快照恢复自身状态
      log.info(s"Recover actor state from snapshot and the snapshot is ${snapshot}")
      // 利用快照恢复Actor的状态
      currentId = snapshot
    case RecoveryCompleted => log.info("the actor recover completed")
  }

  /**
   * Actor正常运行时处理处理消息逻辑，可在这部分内容里持久化自己想要的消息
   *
   * @return
   */
  override def receiveCommand: Receive = {
    case GenerateId =>
      persist(IdGenerated(currentId + 1)) { evt =>
        val snapShotInterval = 50
        currentId = evt.id
        println("currentId == " + currentId)
        // 当有持久化5个事件后我们便存储一次当前Actor状态的快照
        if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0) {
          self ! "saveSnapshot"
        }
        sender() ! evt
      }
    case "saveSnapshot" => {
      // 接收存储快照命令执行存储快照操作
      println("saveSnapshot " + currentId)
      saveSnapshot(currentId)
    }
    case SaveSnapshotSuccess(metadata) => {
      // 你可以在快照存储成功后做一些操作，比如删除之前的快照等
      println("快照存储成功!!!")
    }
  }

}

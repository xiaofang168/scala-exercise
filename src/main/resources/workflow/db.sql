/*表： flow_task(存放需要完成的任务信息)*/------------------
CREATE TABLE `flow_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flow_name` varchar(50) DEFAULT NULL COMMENT '流程名称',
  `flow_num` varchar(50) DEFAULT NULL COMMENT '流程号',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `step` varchar(10) DEFAULT NULL COMMENT '当前步骤(状态)',
  `executor` varchar(500) DEFAULT NULL COMMENT '执行者',
  `start_time` varchar(14) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(14) DEFAULT NULL COMMENT '结束时间',
  `parent` int(11) DEFAULT NULL COMMENT '父任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/*表： flow_execution(存放流程执行信息)*/-----------------------
CREATE TABLE `flow_execution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flow_name` varchar(50) DEFAULT NULL COMMENT '流程名称',
  `flow_num` varchar(50) DEFAULT NULL COMMENT '流程号',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `step` varchar(10) DEFAULT NULL COMMENT '步骤(状态)',
  `executor` varchar(50) DEFAULT NULL COMMENT '执行者',
  `start_time` varchar(14) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(14) DEFAULT NULL COMMENT '结束时间',
  `parent` int(11) DEFAULT NULL COMMENT '父任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
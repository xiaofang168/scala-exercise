scala-exercise
==============

scala exercise and lean

使用github配置代理的方法:

如使用Google GoAgent

在本地项目库中找到.git\config文件,在文件中添加http代理配置即可
[http]
	proxy = http://127.0.0.1:8087
	
# use the -x command line argument which excludes any task.
gradle build -x test

# jetty debug VM arguments 
-Dtapestry.production-mode=false

# gradle exclude setting
compile ('commons-collections:commons-collections:3.2.1'){
		 exclude module: 'beanutils' //by artifact name
     	//exclude group: 'org.jmock' //by group
     	//exclude group: 'org.unwanted', module: 'iAmBuggy' //by both name and group
}

sbt 命令
项目Eclipse导入
>sbt eclipse
更新资源
>sbt update
编译
>sbt eclipse
copy 资源文件
>sbt copy-resources
编译测试文件
>sbt test:compile
运行文件命令
>sbt < command
持续编译
>~ ;compile;test:compile;copy-resources
单独测试某一个类
test-only com.example.Test

// sbt !使用方法
!
History commands:
   !!    Execute the last command again
   !:    Show all previous commands
   !:n    Show the last n commands
   !n    Execute the command with index n, as shown by the !: command
   !-n    Execute the nth command before this one
   !string    Execute the most recent command starting with 'string'
   !?string    Execute the most recent command containing 'string'

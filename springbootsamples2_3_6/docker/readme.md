1、项目打jar包
2、将Dockerfile、jar包文件上传到服务器
3、执行命令构建Dockerfile将jar包文件生成名叫springbootapp的镜像文件 
docker build -t dockerspringapp .
4、执行命令运行生成的镜像
docker run -d -P --name app1 dockerspringapp
5、查看运行的镜像
docker ps
看到ports对应暴露端口
6、测试运行的应用
curl localhost:暴露端口/test
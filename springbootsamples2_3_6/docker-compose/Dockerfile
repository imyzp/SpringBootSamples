# 基础镜像通过java8构建
FROM java:8

# 拷贝当前项目的jar包
COPY *.jar /app.jar

# 启动命令
CMD ["--server.port=8080"]

# 暴露项目的启动端口
EXPOSE 8080

# 运行命令启动应用
ENTRYPOINT ["java","-jar","/app.jar"]
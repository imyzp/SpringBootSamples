# 基础镜像通过java8构建
FROM java:8

# 拷贝运行的包
COPY *.jar /app.jar

# 自定义应用启动的端口
CMD ["--server.port=8080"]

# 容器对外暴露的端口，与应用启动的端口一致，才能访问
EXPOSE 8080

# 运行命令启动应用
ENTRYPOINT ["java","-jar","/app.jar"]
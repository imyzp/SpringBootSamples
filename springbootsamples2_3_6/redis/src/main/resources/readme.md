## springboot整合redis
- 引入jar包
    - 查看pom中Redis相关
- yml文件中添加redis配置
- springboot目前使用RedisTemplate来理方便的操作redis
    - 见com.example.fs.config.redis.RedisConfig
- 封装工具类操作redis,见com.example.fs.collaborators.yzpDaily.redisModule.util.RedisClient
- 更多参见com.example.fs.collaborators.yzpDaily.redisModule
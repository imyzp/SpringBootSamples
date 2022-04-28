Filter可以认为是Servlet的一种“加强版”，它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理，是个典型的处理链。Filter也可以对用户请求生成响应，这一点与Servlet相同，
但实际上很少会使用Filter向用户请求生成响应。使用Filter完整的流程是：Filter对用户请求进行预处理，接着将请求交给Servlet进行预处理并生成响应，最后Filter再对服务器响应进行后处理。

Filter有如下几个用处。

 - 在HttpServletRequest到达Servlet之前，拦截客户的HttpServletRequest。
 - 根据需要检查HttpServletRequest，也可以修改HttpServletRequest头和数据。
 - 在HttpServletResponse到达客户端之前，拦截HttpServletResponse。
 - 根据需要检查HttpServletResponse，也可以修改HttpServletResponse头和数据。

Filter有如下几个种类。

 - 用户授权的Filter：Filter负责检查用户请求，根据请求过滤用户非法请求。
 - 日志Filter：详细记录某些特殊的用户请求。
 - 负责解码的Filter:包括对非标准编码的请求解码。
 - 能改变XML内容的XSLT Filter等。
 - Filter可以负责拦截多个请求或响应；一个请求或响应也可以被多个Filter拦截。

创建Filter必须实现javax.servlet.Filter接口，在该接口中定义了如下三个方法。

 - void init(FilterConfig config):用于完成Filter的初始化。
 - void destory():用于Filter销毁前，完成某些资源的回收。
 - void doFilter(ServletRequest request,ServletResponse response,FilterChain chain):实现过滤功能，该方法就是对每个请求及响应增加的额外处理。
 - 该方法可以实现对用户请求进行预处理(ServletRequest request)，也可实现对服务器响应进行后处理(ServletResponse response)—它们的分界线为是否调用了chain.doFilter(),执行该方法之前，
 - 即对用户请求进行预处理；执行该方法之后，即对服务器响应进行后处理。
package com.itheima.aop;


import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;

import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {


    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知，拦截所有带有 @Log 注解的方法
     * 切入点表达式：@annotation(com.itheima.anno.Log)
     */
    @Around("@annotation(com.itheima.anno.LogOperation)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. 获取操作人ID（从请求头中的JWT令牌解析）
        //String jwt = request.getHeader("token");
        //Claims claims = JwtUtils.parseJWT(jwt);
        //Integer operateEmpId = Integer.parseInt(claims.get("id").toString());

        // 2. 记录操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 3. 获取目标类的全类名
        String className = joinPoint.getTarget().getClass().getName();

        // 4. 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();

        // 5. 获取方法运行时参数
        String methodParams = Arrays.toString(joinPoint.getArgs());

        // 6. 记录方法执行开始时间
        long begin = System.currentTimeMillis();

        // 7. 执行原始目标方法
        Object result = joinPoint.proceed();

        // 8. 记录方法执行结束时间，计算耗时
        long end = System.currentTimeMillis();
        Long costTime = end - begin;

        // 9. 获取方法返回值（转为JSON字符串）

        // 10. 构建操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId());
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        // 11. 保存日志到数据库
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        // 12. 返回原始方法的执行结果
        return result;
    }
}
package com.example.demo.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

/**
 * 辽宁捷畅物流有限公司 -信息技术中心
 * <p>
 * 对象工具类
 *
 * @author 臧英明
 * @create 2017-12-04
 */
public abstract class ObjectUtil {
    private static Logger logger = Logger.getLogger(ReflectionUtils.class);

    /**
     * 对象克隆工具
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static <T> T cloneObject(Object obj, Class<T> clazz) {
        try {
            String string = JSONObject.toJSONString(obj);
            T t = JSONObject.parseObject(string, clazz);
            return t;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}

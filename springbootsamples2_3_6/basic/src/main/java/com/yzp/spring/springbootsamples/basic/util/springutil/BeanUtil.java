package com.yzp.spring.springbootsamples.basic.util.springutil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * 对象复制
 */
public class BeanUtil {
    /**
     * <pre>
     *     List<UserBean> userBeans = userDao.queryUsers();
     *     List<UserDTO> userDTOs = BeanUtil.batchTransform(UserDTO.class, userBeans);
     * </pre>
     * @param clazz
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> batchTransform(final Class<T> clazz, List<? extends Object> srcList){

        if(CollectionUtils.isEmpty(srcList)){
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(srcList.size());
        for(Object srcObject: srcList)
        {
            result.add(transForm(clazz,srcObject));
        }
        return result;
    }

    /**
     * 封装（@link org.springframework.beans.BeanUtils#copyProperties）直接将转换结果返回
     * <pre>
     *     UserBean userBean = new UserBean("username);
     *     return BeanUtil.transform(UserDTO.class, userBean);
     * </pre>
     * @param clazz
     * @param src
     * @param <T>
     * @return
     */
    private static <T> T transForm(Class<T> clazz, Object src) {
        if(src == null){
            return null;
        }
        T instance = null;
        try{
            instance = clazz.newInstance();
        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(src,instance,getNullPropertyNames(src));
        return instance;

    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapperImpl src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd:pds)
        {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }

}

package com.jmdz.fushan.wrapper;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.common.wrapper.ValidateWrapper;
import com.jmdz.fushan.base.BaseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler封装处理类
 *
 * @author LiCongLu
 * @date 2020-01-09 14:12
 */
@Component
public class HandlerWrapper extends BaseService {

    /**
     * 验证请求数据
     *
     * @param bean 请求数据
     * @return
     * @author LiCongLu
     * @date 2020-01-09 15:49
     */
    public <T extends BaseBean> BaseResult validateBean(T bean) {
        // 实体不存在
        if (DataUtil.isNull(bean)) {
            return null;
        }

        // 验证数据
        HashMap<String, String> hashMap = ValidateWrapper.validateBean("", bean.getClass(), bean);
        if (hashMap != null && hashMap.size() > 0) {
            String[] values = hashMap.values().toArray(new String[]{});
            return failure(StringUtil.joinComma(values));
        }
        return null;
    }

    /**
     * 验证请求集合数据
     *
     * @param beanList 请求数据集合
     * @return
     * @author LiCongLu
     * @date 2020-03-10 09:47
     */
    public <T extends BaseBean> BaseResult validateList(ArrayList<T> beanList) {
        // 集合不存在
        if (DataUtil.invalid(beanList)) {
            return null;
        }

        // 验证数据集合
        HashMap<String, String> hashMap = ValidateWrapper.validateList("", beanList);
        if (hashMap != null && hashMap.size() > 0) {
            String[] values = hashMap.values().toArray(new String[]{});
            return failure(StringUtil.joinComma(values));
        }
        return null;
    }
}

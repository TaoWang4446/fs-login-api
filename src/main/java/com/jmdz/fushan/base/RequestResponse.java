package com.jmdz.fushan.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

/**
 * RequestResponse基类
 *
 * @author LiCongLu
 * @date 2019-12-20 16:36
 */
@SuppressWarnings("unchecked")
public class RequestResponse {

    /**
     * 获取Request请求
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 Response响应
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取Session会话
     *
     * @return
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 判断是否Get请求
     *
     * @return
     */
    public boolean isGet() {
        return "get".equals(getRequest().getMethod().toLowerCase());
    }

    /**
     * 判断是否Post请求
     *
     * @return
     */
    public boolean isPost() {
        return "post".equals(getRequest().getMethod().toLowerCase());
    }

    /**
     * 得到请求数据值
     *
     * @param paramKey
     * @param defObj
     * @return
     */
    public Object getObject(String paramKey, Object defObj) {
        if (paramKey != null && paramKey.length() > 0) {
            Object obj = getRequest().getAttribute(paramKey);
            if (obj != null) {
                return obj;
            }
        }
        return defObj;
    }

    public <T> T getBean(String paramKey, Class<T> clazz) {
        Object obj = getObject(paramKey, null);
        if (obj != null && obj.getClass().equals(clazz)) {
            return (T) obj;
        }
        return null;
    }

    /**
     * 向页面传递数据值
     *
     * @param key
     * @param value
     */
    public void putValue(String key, Object value) {
        if (getRequest() != null) {
            getRequest().setAttribute(key, value);
        }
    }

    /**
     * 获取请求字符串值
     *
     * @param paramKey
     * @param defVal
     * @return
     */
    public String getString(String paramKey, String defVal) {
        if (paramKey != null && paramKey.length() > 0) {
            HttpServletRequest request = getRequest();
            String paramVal = request.getParameter(paramKey);
            if (paramVal != null && paramVal.length() > 0) {
                return paramVal;
            }

            // 不区分大小写获取
            Enumeration<String> paramNames = getRequest().getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (paramKey.toLowerCase().equals(paramName)) {
                    paramVal = request.getParameter(paramName);
                    if (paramVal != null && paramVal.length() > 0) {
                        return paramVal;
                    }
                }
            }
        }
        return defVal;
    }

    /**
     * 获取请求字符串值
     *
     * @param paramKey
     * @return
     */
    public String getString(String paramKey) {
        return getString(paramKey, "");
    }

    /**
     * 获取请求整数值
     *
     * @param paramKey
     * @return
     */
    public int getInt(String paramKey) {
        try {
            String value = getString(paramKey);
            if (value != null && value.length() > 0) {
                return Integer.parseInt(value);
            }
        } catch (Exception ex) {
            return 0;
        }
        return 0;
    }

    /**
     * 获取请求整数值
     *
     * @param paramKey
     * @param defVal
     * @return
     */
    public int getInt(String paramKey, int defVal) {
        int num = getInt(paramKey);
        return num > 0 ? num : defVal;
    }

    /**
     * 获取Session属性值
     *
     * @param nameKey 属性名称
     * @param defVal  默认值
     * @return
     */
    public String getSessionString(String nameKey, String defVal) {
        Object obj = getSession().getAttribute(nameKey);
        if (obj != null && obj instanceof String) {
            return obj.toString();
        }
        return defVal;
    }

    /**
     * 读取Session值
     *
     * @param nameKey
     * @return
     */
    public String getSessionString(String nameKey) {
        return getSessionString(nameKey, "");
    }

    /**
     * 清除Session值
     *
     * @param keys
     */
    public void clearSession(String... keys) {
        if (keys != null && keys.length > 0) {
            HttpSession session = getSession();
            for (String key : keys) {
                session.removeAttribute(key);
            }
        }
    }

    /**
     * 从数据流获取请求数据
     *
     * @return
     */
    public String getStringInputStream() {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = getRequest().getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取基础访问路径
     *
     * @return
     */
    public String getBaserUrl() {
        HttpServletRequest request = getRequest();
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return baseUrl;
    }

    /**
     * 获取完整访问路径
     *
     * @param path
     * @return
     */
    public String getFullUrl(String path) {
        String fullPath;
        String baseUrl = getBaserUrl();
        String backslash = "/";
        if (path.startsWith(backslash) && !baseUrl.endsWith(backslash)) {
            fullPath = baseUrl + path;
        } else if (!path.startsWith(backslash) && baseUrl.endsWith(backslash)) {
            fullPath = baseUrl + path;
        } else if (path.startsWith(backslash) && baseUrl.endsWith(backslash)) {
            fullPath = baseUrl + path.substring(1, path.length() - 1);
        } else {
            fullPath = baseUrl + backslash + path;
        }
        return fullPath;
    }
}

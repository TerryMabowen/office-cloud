package cn.mbw.oc.spy.web.filter;

import cn.mbw.oc.common.kit.http.RequestParameterUtil;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * 统一修改表单参数(表单提交的空字符串统一转null,同时还可以对其他类型的参数做相应的处理)
 * @author Mabowen
 * @date 2020-03-16 10:36
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = Maps.newHashMap();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出
        super(request);

        //将参数表，赋予给当前的Map以便于持有request中的参数
        Map<String, String[]> map = Maps.newHashMap(request.getParameterMap());

//         删除空字符串参数
//        map = MapUtil.filterEmptyParam(map);

        // 对不同类型的参数进行处理
        RequestParameterUtil.discopeParams(map);

        this.params.putAll(map);
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     * @author Mabowen
     * @date 11:21 2020-03-16
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 同上
     * @author Mabowen
     * @date 11:21 2020-03-16
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    /**
     * 增加多个参数
     * @author Mabowen
     * @date 11:22 2020-03-16
     */
    public void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 增加参数
     * @author Mabowen
     * @date 11:22 2020-03-16
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }
}

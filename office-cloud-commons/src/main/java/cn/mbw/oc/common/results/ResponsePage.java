package cn.mbw.oc.common.results;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Mabowen
 * @date 2020-01-08 09:25
 */
@Data
@ToString
@Accessors(chain = true)
public class ResponsePage {
    /**
     * 网络请求的成功的状态
     */
    public static final int OK = 0;
    public static final int SUCCESS = 200;
    public static final int DEFAULT_ERROR = 500;
    public static final int FORBIDDEN = 403;
    public static final int PARAM_ERROR = 400;

    /**
     * 属性
     */
    private int code;
    private String msg;
    private long count;
    private Object data;

    public ResponsePage() {
    }

    public ResponsePage(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static ResponsePage newFailed() {
        ResponsePage responsePage = new ResponsePage();
        responsePage.setCode(DEFAULT_ERROR);
        responsePage.setMsg("Failed");

        return responsePage;
    }

    public static ResponsePage newFailed(String errorMsg) {
        ResponsePage responsePage = new ResponsePage();
        responsePage.setCode(DEFAULT_ERROR);
        responsePage.setMsg(errorMsg);

        return responsePage;
    }

    public static ResponsePage newSuccess() {
        ResponsePage responsePage = new ResponsePage();

        responsePage.setCode(OK);
        responsePage.setMsg("Success");

        return responsePage;
    }

    public static ResponsePage newSuccess(Object data) {
        ResponsePage responsePage = new ResponsePage();
        responsePage.setCode(OK);
        responsePage.setMsg("Success");
        responsePage.setData(data);
        return responsePage;
    }

}

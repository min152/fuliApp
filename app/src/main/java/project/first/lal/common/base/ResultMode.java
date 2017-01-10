package project.first.lal.common.base;

import java.util.ArrayList;

/**
 * YW zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/4 2017 01 2017/1/4
 * @说明 代码版权归 作者 所有
 */
public class ResultMode<T> {

    private String code;

    private String msg;

    private ArrayList<T> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<T> getContent() {
        return content;
    }

    public void setContent(ArrayList<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResultMode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}

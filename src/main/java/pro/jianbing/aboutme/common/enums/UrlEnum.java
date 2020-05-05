package pro.jianbing.aboutme.common.enums;

import io.netty.util.internal.StringUtil;

public enum UrlEnum {
    GRANDPA("GRANDPA", "/grandpa", "李贺臣"),
    INDEX("INDEX", "/", "主页"),
    RIDING_TAIHU("RIDING_TAIHU", "/riding/taihu", "太湖骑行");

    public static boolean hasValue(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return false;
        }
        for (UrlEnum urlEnum : UrlEnum.values()) {
            if (urlEnum.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static UrlEnum getEnumByValue(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return null;
        }
        for (UrlEnum urlEnum : UrlEnum.values()) {
            if (urlEnum.getValue().equals(value)) {
                return urlEnum;
            }
        }
        return null;
    }

    private String code;
    private String value;
    private String remark;

    UrlEnum(String code, String value, String remark) {
        this.code = code;
        this.value = value;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
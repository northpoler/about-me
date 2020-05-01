package pro.jianbing.aboutme.common.enums;

public enum EmailTypeEnum {
    TIMELINE("TIMELINE","时间线"),
    RIDING_TAIHU_ADVISE("RIDING_TAIHU_ADVISE","环太湖骑行建议");
    private String code;
    private String value;

    EmailTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
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
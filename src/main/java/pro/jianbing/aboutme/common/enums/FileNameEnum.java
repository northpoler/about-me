package pro.jianbing.aboutme.common.enums;

import io.netty.util.internal.StringUtil;

public enum FileNameEnum {
    LI_HE_CHEN("li_hechen","《继古开今图》.jpg");
    private String code;
    private String value;

    FileNameEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(String code) {
        if (StringUtil.isNullOrEmpty(code)) {
            return null;
        }
        FileNameEnum[] values = FileNameEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (code.equals(values[i].getCode())){
                return values[i].getValue();
            }
        }
        return null;
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

package pro.jianbing.aboutme.common.util;

import org.junit.Test;

import java.util.List;

public class CueWordUtilTest {

    @Test
    public void getCueWords() {
        List<String> words = CueWordUtil.getCueWords("测试");
        System.out.println(words);
    }
}
package cupraccoon.myboard.domain.board;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    dev("D", "개발게시판"),
    free("F", "자유게시판"),
    game("G", "게임게시판"),
    hobby("H", "취미게시판"),
    all("A", "종합게시판"),
    test("T","페이지테스트");

    private final String dtype;
    private final String korName;

    private Category(String dtype, String korName) {
        this.dtype = dtype;
        this.korName = korName;
    }

    private String dtype() {
        return dtype;
    }

    private String korName() {
        return korName;
    }


    public static String findCategoryByUrl(String urlName) {
        return Category.valueOf(urlName).dtype();
    }

    public static String findKorNameByUrl(String urlName) {
        return Category.valueOf(urlName).korName();
    }
}

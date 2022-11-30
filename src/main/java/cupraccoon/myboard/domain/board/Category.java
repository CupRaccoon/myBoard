package cupraccoon.myboard.domain.board;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    Dev("D","개발","dev"),
    Free("F","자유","free"),
    Game("G","게임","game"),
    Hobby("H","취미","hobby");

    private final String discriminator;
    private final String korName;
    private final String urlName;

    private Category(String discriminator, String korName, String urlName) {
        this.discriminator = discriminator;
        this.korName = korName;
        this.urlName = urlName;
    }

    private String Discriminator() {
        return discriminator;
    }

    private String KorName() {
        return korName;
    }

    private String UrlName() {
        return urlName;
    }
    private static final Map<String, Category> BY_URLNAME =
            Stream.of(values()).collect(Collectors.toMap(Category::UrlName, Function.identity()));

    public static String findCategoryByUrl(String urlName){
        return BY_URLNAME.get(urlName).name();
    }



}

package sample.web;

public class ContentRange {
    public static String NAME = "Content-Range";

    public static String value(String resourceName, Integer offset, Integer length, Integer total) {
        return String.format("%s %s-%s/%s", resourceName, offset, offset + length, total);
    }
}

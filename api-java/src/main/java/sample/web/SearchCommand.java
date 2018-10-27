package sample.web;

public class SearchCommand {

    public String filter;
    public String range;
    public String sort;

    @Override
    public String toString() {
        return "SearchCommand{" +
                "filter='" + filter + '\'' +
                ", sort='" + sort + '\'' +
                ", range=" + range +
                '}';
    }
}

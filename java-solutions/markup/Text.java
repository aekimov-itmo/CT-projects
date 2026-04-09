package markup;


public class Text implements ParseComponent {
    private String line;
    public Text(String line) {
        this.line = line;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(line);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(line);
    }
}

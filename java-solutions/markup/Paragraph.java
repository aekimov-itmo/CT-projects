package markup;

import java.util.List;

public class Paragraph extends AbstractMarkdown {
    private List<ParseComponent> markedPiece;

    public Paragraph(List<ParseComponent> arr) {
        super(arr);
    }

    // :NOTE: copypaste
    @Override
    protected String getMarkdownTag() {
        return "";
    }

    @Override
    protected String getHtmlTag() {
        return "p";
    }
}

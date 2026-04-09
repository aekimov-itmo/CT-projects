package markup;

import java.util.List;

public class Strikeout extends AbstractMarkdown implements ParseComponent{
    public Strikeout(List<ParseComponent> markedPiece) {
        super(markedPiece);
    }

    @Override
    protected String getMarkdownTag() {
        return "~";
    }

    @Override
    protected String getHtmlTag() {
        return "s";
    }
}

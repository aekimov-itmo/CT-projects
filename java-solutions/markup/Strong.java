package markup;

import java.util.List;

public class Strong extends AbstractMarkdown implements ParseComponent{
    public Strong(List<ParseComponent> markedPiece) {
        super(markedPiece);
    }

    @Override
    protected String getMarkdownTag() {
        return "__";
    }

    @Override
    protected String getHtmlTag() {
        return "strong";
    }
}

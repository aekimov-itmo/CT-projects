package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdown implements ParseComponent{

    public Emphasis(List<ParseComponent> markedPiece) {
        super(markedPiece);
    }

    @Override
    protected String getMarkdownTag() {
        return "*";
    }

    @Override
    protected String getHtmlTag() {
        return "em";
    }

}


package markup;

import java.util.List;

public abstract class AbstractMarkdown implements Markdown{
    protected List<ParseComponent> markedPiece;

    public AbstractMarkdown(List<ParseComponent> arr) {
        this.markedPiece = arr;
    }

    protected abstract String getMarkdownTag();

    protected abstract String getHtmlTag();

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(getMarkdownTag());
        for (ParseComponent piece : markedPiece) {
            piece.toMarkdown(sb);
        }
        sb.append(getMarkdownTag());
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<").append(getHtmlTag()).append(">");
        for (ParseComponent piece : markedPiece) {
            piece.toHtml(sb);
        }
        sb.append("</").append(getHtmlTag()).append(">");
    }


}

package aero.champ.exercise.patternmatching.switchcase;

public final class TextElement implements DataElement {
    private final String text;
    public TextElement(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    @Override
    public String getDescription() {
        return "Text: " + text;
    }
}
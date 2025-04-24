package aero.champ.exercise.patternmatching.switchcase;
import java.util.List;

public final class ListElement implements DataElement {
    public final List<DataElement> elements;
    public ListElement(List<DataElement> elements) {
        this.elements = elements;
    }
    public List<DataElement> getElements() {
        return elements;
    }
    @Override
    public String getDescription() {
        return "List with " + elements.size() + " elements";
    }
}

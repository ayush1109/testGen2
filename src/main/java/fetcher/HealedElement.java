package fetcher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HealedElement {
    private WebElement element;
    private ScoreGenerator<By> scoreGenerator;

    public HealedElement() {
    }

    public WebElement getElement() {
        return this.element;
    }

    public ScoreGenerator<By> getScored() {
        return this.scoreGenerator;
    }

    public HealedElement setElement(WebElement element) {
        this.element = element;
        return this;
    }

    public HealedElement setScored(ScoreGenerator<By> scoreGenerator) {
        this.scoreGenerator = scoreGenerator;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof HealedElement)) {
            return false;
        } else {
            HealedElement other = (HealedElement)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$element = this.getElement();
                Object other$element = other.getElement();
                if (this$element == null) {
                    if (other$element != null) {
                        return false;
                    }
                } else if (!this$element.equals(other$element)) {
                    return false;
                }

                Object this$scored = this.getScored();
                Object other$scored = other.getScored();
                if (this$scored == null) {
                    if (other$scored != null) {
                        return false;
                    }
                } else if (!this$scored.equals(other$scored)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof HealedElement;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $element = this.getElement();
        result = result * 59 + ($element == null ? 43 : $element.hashCode());
        Object $scored = this.getScored();
        result = result * 59 + ($scored == null ? 43 : $scored.hashCode());
        return result;
    }

    public String toString() {
        return "HealedElement(element=" + this.getElement() + ", scoreGenerator=" + this.getScored() + ")";
    }
}


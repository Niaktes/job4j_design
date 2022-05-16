package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "cat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cat {

    @XmlAttribute
    private boolean hungry;
    @XmlAttribute
    private int legsNumber;
    @XmlAttribute
    private String hobby;
    private Preserves preserves;
    @XmlElementWrapper(name = "voices")
    @XmlElement(name = "voice")
    private String[] voices;

    public Cat() {

    }

    public Cat(boolean hunger, int legs, String action, Preserves preserves, String... phrases) {
        this.hungry = hunger;
        this.legsNumber = legs;
        this.hobby = action;
        this.preserves = preserves;
        this.voices = phrases;
    }

    public boolean isHungry() {
        return hungry;
    }

    public int getLegs() {
        return legsNumber;
    }

    public String getHobby() {
        return hobby;
    }

    public Preserves getPreserves() {
        return preserves;
    }

    public String[] getVoice() {
        return voices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cat cat = (Cat) o;
        if (hungry != cat.hungry) {
            return false;
        }
        if (legsNumber != cat.legsNumber) {
            return false;
        }
        if (!hobby.equals(cat.hobby)) {
            return false;
        }
        if (!preserves.equals(cat.preserves)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(voices, cat.voices);
    }

    @Override
    public int hashCode() {
        int result = (hungry ? 1 : 0);
        result = 31 * result + legsNumber;
        result = 31 * result + hobby.hashCode();
        result = 31 * result + preserves.hashCode();
        result = 31 * result + Arrays.hashCode(voices);
        return result;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "hungry=" + hungry
                + ", legsNumber=" + legsNumber
                + ", hobby='" + hobby + '\''
                + ", preserves=" + preserves
                + ", voice=" + Arrays.toString(voices)
                + '}';
    }
}

package ru.job4j.serialization;

import java.util.Arrays;

public class Cat {

    private final boolean hungry;
    private final int legsNumber;
    private final String hobby;
    private final Preserves preserves;
    private final String[] voice;

    public Cat(boolean hunger, int legs, String action, Preserves preserves, String[] phrases) {
        this.hungry = hunger;
        this.legsNumber = legs;
        this.hobby = action;
        this.preserves = preserves;
        this.voice = phrases;
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
        return voice;
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
        return Arrays.equals(voice, cat.voice);
    }

    @Override
    public int hashCode() {
        int result = (hungry ? 1 : 0);
        result = 31 * result + legsNumber;
        result = 31 * result + hobby.hashCode();
        result = 31 * result + preserves.hashCode();
        result = 31 * result + Arrays.hashCode(voice);
        return result;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "hungry=" + hungry
                + ", legsNumber=" + legsNumber
                + ", hobby='" + hobby + '\''
                + ", preserves=" + preserves
                + ", voice=" + Arrays.toString(voice)
                + '}';
    }
}

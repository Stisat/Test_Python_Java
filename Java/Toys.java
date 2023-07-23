package JavaTest;

import java.util.Objects;


public class Toys {


    /**
     * номер id игрушки
     */
    private final int id;

    /**
     * поле название игрушки
     */
    private final String toysName;


    /**
     * поле количества игрушек
     */
    private int count;

    /**
     * поле веса игрушки
     */
    private int weight;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getToysName() {
        return toysName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public Toys(int id, String toysName, int count,int weight){
        this.id = id;
        this.toysName = toysName;
        this.count = count;
        this.weight = weight;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toys toys = (Toys) o;
        return id == toys.id  && Objects.equals(toysName, toys.toysName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, toysName);
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%d;%d",id,toysName,count,weight);
    }
}

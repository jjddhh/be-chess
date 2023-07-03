package softeer2nd;

public class Pawn {

    private String color;

    public Pawn(String color) {
        if(color.equals("white") || color.equals("black"))
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}

import java.util.List;

public class Player {
    private String name;
    private List<Poker> pokers;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public void setPokers(List<Poker> pokers) {
        this.pokers = pokers;
    }

    public Player(List<Poker> pokers) {
        this.pokers = pokers;
    }

    public Player(String name, List<Poker> pokers) {
        this.name = name;
        this.pokers = pokers;
    }
}

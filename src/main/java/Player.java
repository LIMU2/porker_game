import java.util.Collections;
import java.util.Comparator;
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

    public String printDraw() {
        return "Draw";
    }

    public String printLose() {
        return this.name + " Lose";
    }

    public String printWin() {
        return this.name + " Win";
    }

    public String vs(Player player) {
        this.bePrepared();
        player.bePrepared();
        for (int i = this.pokers.size() - 1; i >= 0; i--) {
            switch (this.pokers.get(i).compareToSinglePoker(player.pokers.get(i))) {
                case 1:
                    return printWin();
                case -1:
                    return printLose();
                case 0:
                    break;
            }
        }
        return printDraw();
    }

    public void reFinePokers() {
        for (Poker poker : this.pokers) {
            switch (poker.getNumber()) {
                case "T":
                    poker.setNumber("10");
                    break;
                case "J":
                    poker.setNumber("11");
                    break;
                case "Q":
                    poker.setNumber("12");
                    break;
                case "K":
                    poker.setNumber("13");
                    break;
                case "A":
                    poker.setNumber("14");
                    break;
                default:
                    poker.setNumber(poker.getNumber());
            }
        }
    }

    public void sortPokers() {
        Collections.sort(this.pokers, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                return o1.compareToSinglePoker(o2);
            }
        });
    }

    public void bePrepared() {
        this.reFinePokers();
        this.sortPokers();
    }
}

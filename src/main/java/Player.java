import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (this.hasPair() || player.hasPair()) {  // 有对牌的情况
            if (this.hasPair() && !player.hasPair()) {
                return printWin();
            } else if (!this.hasPair() && player.hasPair()) {
                return printLose();
            } else {  // 都有对牌
                switch (this.getPairPoker().compareToSinglePoker(player.getPairPoker())) {  // 比较对牌大小
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return this.getRemain().vs(player.getRemain());  // 剩下的牌， 递归调用
                }
            }
        } else {
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
        return null;
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

    public boolean hasPair() {
        int maxDuplicateNumber = getMaxDuplicateNumber();
        if (maxDuplicateNumber == 2) {
            return true;
        }
        return false;
    }

    public int getMaxDuplicateNumber() {
        return this.pokers.stream()
                .map(poker -> poker.getNumber())
                .collect(Collectors.toMap(poker -> poker, poker -> 1, Integer::sum))
                .entrySet().stream()
                .mapToInt(r -> r.getValue())
                .max()
                .getAsInt();
    }

    public Poker getPairPoker() {  // 只取排在前面的对牌
        int duplicateNumber = 2;  // pair
        List<Map.Entry<String, Integer>> res = getCountedDuplicatedPokers();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).getValue() == duplicateNumber)  // 如果这张牌的出现次数是2次
                for (int j = 0; j < this.pokers.size(); j++) {
                    if (this.pokers.get(j).getNumber() == res.get(i).getKey()) {
                        return this.pokers.get(j);  // 返回这张牌
                    }
                }
        }
        return null;
    }

    public List<Map.Entry<String, Integer>> getCountedDuplicatedPokers() {
        return this.pokers.stream()
                .map(poker -> poker.getNumber())  // 只比较数字
                .collect(Collectors.toMap(poker -> poker, poker -> 1, Integer::sum))
                .entrySet().stream()
                .collect(Collectors.toList());
    }


}

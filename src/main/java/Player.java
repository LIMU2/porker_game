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
        if (this.hasFour() || player.hasFour()) {
            if (this.hasFour() && !player.hasFour()) {
                return printWin();
            } else if (!this.hasFour() && player.hasFour()) {
                return printLose();
            } else {  // 都有炸
                switch (this.pokers.get(this.pokers.size() - 1).compareToSinglePoker(player.pokers.get(this.pokers.size() - 1))) {
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return printDraw();
                }
            }
        }
        if (this.hasFullHouse() || player.hasFullHouse()) {
            if (this.hasFullHouse() && !player.hasFullHouse()) {
                return printWin();
            } else if (!this.hasFullHouse() && player.hasFullHouse()) {
                return printLose();
            } else {  // 都有三带二
                switch (this.pokers.get(this.pokers.size() - 1).compareToSinglePoker(player.pokers.get(this.pokers.size() - 1))) {
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return printDraw();
                }
            }
        }
        if (this.hasFlush() || player.hasFlush()) {
            if (this.hasFlush() && !player.hasFlush()) {
                return printWin();
            } else if (!this.hasFlush() && player.hasFlush()) {
                return printLose();
            } else {  // 都有同花
                switch (this.pokers.get(this.pokers.size() - 1).compareToSinglePoker(player.pokers.get(this.pokers.size() - 1))) {
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return printDraw();
                }
            }
        }
        if (this.hasStraight() || player.hasStraight()) {
            if (this.hasStraight() && !player.hasStraight()) {
                return printWin();
            } else if (!this.hasStraight() && player.hasStraight()) {
                return printLose();
            } else {  // 都有顺子
                switch (this.pokers.get(this.pokers.size() - 1).compareToSinglePoker(player.pokers.get(this.pokers.size() - 1))) {
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return printDraw();
                }
            }
        }
        if (this.hasThree() || player.hasThree()) {  // 有三牌的情况
            if (this.hasThree() && !player.hasThree()) {
                return printWin();
            } else if (!this.hasThree() && player.hasThree()) {
                return printLose();
            } else {  // 都有三牌
                switch (this.getThreePoker().compareToSinglePoker(player.getThreePoker())) {
                    case 1:
                        return printWin();
                    case -1:
                        return printLose();
                    case 0:
                        return this.getRemain().vs(player.getRemain());  // 剩下的牌， 递归调用
                }
            }
        }
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

    public boolean hasThree() {
        int maxDuplicateNumber = getMaxDuplicateNumber();
        if (maxDuplicateNumber == 3) {
            return true;
        }
        return false;
    }

    public boolean hasStraight() {
        for (int i = 0; i < this.pokers.size() - 1; i++) {
            if (Integer.parseInt(this.pokers.get(i + 1).getNumber()) - Integer.parseInt(this.pokers.get(i).getNumber()) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFlush() {
        if (this.pokers.stream()
                .map(p -> p.getSuit())
                .distinct().toArray().length == 1) {
            return true;
        }
        return false;
    }

    public boolean hasFullHouse() {
        if (this.hasThree() && this.hasPair()) {
            return true;
        }
        return false;
    }

    public boolean hasFour() {
        int maxDuplicateNumber = getMaxDuplicateNumber();
        if (maxDuplicateNumber == 4) {
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

    public Poker getThreePoker() {
        int duplicateNumber = 3;
        List<Map.Entry<String, Integer>> res = getCountedDuplicatedPokers();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).getValue() == duplicateNumber)  // 如果这张牌的出现次数是3次
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

    public Player getRemain() {
        if (this.hasPair()) {
            return new Player(this.name, this.pokers.stream()
                    .filter(poker -> poker.getNumber() != this.getPairPoker().getNumber())
                    .collect(Collectors.toList()));
        }
        return this;
    }
}

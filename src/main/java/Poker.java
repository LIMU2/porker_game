public class Poker {
    private String number;
    private String suit;

    public Poker(String number, String suit) {
        this.number = number;
        this.suit = suit;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }

    public int compareToSinglePoker(Poker poker) {
        if (Integer.parseInt(this.getNumber()) > Integer.parseInt(poker.getNumber())) {
            return 1;  // 大于
        } else if (Integer.parseInt(this.getNumber()) < Integer.parseInt(poker.getNumber())) {
            return -1;  // 小于
        } else {
            return 0;  // draw
        }
    }

}
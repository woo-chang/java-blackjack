package blackjack.domain.card;

public enum Suit {

    //이름은 뷰에 관한 정보일까?
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

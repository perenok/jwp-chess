package chess.dto;

public class TurnDto {
    private String turn;

    public TurnDto() {
    }

    public TurnDto(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
}

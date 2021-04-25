package chess.domain.piece;

import chess.domain.position.Position;
import chess.exception.chessgame.PieceDoesNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlankTest {
    @Test
    @DisplayName("기물이 없는 빈칸 선택시 예외발생")
    void getRoute() {
        Piece blank = Blank.getBlank();
        assertThatThrownBy(() -> blank.route(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(PieceDoesNotExistException.class);
    }
}
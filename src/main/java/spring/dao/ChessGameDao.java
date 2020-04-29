package spring.dao;

import spring.chess.team.Team;
import spring.db.DBConnection;
import spring.dto.ChessGameVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    public ChessGameVo findChessGameBy(int gameId) throws SQLException {
        String query = "SELECT * FROM game WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            // TODO : null 제거
            if (!rs.next()) {
                return null;
            }

            ChessGameVo chessGameVo = new ChessGameVo(
                    rs.getInt("id"),
                    rs.getString("white_name"),
                    rs.getString("black_name"),
                    rs.getInt("turn_is_black")
            );

            rs.close();

            return chessGameVo;
        }
    }

    public void updateTurn(Team turn, int gameId) throws SQLException {
        String query = "UPDATE game SET turn_is_black = ? WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, String.valueOf(turn.getTurnIsBlack()));
            pstmt.setString(2, String.valueOf(gameId));
            pstmt.executeUpdate();
        }
    }
}
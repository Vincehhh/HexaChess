package im.bpu.hexachess.dao;

import im.bpu.hexachess.entity.Puzzle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PuzzleDAO extends DAO<Puzzle> {
	@Override
	public Puzzle create(Puzzle puzzle) {
		String request =
			"INSERT INTO puzzles (puzzle_id, moves, solutions, rating, theme, created_at) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, puzzle.getPuzzleId());
			pstmt.setString(2, puzzle.getMoves());
			pstmt.setString(3, puzzle.getSolutions());
			pstmt.setInt(4, puzzle.getRating());
			pstmt.setString(5, puzzle.getTheme());
			if (puzzle.getCreatedAt() != null)
				pstmt.setTimestamp(6, Timestamp.valueOf(puzzle.getCreatedAt()));
			else
				pstmt.setTimestamp(6, null);
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	@Override
	public Puzzle update(Puzzle puzzle) {
		String request = "UPDATE puzzles SET moves = ?, solutions = ?, rating = ?, theme = ? WHERE "
			+ "puzzle_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, puzzle.getMoves());
			pstmt.setString(2, puzzle.getSolutions());
			pstmt.setInt(3, puzzle.getRating());
			pstmt.setString(4, puzzle.getTheme());
			pstmt.setString(5, puzzle.getPuzzleId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	@Override
	public void delete(Puzzle puzzle) {
		String request = "DELETE FROM puzzles WHERE puzzle_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, puzzle.getPuzzleId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public Puzzle read(String id) {
		Puzzle puzzle = null;
		String request = "SELECT * FROM puzzles WHERE puzzle_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				puzzle = new Puzzle(rs.getString("puzzle_id"), rs.getString("moves"),
					rs.getString("solutions"), rs.getInt("rating"), rs.getString("theme"),
					rs.getTimestamp("created_at") != null
						? rs.getTimestamp("created_at").toLocalDateTime()
						: null);
			}
			rs.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return puzzle;
	}
	public ArrayList<Puzzle> readAll() {
		ArrayList<Puzzle> list = new ArrayList<>();
		String request = "SELECT * FROM puzzles";
		try {
			ResultSet rs = stmt.executeQuery(request);
			while (rs.next()) {
				list.add(new Puzzle(rs.getString("puzzle_id"), rs.getString("moves"),
					rs.getString("solutions"), rs.getInt("rating"), rs.getString("theme"),
					rs.getTimestamp("created_at") != null
						? rs.getTimestamp("created_at").toLocalDateTime()
						: null));
			}
			rs.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return list;
	}
}
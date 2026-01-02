package im.bpu.hexachess.dao;

import im.bpu.hexachess.entity.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsDAO extends DAO<Settings> {
	@Override
	public Settings create(Settings settings) {
		String request = "INSERT INTO settings (player_id, theme, show_legal_moves, "
			+ "auto_promote_queen, ai_difficulty_level) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, settings.getPlayerId());
			pstmt.setString(2, settings.getTheme());
			pstmt.setBoolean(3, settings.isShowLegalMoves());
			pstmt.setBoolean(4, settings.isAutoPromoteQueen());
			pstmt.setInt(5, settings.getAiDifficultyLevel());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return settings;
	}
	@Override
	public Settings update(Settings settings) {
		String request =
			"UPDATE settings SET theme = ?, show_legal_moves = ?, auto_promote_queen = ?, "
			+ "ai_difficulty_level = ? WHERE player_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, settings.getTheme());
			pstmt.setBoolean(2, settings.isShowLegalMoves());
			pstmt.setBoolean(3, settings.isAutoPromoteQueen());
			pstmt.setInt(4, settings.getAiDifficultyLevel());
			pstmt.setString(5, settings.getPlayerId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return settings;
	}
	@Override
	public void delete(Settings settings) {
		String request = "DELETE FROM settings WHERE player_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, settings.getPlayerId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public Settings read(String playerId) {
		Settings settings = null;
		String request = "SELECT * FROM settings WHERE player_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, playerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				settings = new Settings(rs.getString("player_id"), rs.getString("theme"),
					rs.getBoolean("show_legal_moves"), rs.getBoolean("auto_promote_queen"),
					rs.getInt("ai_difficulty_level"));
			}
			rs.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return settings;
	}
}
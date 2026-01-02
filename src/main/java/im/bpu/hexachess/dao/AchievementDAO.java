package im.bpu.hexachess.dao;

import im.bpu.hexachess.entity.Achievement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchievementDAO extends DAO<Achievement> {
	@Override
	public Achievement create(Achievement achievement) {
		String request =
			"INSERT INTO achievements (achievement_id, name, description) VALUES(?, ?, ?)";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, achievement.getAchievementId());
			pstmt.setString(2, achievement.getName());
			pstmt.setString(3, achievement.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return achievement;
	}
	@Override
	public Achievement update(Achievement achievement) {
		String request =
			"UPDATE achievements SET name = ?, description = ? WHERE achievement_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, achievement.getName());
			pstmt.setString(2, achievement.getDescription());
			pstmt.setString(3, achievement.getAchievementId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return achievement;
	}
	@Override
	public void delete(Achievement achievement) {
		String request = "DELETE FROM achievements WHERE achievement_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, achievement.getAchievementId());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	public Achievement read(String id) {
		Achievement achievement = null;
		String request = "SELECT * FROM achievements WHERE achievement_id = ?";
		try {
			PreparedStatement pstmt = connect.prepareStatement(request);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				achievement = new Achievement(rs.getString("achievement_id"), rs.getString("name"),
					rs.getString("description"));
			}
			rs.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return achievement;
	}
	public ArrayList<Achievement> readAll() {
		ArrayList<Achievement> list = new ArrayList<>();
		String request = "SELECT * FROM achievements";
		try {
			ResultSet rs = stmt.executeQuery(request);
			while (rs.next()) {
				list.add(new Achievement(rs.getString("achievement_id"), rs.getString("name"),
					rs.getString("description")));
			}
			rs.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return list;
	}
}
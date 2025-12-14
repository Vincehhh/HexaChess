package im.bpu.hexachess.model;

public class Piece {
	public PieceType type;
	public boolean isWhite;
	Piece(PieceType type, boolean isWhite) {
		this.type = type;
		this.isWhite = isWhite;
	}
}
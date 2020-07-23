package imericxu.components;

public class Pos
{
    private final int row;
    private final int col;
    
    public Pos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public static Dir calcDirection(Pos start, Pos end)
    {
        if (end.row < start.row) return Dir.UP;
        if (end.col > start.col) return Dir.RIGHT;
        if (end.row > start.row) return Dir.DOWN;
        if (end.col < start.col) return Dir.LEFT;
        return null;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public boolean equals(Pos other)
    {
        return row == other.row && col == other.col;
    }
}

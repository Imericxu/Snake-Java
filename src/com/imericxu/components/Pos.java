package com.imericxu.components;

public class Pos
{
    private final int row;
    private final int col;
    
    public Pos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public static Direction calcDirection(Pos start, Pos end)
    {
        if (end.row < start.row) return Direction.NORTH;
        if (end.col > start.col) return Direction.EAST;
        if (end.row > start.row) return Direction.SOUTH;
        if (end.col < start.col) return Direction.WEST;
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
}

package imericxu.components.core;

import imericxu.components.Dir;
import imericxu.components.Pos;

import java.util.ArrayList;
import java.util.Objects;

public class Snake
{
    /**
     * A queue of keyed directions to prevent reversal
     */
    public final ArrayList<Dir> keyQueue;
    private final int rows;
    private final int cols;
    /**
     * All the points the snake contains in order
     */
    private final ArrayList<Pos> path;
    /**
     * The direction the snake is currently facing
     */
    private Dir direction;
    
    public Snake(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        path = new ArrayList<>();
        path.add(new Pos(rows / 2, cols / 2));
        keyQueue = new ArrayList<>();
    }
    
    /* * * * * * * * * * * * * * * * * * * * *
    Methods
    * * * * * * * * * * * * * * * * * * * * */
    
    /**
     * Adds a {@link Pos} to the end of the {@link #path} based on calculated {@link #direction}
     */
    public void increaseLength()
    {
        Pos end = path.get(path.size() - 1);
        Dir dir;
        
        if (path.size() > 1)
        {
            Pos secToLast = path.get(path.size() - 2);
            dir = Pos.calcDirection(secToLast, end);
        }
        else dir = direction.opposite();
        
        int row = end.getRow();
        int col = end.getCol();
        
        switch (Objects.requireNonNull(dir))
        {
        case UP -> path.add(new Pos(row - 1, col));
        case DOWN -> path.add(new Pos(row + 1, col));
        case LEFT -> path.add(new Pos(row, col - 1));
        case RIGHT -> path.add(new Pos(row, col + 1));
        }
    }
    
    /**
     * Modifies the {@link #path} array so the snake "moves" based on its
     * current {@link #direction}
     */
    public void move()
    {
        if (direction != null)
        {
            switch (direction)
            {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            }
        }
    }
    
    public boolean isTouchingSelf()
    {
        Pos head = path.get(0);
        for (int i = 1; i < path.size(); ++i)
        {
            if (head.equals(path.get(i))) return true;
        }
        return false;
    }
    
    public boolean isOutOfBounds()
    {
        int row = path.get(0).getRow();
        int col = path.get(0).getCol();
        
        if (row < 0) return true;
        if (row > rows - 1) return true;
        if (col < 0) return true;
        return col > cols - 1;
    }
    
    public void pushQueue(Dir direction)
    {
        while (keyQueue.size() >= 3) keyQueue.remove(0);
        if (!keyQueue.isEmpty() && direction == keyQueue.get(keyQueue.size() - 1).opposite())
            return;
        if (this.direction != null && direction == this.direction.opposite()) return;
        
        keyQueue.add(direction);
    }
    
    public Dir nextDir()
    {
        return keyQueue.isEmpty() ? null : keyQueue.remove(0);
    }
    
    /* * * * * * * * * * * * * * * * * * * * *
    Getters and Setters
    * * * * * * * * * * * * * * * * * * * * */
    
    public ArrayList<Pos> getPath()
    {
        return path;
    }
    
    public void setDir(Dir direction)
    {
        this.direction = direction;
    }
    
    /* * * * * * * * * * * * * * * * * * * * *
    Helper Methods
    * * * * * * * * * * * * * * * * * * * * */
    
    private void moveUp()
    {
        Pos head = path.get(0);
        path.add(0, new Pos(head.getRow() - 1, head.getCol()));
        path.remove(path.size() - 1);
    }
    
    private void moveDown()
    {
        Pos head = path.get(0);
        path.add(0, new Pos(head.getRow() + 1, head.getCol()));
        path.remove(path.size() - 1);
    }
    
    private void moveLeft()
    {
        Pos head = path.get(0);
        path.add(0, new Pos(head.getRow(), head.getCol() - 1));
        path.remove(path.size() - 1);
    }
    
    private void moveRight()
    {
        Pos head = path.get(0);
        path.add(0, new Pos(head.getRow(), head.getCol() + 1));
        path.remove(path.size() - 1);
    }
}

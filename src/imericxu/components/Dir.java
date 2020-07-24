package imericxu.components;

public enum Dir
{
    UP, DOWN, LEFT, RIGHT;
    
    public Dir opposite()
    {
        return switch (this)
                {
                    case UP -> DOWN;
                    case DOWN -> UP;
                    case LEFT -> RIGHT;
                    case RIGHT -> LEFT;
                };
    }
}

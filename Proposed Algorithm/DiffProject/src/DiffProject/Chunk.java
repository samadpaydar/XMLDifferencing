package DiffProject;

/**
 * <p>Title: XML Diff Project</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author Samad Paydar
 * @version 1.0
 */
public class Chunk {
    public int startIndex = -1;
    public int endIndex = -1;

    public Chunk(int i, int j) {
        startIndex = i;
        endIndex = j;
    }
}

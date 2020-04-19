package DiffProject;

/**
 * <p>Title: XML Diff Project</p>
 * <p>Description: This class implements the Record concept.</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * @author Samad Paydar
 * @version 1.0
 */
public class LookupTableRecord {

    public int oldIndex;
    public int newIndex;
    public int status;
    public int param;

    public LookupTableRecord(int theOldIndex, int theNewIndex) {
        oldIndex = theOldIndex;
        newIndex = theNewIndex;
    }

}

package DiffProject;

import java.util.ArrayList;
/**
 * <p>Title: XML Diff Project</p>
 * <p>Description: This class implements the rows of the lookup table. Each row, includes a headName, and a list of records. </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * @author Samad Paydar
 * @version 1.0
 */
public class LookupTableRow {

    public ArrayList records;
    public String headName;
    public int insertedOldIndexCount = 0;
    public int insertedNewIndexCount = 0;

    public LookupTableRow() {
        records = new ArrayList();
    }

    public LookupTableRow(String name) {
        records = new ArrayList();
        headName = name;
    }

    public int insertOldRecord(int index) {
        LookupTableRecord temp;
        if(insertedOldIndexCount >= insertedNewIndexCount) {
            LookupTableRecord record = new LookupTableRecord(index, -1);
            record.status = Constants.RECORD_IS_DELETED;
            records.add(record);
            insertedOldIndexCount++;
            return -1;
        } else {
            int i = insertedOldIndexCount;
            temp = (LookupTableRecord) records.get(i);
            temp.oldIndex = index;
            temp.status = Constants.RECORD_IS_MOVED;
            insertedOldIndexCount++;
            return temp.newIndex;
        }
    }

    public int insertNewRecord(int index) {
        LookupTableRecord temp;
        if(insertedNewIndexCount >= insertedOldIndexCount) {
            LookupTableRecord record = new LookupTableRecord(-1, index);
            record.status = Constants.RECORD_IS_INSERTED;
            records.add(record);
            insertedNewIndexCount++;
            return -1;
        } else {
            int i = insertedNewIndexCount;
            temp = (LookupTableRecord) records.get(i);
            temp.newIndex = index;
            temp.status = Constants.RECORD_IS_MOVED;
            insertedNewIndexCount++;
            return temp.oldIndex;
        }
    }

    public LookupTableRecord getRecord(int index) {
        return (LookupTableRecord)records.get(index);
    }

}
